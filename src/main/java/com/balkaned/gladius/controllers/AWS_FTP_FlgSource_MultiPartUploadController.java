package com.balkaned.gladius.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.EmpleadoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;

@RestController
@Slf4j
public class AWS_FTP_FlgSource_MultiPartUploadController {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Autowired
    CompaniaService companiaService;

    @Autowired
    EmpleadoService empleadoService;

    @SneakyThrows
    @RequestMapping(value = "/AWSorFTP_flgsource_MultipartUpload@{accion}@{idComp}@{idTrab}", method = RequestMethod.POST)
    public ModelAndView AWSorFTP_flgsource_MultipartUpload(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                                           @PathVariable String accion,
                                                           @PathVariable String idComp,
                                                           @PathVariable String idTrab,
                                                           @RequestParam("uploadFile") MultipartFile uploadFile) throws ServletException, IOException, NoSuchFileException, UncheckedIOException {
        log.info("\n\n\n/AWSorFTP_flgsource_MultipartUpload");

        String accionx = accion;
        String codciax = idComp;
        String idTrabx = idTrab;
        log.info("accionx: " + accionx);
        log.info("codciax: " + codciax);
        log.info("idTrabx: " + idTrabx);

        String idimg = request.getParameter("idimg");
        String idDerHab = request.getParameter("idDerHab");
        log.info("idimg: " + idimg);
        log.info("idDerHab: " + idDerHab);

        if (codciax.equals("") || codciax == null || codciax == "") {
            log.info("codciax vacio");
        } else {
            Compania ciainfo = companiaService.getCompaniaAll(Integer.valueOf(idComp));
            log.info("ciainfo.getDescCia(): " + ciainfo.getDescCia());
            log.info("ciainfo.getUrlflgsource(): " + ciainfo.getUrlflgsource());

            //======================================
            //======================================
            //   Getting Started with Amazon S3
            //======================================
            //======================================

            //################# AWS CONEXION ##############################################################################
            if (ciainfo.getUrlflgsource().equals("1")) {
                log.info("################# AWS CONEXION ##################");

                System.out.println("===========================================");
                System.out.println("Getting Started with Amazon S3");
                System.out.println("===========================================");

                //Codigo cuando el flag 1 es AWS
                log.info("flgsource 1 : AWS");

                Regions clientRegion = null;
                String bucket_name = "";
                String key_name = "";
                String passPhrase = "";
                AWSCredentials credentials = null;
                AmazonS3 s3 = null;

                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name = ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();

                log.info("clientRegion: " + clientRegion);
                log.info("bucket_name: " + bucket_name);
                log.info("key_name: " + clientRegion);
                log.info("passPhrase: " + passPhrase);

                //######### SUBIR FOTO EMPL AWS ##################################################################################
                if (accion.equals("subirFotoEmpl")) {
                    log.info("#### AWS subirFotoEmpl ####");

                    Integer codciaxRecup = Integer.valueOf(idComp);
                    Empleado emp = empleadoService.recuperarCabecera(codciaxRecup, Integer.parseInt(idTrab));

                    String nombreArchivo = "";

                    log.info("Direccion existe");
                    log.info("request: " + request);
                    log.info("ciainfo.getIexsourcedes().trim(): " + ciainfo.getIexsourcedes().trim());

                    try {
                        nombreArchivo = codciax + "/fotoemp/" + idimg + "." + "jpg";
                        log.info("nombreArchivo: " + nombreArchivo);

                        credentials = new BasicAWSCredentials(key_name, passPhrase);
                        s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                        s3.putObject(bucket_name, nombreArchivo, nombreArchivo);
                        log.info("Path: " + nombreArchivo);

                        InputStream in = uploadFile.getInputStream();
                        File tmp = null;
                        tmp = File.createTempFile("s3test", ".jpg");
                        Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        PutObjectRequest request2 = new PutObjectRequest(bucket_name, nombreArchivo, tmp);

                        ObjectMetadata metadata = new ObjectMetadata();
                        metadata.setContentType(uploadFile.getContentType());
                        request2.setMetadata(metadata);
                        s3.putObject(request2);

                        tmp.delete();
                        tmp.deleteOnExit();
                        System.gc();

                        log.info("Archivo escrito: " + nombreArchivo);
                        emp.setIexlogo(idimg + "." + "jpg");
                        empleadoService.actualizarFoto(emp);
                    } catch (IOException e) {
                        log.info("You failed to upload " + nombreArchivo + " => " + e.getMessage());
                    }

                    return new ModelAndView("redirect:/detalleEmpl@" + idTrab);
                }


                //######### SUBIR FOTO DERECHO HABIENTE AWS ##################################################################################
                if (accion.equals("subirFotoDerHabiente")) {
                    log.info("#### AWS subirFotoDerHabiente ####");

                    Integer codciaxRecup = Integer.valueOf(idComp);
                    Empleado emp = empleadoService.recuperarCabecera(codciaxRecup, Integer.parseInt(idTrab));

                    String nombreArchivo = "";

                    log.info("Direccion existe");
                    log.info("request: " + request);

                    try {
                        nombreArchivo = codciax + "/fotoderhab/" + idDerHab + "/" + idimg + "." + "jpg";
                        log.info("nombreArchivo: " + nombreArchivo);

                        credentials = new BasicAWSCredentials(key_name, passPhrase);
                        s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                        s3.putObject(bucket_name, nombreArchivo, nombreArchivo);
                        log.info("Path: " + nombreArchivo);

                        InputStream in = uploadFile.getInputStream();
                        File tmp = null;
                        tmp = File.createTempFile("s3test", ".jpg");
                        Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        PutObjectRequest request2 = new PutObjectRequest(bucket_name, nombreArchivo, tmp);

                        ObjectMetadata metadata = new ObjectMetadata();
                        metadata.setContentType(uploadFile.getContentType());
                        request2.setMetadata(metadata);
                        s3.putObject(request2);

                        log.info("Archivo escrito: " + nombreArchivo);
                        emp.setIexlogo(idimg + "." + "jpg");
                        empleadoService.actualizarFoto(emp);
                    } catch (IOException e) {
                        log.info("You failed to upload " + nombreArchivo + " => " + e.getMessage());
                    }

                    return new ModelAndView("redirect:/derechoHab@" + idTrab);
                }


                //######### SUBIR LOGO COMPANIA AWS ##################################################################################
                if (accion.equals("subirLogoCompania")) {
                    log.info("#### AWS subirLogoCompania ####");

                    String nombreArchivo = "";

                    log.info("Direccion existe");
                    log.info("request: " + request);

                    try {
                        nombreArchivo = "img/" + idimg + "." + "jpg";
                        log.info("nombreArchivo: " + nombreArchivo);

                        credentials = new BasicAWSCredentials(key_name, passPhrase);
                        s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                        s3.putObject(bucket_name, nombreArchivo, nombreArchivo);
                        log.info("Path: " + nombreArchivo);

                        InputStream in = uploadFile.getInputStream();
                        File tmp = null;
                        tmp = File.createTempFile("s3test", ".jpeg");
                        Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        PutObjectRequest request2 = new PutObjectRequest(bucket_name, nombreArchivo, tmp);

                        ObjectMetadata metadata = new ObjectMetadata();
                        metadata.setContentType(uploadFile.getContentType());
                        request2.setMetadata(metadata);
                        s3.putObject(request2);

                        log.info("Archivo escrito: " + nombreArchivo);

                        Compania cia = new Compania();
                        cia.setIdCodcia(Integer.parseInt(idimg));
                        cia.setUrlLogo(idimg + "." + "jpg");
                        companiaService.logoCompania(cia);

                        return new ModelAndView("redirect:/editarCompania@" + idComp);

                    } catch (IOException e) {
                        log.info("You failed to upload " + nombreArchivo + " => " + e.getMessage());
                    }
                }
            }


            //======================================
            //======================================
            //           FTP Connection
            //======================================
            //======================================

            //################# FTP CONEXION ##############################################################################
            if (ciainfo.getUrlflgsource().equals("2")) {
                log.info("################# FTP CONEXION ##################");

                System.out.println("===========================================");
                System.out.println("    FTP Connection");
                System.out.println("===========================================");

                //Codigo cuando el flag es 2 FTP
                log.info("flgsource 2 : FTP");

                String server = ciainfo.getIexurlfileserver();
                int port = Integer.parseInt(ciainfo.getIexportsource());
                String user = ciainfo.getIexususource().trim();
                String pass = ciainfo.getIexpasssource().trim();

                log.info("server: " + server);
                log.info("port: " + port);
                log.info("user: " + user);
                log.info("pass: " + pass);

                //######### SUBIR FOTO EMPL FTP ##################################################################################
                if (accion.equals("subirFotoEmpl")) {
                    log.info("#### FTP subirFotoEmpl ####");

                    Integer codciaxRecup = Integer.valueOf(idComp);
                    Empleado emp = empleadoService.recuperarCabecera(codciaxRecup, Integer.parseInt(idTrab));

                    try {
                        FTPClient ftpClient = new FTPClient();
                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);

                        String remoteFile = "/" + codciax + "/fotoemp/" + idimg + "." + "jpg";
                        log.info("Path: " + remoteFile);

                        InputStream in = uploadFile.getInputStream();

                        ftpClient.storeFile(remoteFile, in);

                        in.close();
                        ftpClient.logout();

                        emp.setIexlogo(idimg + "." + "jpg");
                        empleadoService.actualizarFoto(emp);
                    } catch (IOException e) {
                        log.info(e.getMessage());
                    }

                    return new ModelAndView("redirect:/detalleEmpl@" + idTrab);

                }

                //######### SUBIR FOTO DERECHO HABIENTE FTP ##################################################################################
                if (accion.equals("subirFotoDerHabiente")) {
                    log.info("#### FTP subirFotoDerHabiente ####");

                    Integer codciaxRecup = Integer.valueOf(idComp);
                    Empleado emp = empleadoService.recuperarCabecera(codciaxRecup, Integer.parseInt(idTrab));

                    try {
                        FTPClient ftpClient = new FTPClient();
                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);

                        String directory = "/" + codciax + "/fotoderhab/" + idDerHab;
                        String remoteFile = "/" + codciax + "/fotoderhab/" + idDerHab + "/" + idimg + ".jpg";
                        log.info("Path: " + remoteFile);

                        InputStream in = uploadFile.getInputStream();
                        ftpClient.makeDirectory(directory);
                        ftpClient.storeFile(remoteFile, in);

                        in.close();
                        ftpClient.logout();

                        emp.setIexlogo(idimg + ".jpg");
                        empleadoService.actualizarFoto(emp);
                    } catch (IOException e) {
                        log.info(e.getMessage());
                    }

                    return new ModelAndView("redirect:/derechoHab@" + idTrab);
                }

                //######### SUBIR LOGO COMPANIA FTP ##################################################################################
                if (accion.equals("subirLogoCompania")) {
                    log.info("#### FTP subirLogoCompania ####");

                    try {
                        FTPClient ftpClient = new FTPClient();
                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);

                        String remoteFile = "/img/" + idimg + ".jpg";
                        log.info("Path: " + remoteFile);

                        InputStream in = uploadFile.getInputStream();

                        ftpClient.storeFile(remoteFile, in);

                        in.close();
                        ftpClient.logout();
                    } catch (IOException e) {
                        log.info(e.getMessage());
                    }

                    return new ModelAndView("redirect:/editarCompania@" + idComp);
                }
            }
        }

        return null;
    }

}
