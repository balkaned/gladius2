package com.balkaned.gladius.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.EmpleadoService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@RestController
public class FlgSourceController {
    static Logger logger = Logger.getLogger(FlgSourceController.class.getName());

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    EmpleadoService empleadoService;

    @RequestMapping("/flgsource@{accion}@{idComp}@{urlLogo}")
    public ModelAndView flgsource(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                  @PathVariable String accion,
                                  @PathVariable String idComp,
                                  @PathVariable String urlLogo,
                                  @RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        logger.info("/flgsource");

        logger.info("accion: "+accion);
        String codciax=idComp;
        String fileurl=urlLogo;

        Compania ciainfo= companiaService.getCompaniaAll(Integer.valueOf(idComp));

        Integer outrep=1;
        String fileName = "";
        Regions clientRegion = null;
        String bucket_name = "";
        String key_name ="";
        String passPhrase = "";
        AWSCredentials credentials =null;
        AmazonS3 s3 = null;
        File tmp = null;
        String pathfile = "";

        if(ciainfo.getUrlflgsource()=="1"){
            //Codigo cuando el flag es AWS
            logger.info("flgsource 1 : AWS");

            if(accion.equals("verLogo")){

                outrep=0;
                fileName = "img/"+fileurl;;
                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name =ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();

                logger.info("clientRegion: "+clientRegion);
                logger.info("bucket_name: "+bucket_name);
                logger.info("key_name: "+clientRegion);
                logger.info("passPhrase: "+passPhrase);

                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                try {
                    S3Object o = s3.getObject(bucket_name, fileName);
                    S3ObjectInputStream s3is = o.getObjectContent();

                    tmp = File.createTempFile("s3test", ".jpeg");
                    Files.copy(s3is, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

                    try {
                        BufferedImage image = ImageIO.read(tmp);
                        ImageIO.write(image, "jpeg", jpegOutputStream);
                    } catch (IllegalArgumentException e) {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                    tmp.delete();
                    byte[] imgByte = jpegOutputStream.toByteArray();

                    response.setHeader("Cache-Control", "no-store");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                    response.setContentType("image/jpeg");
                    ServletOutputStream responseOutputStream = response.getOutputStream();
                    responseOutputStream.write(imgByte);
                    responseOutputStream.flush();
                    responseOutputStream.close();

                }catch (IOException ex) {
                    logger.info("Fallo");
                }
            }

            if(accion.equals("verFoto")){
                outrep=0;
                pathfile = codciax+"/fotoemp/"+fileurl;

                logger.info("######LECTOR URL IMG:####### :"+pathfile);

                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());

                System.out.format("Entro a la CIAINFO");
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name =ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();

                fileName = pathfile;
                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                Integer pospoint = fileurl.indexOf(".");
                String extension = fileurl.substring(pospoint);
                String extension2 = fileurl.substring(pospoint+1);
                System.out.println("Fileurl ="+fileurl);
                System.out.println("Extension file ="+extension);

                S3Object o = s3.getObject(bucket_name, fileName);
                S3ObjectInputStream s3is = o.getObjectContent();

                tmp = File.createTempFile("s3test", extension);
                Files.copy(s3is, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

                try {
                    BufferedImage image = ImageIO.read(tmp);
                    ImageIO.write(image, extension2, jpegOutputStream);
                } catch (IllegalArgumentException e) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }

                tmp.delete();
                byte[] imgByte = jpegOutputStream.toByteArray();

                response.setHeader("Cache-Control", "no-store");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setContentType("image/"+extension2);
                ServletOutputStream responseOutputStream = response.getOutputStream();
                responseOutputStream.write(imgByte);
                responseOutputStream.flush();
                responseOutputStream.close();
            }

            if(accion.equals("subirFoto")){
                String idimg = request.getParameter("idimg");
                String idTrab = request.getParameter("idTrab");

                Integer codciaxRecup = Integer.valueOf(idComp);

                Empleado emp = empleadoService.recuperarCabecera(codciaxRecup,Integer.parseInt(idTrab));

                String filePath ="";
                String filelink ="";
                String target="";

                System.out.println("Accion :"+accion);
                // checks if the request actually contains upload file

                String server = "ftp.balkaned.com";
                int port = 21;
                String user = "ebaldeon@balkaned.com";
                String pass = "@Kekereke1984";

                String uploadPath = "";

                S3Object o =null;
                String nombreArchivo = "";
                String fileName2 = "";

                logger.info("Direccion existe");
                logger.info("request: "+request);

                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name =ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();

                logger.info("ciainfo.getIexsourcedes().trim(): "+ciainfo.getIexsourcedes().trim());

                try {
                    nombreArchivo=codciax+"/fotoemp/"+idimg+"."+"jpg";
                    logger.info("nomeArchivo: "+nombreArchivo);

                    credentials = new BasicAWSCredentials(key_name, passPhrase);
                    s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                    s3.putObject(bucket_name, nombreArchivo, nombreArchivo);

                    InputStream in = uploadFile.getInputStream();
                    File tmp2 = null;
                    tmp2 = File.createTempFile("s3test", ".jpeg");
                    Files.copy(in, tmp2.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    PutObjectRequest request2 = new PutObjectRequest(bucket_name, nombreArchivo,tmp2 );

                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentType(uploadFile.getContentType());
                    request2.setMetadata(metadata);
                    s3.putObject(request2);

                    logger.info("Archivo escrito: " + nombreArchivo);
                    emp.setIexlogo(idimg+"."+"jpg");
                    empleadoService.actualizarFoto(emp);
                } catch (final Exception e) {
                    System.out.println("You failed to upload " + nombreArchivo + " => " + e.getMessage());
                }

                return new ModelAndView("redirect:/detalleEmpl@"+idTrab);
            }

            if(accion.equals("verReporte")){

            }
        } else if (ciainfo.getUrlflgsource()=="2"){
            //Codigo cuando el flag es FTP
            logger.info("flgsource 2 : FTP");

            if(accion.equals("verLogo")){

            }

            if(accion.equals("verFoto")){

            }

            if(accion.equals("subirFoto")){

            }

            if(accion.equals("verReporte")){

            }
        }

        return null;
    }

}

