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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class AWS_FTP_FlgSourceController {

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
    @RequestMapping(value = "/AWSorFTP_flgsource@{accion}@{idComp}@{idTrab}@{urlLogo}@{idDerHab}@{nombreJasper}")
    public ModelAndView AWSorFTP_flgsource(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable String accion,
                                           @PathVariable String idComp,
                                           @PathVariable String idTrab,
                                           @PathVariable String urlLogo,
                                           @PathVariable String idDerHab,
                                           @PathVariable String nombreJasper) {
        log.info("\n\n/AWSorFTP_flgsource");

        String accionx = accion;
        String codciax = idComp;
        String idTrabx = idTrab;
        String fileurl = urlLogo;
        String idDerHabx = idDerHab;
        String nombreJasperx = nombreJasper;

        log.info("accionx: " + accionx);
        log.info("codciax: " + codciax);
        log.info("idTrabx: " + idTrab);
        log.info("fileurl: " + urlLogo);
        log.info("idDerHabx: " + idDerHabx);
        log.info("nombreJasperx: " + nombreJasperx);

        Compania ciainfo = companiaService.getCompaniaAll(Integer.valueOf(codciax));
        log.info("ciainfo.getDescCia(): " + ciainfo.getDescCia());
        log.info("ciainfo.getUrlflgsource(): " + ciainfo.getUrlflgsource());

        //################# AWS CONEXION ##############################################################################
        if (ciainfo.getUrlflgsource().equals("1")) {
            log.info("################# AWS CONEXION ##################");

            System.out.println("===========================================");
            System.out.println("Getting Started with Amazon S3");
            System.out.println("===========================================");

            //Codigo cuando el flag 1 es AWS
            log.info("flgsource 1 : AWS");

            //Integer outrep = 1;
            String fileName = "";
            Regions clientRegion = null;
            String bucket_name = "";
            String key_name = "";
            String passPhrase = "";
            AWSCredentials credentials = null;
            AmazonS3 s3 = null;
            File tmp = null;
            String pathfile = "";

            clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
            bucket_name = ciainfo.getIexsourcedes().trim();
            key_name = ciainfo.getIexususource().trim();
            passPhrase = ciainfo.getIexpasssource().trim();

            log.info("clientRegion: " + clientRegion);
            log.info("bucket_name: " + bucket_name);
            log.info("key_name: " + clientRegion);
            log.info("passPhrase: " + passPhrase);

            //######### VER LOGO AWS ##################################################################################
            if (accion.equals("verLogo")) {
                log.info("#### AWS verLogo ####");

                //outrep = 0;
                fileName = "img/" + fileurl;

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

                } catch (IOException ex) {
                    log.info("Fallo");
                }
            }

            //######### VER FOTO AWS ########################################################################
            if (accion.equals("verFoto")) {
                log.info("#### AWS verFoto ####");

                //outrep = 0;
                pathfile = codciax + "/fotoemp/" + fileurl;

                log.info("###### LECTOR URL IMG ####### :" + pathfile);

                fileName = pathfile;
                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                Integer pospoint = fileurl.indexOf(".");
                String extension = fileurl.substring(pospoint);
                String extension2 = fileurl.substring(pospoint + 1);
                log.info("Fileurl =" + fileurl);
                log.info("Extension file =" + extension);

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
                response.setContentType("image/" + extension2);
                ServletOutputStream responseOutputStream = response.getOutputStream();
                responseOutputStream.write(imgByte);
                responseOutputStream.flush();
                responseOutputStream.close();
            }

            if (accion.equals("FOTODER")) {
                log.info("#### AWS FOTODERHAB ####");

                // pathfile = (String)session.getAttribute("GLADIUS_FILE")+"fotoemp/"+fileurl;
                pathfile = codciax + "/fotoderhab/" + idDerHabx + "/" + fileurl;

                log.info("######LECTOR URL IMG:####### :" + pathfile);

                fileName = pathfile;
                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                Integer pospoint = fileurl.indexOf(".");
                String extension = fileurl.substring(pospoint);
                String extension2 = fileurl.substring(pospoint + 1);
                log.info("Fileurl =" + fileurl);
                log.info("Extension file =" + extension);

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
                response.setContentType("image/" + extension2);
                ServletOutputStream responseOutputStream = response.getOutputStream();
                responseOutputStream.write(imgByte);
                responseOutputStream.flush();
                responseOutputStream.close();

                /*else if (ciainfo.getUrlflgsource().equals("2")) {

                    String path_img = ciainfo.getIexurlfileserver();

                    //pathfile = (String)session.getAttribute("GLADIUS_IMG")+fileurl;
                    pathfile = path_img + codciax + "/fotoderhab/" + idDerHabx + "/" + fileurl;

                    // pathfile = (String)session.getAttribute("GLADIUS_FILE")+"fotoemp/"+fileurl;

                    ServletOutputStream out;
                    out = response.getOutputStream();
                    // FileInputStream fin = new FileInputStream("c:\\test\\java.jpg");
                    FileInputStream fin = new FileInputStream(pathfile);

                    BufferedInputStream bin = new BufferedInputStream(fin);
                    BufferedOutputStream bout = new BufferedOutputStream(out);
                    int ch = 0;
                    ;
                    while ((ch = bin.read()) != -1) {
                        bout.write(ch);
                    }

                    bin.close();
                    fin.close();
                    bout.close();
                    out.close();

                }*/
            }

            if (accion.equals("verReporteExcel")) {
                log.info("#### AWS verReporte Excel ####");

                response.setHeader("Content-Disposition", "attachment; filename=" + nombreJasper + ".xls");

                InputStream inputStream = null;

                fileName = "reportes/" + nombreJasper + ".jasper";
                log.info("Nombre Jasper: " + fileName);

                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                S3Object o = s3.getObject(bucket_name, fileName);

                inputStream = o.getObjectContent();

                Map parametros = new HashMap();
                parametros.put("P_CODCIA", Integer.valueOf(codciax));
                parametros.put("P_CODTRA", -1);
                parametros.put("SUBREPORT_DIR", request.getServletContext().getRealPath(""));

                Connection conn = template.getDataSource().getConnection();

                try {

                    JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conn);

                    log.info("jasperPrint: " + jasperPrint.getName());

                    if (jasperPrint != null) {
                        log.info("Se encontró jasper");

                        fileName = "ReporteExcelTodos.xls";

                        /*JRExporter exporter = new JRPdfExporter();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
                        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
                        exporter.exportReport();*/

                        JRXlsExporter exporter = new JRXlsExporter();
                        ServletOutputStream ouputStream = response.getOutputStream();
                        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ouputStream));
                        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                        configuration.setOnePagePerSheet(false);
                        configuration.setDetectCellType(true);
                        configuration.setCollapseRowSpan(false);
                        exporter.setConfiguration(configuration);
                        exporter.exportReport();
                    }
                } catch (JRException ex) {
                    log.info("No compila ");
                }
            }

            if (accion.equals("verReportePDF")) {
                log.info("#### AWS verReporte PDF ####");

                String path = "";
                String logo = "";
                String fotoemp = "";

                log.info("codciax:" + codciax);
                log.info("idTrabx: " + idTrabx);

                Empleado empleado = empleadoService.recuperarCabecera(Integer.valueOf(codciax), Integer.valueOf(idTrabx));

                if (ciainfo.getUrlLogo() == null || ciainfo.getUrlLogo().equals("")) {
                    logo = "cia.jpg";
                } else {
                    logo = ciainfo.getUrlLogo();
                }

                if (empleado.getIexlogo() == null || empleado.getIexlogo().equals("")) {
                    fotoemp = "fotoemp.png";
                } else {
                    fotoemp = empleado.getIexlogo();
                }

                InputStream inputStreamfotoemp = null;
                InputStream inputStreamlogo = null;
                InputStream inputStreamRep = null;

                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name = ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();

                fileName = codciax + "/fotoemp/" + fotoemp;
                credentials = new BasicAWSCredentials(key_name, passPhrase);

                S3Object o = null;
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                o = s3.getObject(bucket_name, fileName);
                inputStreamfotoemp = o.getObjectContent();

                log.info("logo: " + logo);

                AmazonS3 s4 = null;
                S3Object o2 = null;
                fileName = "img/" + logo;
                s4 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                o2 = s4.getObject(bucket_name, fileName);
                inputStreamlogo = o2.getObjectContent();

                AmazonS3 s5 = null;
                S3Object o3 = null;
                s5 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                fileName = "reportes/" + nombreJasper + ".jasper";
                log.info("Nombre Jasper: " + fileName);
                o3 = s5.getObject(bucket_name, fileName);
                inputStreamRep = o3.getObjectContent();

                Map parametros = new HashMap();
                parametros.put("P_CODCIA", Integer.valueOf(codciax));
                parametros.put("P_CODTRA", Integer.parseInt(idTrabx));
                parametros.put("SUBREPORT_DIR", request.getServletContext().getRealPath(""));
                parametros.put("P_LOGO", inputStreamlogo);
                parametros.put("P_FOTO", inputStreamfotoemp);

                log.info("Ruta reporte:" + path);
                Connection conn = template.getDataSource().getConnection();
                OutputStream out = response.getOutputStream();

                try {
                    JasperReport reporte = (JasperReport) JRLoader.loadObject(inputStreamRep);
                    byte[] bytes = JasperRunManager.runReportToPdf(reporte, parametros, conn);

                    int len = bytes.length;
                    response.setContentLength(len);
                    out.write(bytes, 0, len);
                    out.flush();
                } catch (Exception e) {
                    log.info("Error Reporte:" + e.getMessage());
                }
            }
        }



        //################# FTP CONEXION ##############################################################################
        if (ciainfo.getUrlflgsource() == "2") {
            log.info("################# FTP CONEXION ##################");

            System.out.println("===========================================");
            System.out.println("Getting Started with FTP Connection");
            System.out.println("===========================================");

            //Codigo cuando el flag es 2 FTP
            log.info("flgsource 2 : FTP");

            if (accion.equals("verLogo")) {
                log.info("#### FTP verLogo ####");

            }

            if (accion.equals("verFoto")) {
                log.info("#### FTP verFoto ####");

            }

            if (accion.equals("subirFoto")) {
                log.info("#### FTP subirFoto ####");

            }

            if (accion.equals("verReporteExcel")) {
                log.info("#### FTP verReporte ####");

            }
        }

        return null;
    }


    @SneakyThrows
    @RequestMapping(value = "/AWSorFTP_flgsourceMultipart@{accion}@{idComp}@{urlLogo}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView AWSorFTP_flgsourceMultipart(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                                    @PathVariable String accion,
                                                    @PathVariable String idComp,
                                                    @PathVariable String urlLogo,
                                                    @RequestParam("uploadFile") MultipartFile uploadFile) {
        log.info("/AWSorFTP_flgsourceMultipart");

        log.info("accion: " + accion);
        String accionx = accion;
        String codciax = idComp;
        String fileurl = urlLogo;

        Compania ciainfo = companiaService.getCompaniaAll(Integer.valueOf(idComp));

        //################# AWS CONEXION ##############################################################################
        log.info("################# AWS CONEXION ##################");

        if (ciainfo.getUrlflgsource() == "1") {
            //Codigo cuando el flag 1 es AWS
            log.info("flgsource 1 : AWS");

            //Integer outrep = 1;
            String fileName = "";
            Regions clientRegion = null;
            String bucket_name = "";
            String key_name = "";
            String passPhrase = "";
            AWSCredentials credentials = null;
            AmazonS3 s3 = null;
            File tmp = null;
            String pathfile = "";

            clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
            bucket_name = ciainfo.getIexsourcedes().trim();
            key_name = ciainfo.getIexususource().trim();
            passPhrase = ciainfo.getIexpasssource().trim();

            log.info("clientRegion: " + clientRegion);
            log.info("bucket_name: " + bucket_name);
            log.info("key_name: " + clientRegion);
            log.info("passPhrase: " + passPhrase);

            //######### SUBIR FOTO AWS ##################################################################################
            if (accion.equals("subirFoto")) {
                log.info("#### AWS subirFoto ####");

                String idimg = request.getParameter("idimg");
                String idTrab = request.getParameter("idTrab");
                Integer codciaxRecup = Integer.valueOf(idComp);

                Empleado emp = empleadoService.recuperarCabecera(codciaxRecup, Integer.parseInt(idTrab));

                /*String filePath = "";
                String filelink = "";
                String target = "";*/

                //System.out.println("Accion :" + accion);
                // checks if the request actually contains upload file

                /*String server = "ftp.balkaned.com";
                int port = 21;
                String user = "ebaldeon@balkaned.com";
                String pass = "@Kekereke1984";

                String uploadPath = "";*/

                //S3Object o = null;
                String nombreArchivo = "";
                //String fileName2 = "";

                log.info("Direccion existe");
                log.info("request: " + request);
                log.info("ciainfo.getIexsourcedes().trim(): " + ciainfo.getIexsourcedes().trim());

                try {
                    nombreArchivo = codciax + "/fotoemp/" + idimg + "." + "jpg";
                    log.info("nomeArchivo: " + nombreArchivo);

                    credentials = new BasicAWSCredentials(key_name, passPhrase);
                    s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                    s3.putObject(bucket_name, nombreArchivo, nombreArchivo);

                    InputStream in = uploadFile.getInputStream();
                    File tmp2 = null;
                    tmp2 = File.createTempFile("s3test", ".jpeg");
                    Files.copy(in, tmp2.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    PutObjectRequest request2 = new PutObjectRequest(bucket_name, nombreArchivo, tmp2);

                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentType(uploadFile.getContentType());
                    request2.setMetadata(metadata);
                    s3.putObject(request2);

                    log.info("Archivo escrito: " + nombreArchivo);
                    emp.setIexlogo(idimg + "." + "jpg");
                    empleadoService.actualizarFoto(emp);
                } catch (final Exception e) {
                    log.info("You failed to upload " + nombreArchivo + " => " + e.getMessage());
                }

                return new ModelAndView("redirect:/detalleEmpl@" + idTrab);
            }
        }

        //################# FTP CONEXION ##############################################################################
        log.info("################# FTP CONEXION ##################");

        if (ciainfo.getUrlflgsource() == "2") {
            //Codigo cuando el flag es 2 FTP
            log.info("flgsource 2 : FTP");

            if (accion.equals("subirFoto")) {
                log.info("#### FTP subirFoto ####");

            }
        }

        return null;
    }

}

