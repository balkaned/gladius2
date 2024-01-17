package com.balkaned.gladius.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.FileImageLegajo;
import com.balkaned.gladius.beans.ParametroReport;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.EmpleadoService;
import com.balkaned.gladius.services.LegajoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.XlsExporterConfiguration;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
import java.util.*;

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

    @Autowired
    LegajoService legajoService;

    @SneakyThrows
    @RequestMapping(value = "/AWSorFTP_flgsource@{accion}@{idComp}@{idTrab}@{urlLogo}@{idDerHab}@{nombreJasper}@{parametrosJasper}@{carpetaLectura}@{idGrpfileLegajo}@{idImageLegajo}")
    public ModelAndView AWSorFTP_flgsource(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable String accion,
                                           @PathVariable String idComp,
                                           @PathVariable String idTrab,
                                           @PathVariable String urlLogo,
                                           @PathVariable String idDerHab,
                                           @PathVariable String nombreJasper,
                                           @PathVariable String parametrosJasper,
                                           @PathVariable String carpetaLectura,
                                           @PathVariable String idGrpfileLegajo,
                                           @PathVariable String idImageLegajo) {
        log.info("\n\n\n/AWSorFTP_flgsource");

        String accionx = accion;
        String codciax = idComp;
        String idTrabx = idTrab;
        String fileurl = urlLogo;
        String idDerHabx = idDerHab;
        String nombreJasperx = nombreJasper;
        String parametrosJasperx = parametrosJasper;
        String carpetaLecturax = carpetaLectura;
        String idGrpfileLegajox = idGrpfileLegajo;
        String idImageLegajox = idImageLegajo;

        log.info("accionx: " + accionx);
        log.info("codciax: " + codciax);
        log.info("idTrabx: " + idTrab);
        log.info("fileurl: " + urlLogo);
        log.info("idDerHabx: " + idDerHabx);
        log.info("nombreJasperx: " + nombreJasperx);
        log.info("parametrosJasperx: " + parametrosJasperx);
        log.info("carpetaLecturax: " + carpetaLecturax);
        log.info("idGrpfileLegajox: " + idGrpfileLegajox);
        log.info("idImageLegajox: " + idImageLegajox);

        int cantidadParametros = 0;
        List<ParametroReport> lspreport = new ArrayList<ParametroReport>();

        //Procesando parametros Jasper
        if (parametrosJasperx == null || parametrosJasperx.equals("") || parametrosJasperx.equals("null") || parametrosJasperx == "null") {
            log.info("No hay parámetros de reporte no se procesará parámetros");
        } else {
            cantidadParametros = Integer.parseInt(parametrosJasperx.substring(0, 1));
            log.info("cantidadParametros: " + cantidadParametros);
            String[] parts = null;
            for (int i = 0; i < cantidadParametros; i++) {
                log.info("i: " + i);
                String cadenaSinNumParametros = parametrosJasperx.substring(2, parametrosJasperx.length());
                log.info("cadenaSinNumParametros: " + cadenaSinNumParametros);
                parts = cadenaSinNumParametros.split("P");
                log.info("parts[]: " + parts[i]);
            }

            for (int i = 0; i < cantidadParametros; i++) {
                String stringDivisor[] = parts[i].split("=");
                if (stringDivisor.length >= 2) {
                    log.info("stringDivisor[0]: " + stringDivisor[0]);
                    log.info("stringDivisor[1]: " + stringDivisor[1]);

                    if (stringDivisor[0].contains("fec") || stringDivisor[0].contains("FEC")) {
                        String day = stringDivisor[1].substring(0, 2);
                        String month = stringDivisor[1].substring(3, 5);
                        String year = stringDivisor[1].substring(6, 10);
                        String fechaconv = day + "/" + month + "/" + year;

                        ParametroReport pr = new ParametroReport();
                        pr.setNombreParametro(stringDivisor[0]);
                        pr.setValorParametro(fechaconv);
                        lspreport.add(pr);
                    }
                } else {
                    log.warn("Invalid format for parts[" + i + "]: " + parts[i]);
                }
            }

            //Finalmente recorremos la Lista de Objetos
            for (ParametroReport item : lspreport) {
                log.info("item.getNombreParametro(): " + item.getNombreParametro());
                log.info("item.getValorParametro(): " + item.getValorParametro());
            }
        }

        if (codciax.equals("") || codciax == null || codciax == "") {
            log.info("codciax vacio");
        } else {
            Compania ciainfo = companiaService.getCompaniaAll(Integer.valueOf(codciax));
            log.info("ciainfo.getDescCia(): " + ciainfo.getDescCia());
            log.info("ciainfo.getUrlflgsource(): " + ciainfo.getUrlflgsource());
            log.info("ciainfo.getIexurlfileimg(): " + ciainfo.getIexurlfileimg());

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
                log.info("key_name: " + key_name);
                log.info("passPhrase: " + passPhrase);

                //######### VER LOGO AWS ##################################################################################
                if (accion.equals("verLogo")) {
                    log.info("#### AWS verLogo ####");

                    fileName = "img/" + fileurl;

                    credentials = new BasicAWSCredentials(key_name, passPhrase);
                    s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                    try {
                        S3Object o = s3.getObject(bucket_name, fileName);
                        log.info("Path: " + fileName);
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

                //######### VER FOTO EMPL AWS ########################################################################
                if (accion.equals("verFotoEmpl")) {
                    log.info("#### AWS verFotoEmpl ####");

                    pathfile = codciax + "/fotoemp/" + fileurl;

                    log.info("Lector URL Img:" + pathfile);

                    fileName = pathfile;
                    credentials = new BasicAWSCredentials(key_name, passPhrase);
                    s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                    Integer pospoint = fileurl.indexOf(".");
                    String extension = fileurl.substring(pospoint);
                    String extension2 = fileurl.substring(pospoint + 1);
                    log.info("Fileurl =" + fileurl);
                    log.info("Extension file =" + extension);

                    S3Object o = s3.getObject(bucket_name, fileName);
                    log.info("Path: " + fileName);
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

                //######### VER FOTO DERECHO HABIENTE AWS ########################################################################
                if (accion.equals("verFotoDerechoHab")) {
                    log.info("#### AWS verFotoDerechoHab ####");

                    pathfile = codciax + "/fotoderhab/" + idDerHabx + "/" + fileurl;

                    log.info("Lector URL Img:" + pathfile);

                    fileName = pathfile;
                    credentials = new BasicAWSCredentials(key_name, passPhrase);
                    s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                    Integer pospoint = fileurl.indexOf(".");
                    String extension = fileurl.substring(pospoint);
                    String extension2 = fileurl.substring(pospoint + 1);
                    log.info("Fileurl =" + fileurl);
                    log.info("Extension file =" + extension);

                    S3Object o = s3.getObject(bucket_name, fileName);
                    log.info("Path: " + fileName);
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

                //######### VER REPORTE EXCEL AWS ########################################################################
                if (accion.equals("verReporteExcel")) {
                    log.info("#### AWS verReporte Excel ####");

                    response.setHeader("Content-Disposition", "attachment; filename=" + nombreJasper + ".xls");

                    InputStream inputStream = null;

                    fileName = "reportes/" + nombreJasper + ".jasper";
                    log.info("Nombre Jasper: " + fileName);

                    credentials = new BasicAWSCredentials(key_name, passPhrase);
                    s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                    S3Object o = s3.getObject(bucket_name, fileName);
                    log.info("Path: " + fileName);

                    inputStream = o.getObjectContent();

                    //Parametros de Reporte
                    Map parametros = new HashMap();
                    parametros.put("P_CODCIA", Integer.valueOf(codciax));
                    parametros.put("P_CODTRA", -1);
                    parametros.put("SUBREPORT_DIR", request.getServletContext().getRealPath(""));
                    //Agregamos mas parametros al Reporte que vienen desde la url
                    if (lspreport.size() > 0) {
                        for (ParametroReport item : lspreport) {
                            log.info("item.getNombreParametro(): " + item.getNombreParametro());
                            log.info("item.getValorParametro(): " + item.getValorParametro());

                            parametros.put(item.getNombreParametro(), item.getValorParametro());
                        }
                    }

                    Connection conn = template.getDataSource().getConnection();

                    try {
                        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conn);

                        log.info("jasperPrint: " + jasperPrint.getName());

                        if (jasperPrint != null) {
                            log.info("Se encontró jasper");

                            JRXlsExporter exporter = new JRXlsExporter();
                            //JRProperties.setProperty("net.sf.jasperreports.default.font.name", "Sans Serif");
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

                //######### VER REPORTE PDF AWS ########################################################################
                if (accion.equals("verReportePDF")) {
                    log.info("#### AWS verReporte PDF ####");

                    String path = "";
                    String logo = "";
                    String fotoemp = "";

                    log.info("codciax:" + codciax);
                    log.info("idTrabx: " + idTrabx);

                    InputStream inputStreamfotoemp = null;
                    InputStream inputStreamlogo = null;
                    InputStream inputStreamRep = null;

                    if(idTrabx!=null || !idTrabx.equals("") || idTrabx!="") {
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
                    }

                    if(idTrabx!=null || !idTrabx.equals("") || idTrabx!="") {

                        fileName = codciax + "/fotoemp/" + fotoemp;
                        credentials = new BasicAWSCredentials(key_name, passPhrase);
                        S3Object o = null;
                        s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                        o = s3.getObject(bucket_name, fileName);
                        inputStreamfotoemp = o.getObjectContent();
                        log.info("Obtiene FotoEmpl Path: " + fileName);


                        AmazonS3 s4 = null;
                        S3Object o2 = null;
                        fileName = "img/" + logo;
                        s4 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                        o2 = s4.getObject(bucket_name, fileName);
                        inputStreamlogo = o2.getObjectContent();
                        log.info("Obtiene Logo Path: " + fileName);
                    }

                    AmazonS3 s5 = null;
                    S3Object o3 = null;
                    s5 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                    fileName = "reportes/" + nombreJasper + ".jasper";
                    o3 = s5.getObject(bucket_name, fileName);
                    inputStreamRep = o3.getObjectContent();
                    log.info("Obtiene Reporte jasper Path: " + fileName);

                    Map parametros = new HashMap();
                    parametros.put("P_CODCIA", Integer.valueOf(codciax));
                    parametros.put("P_CODTRA", Integer.parseInt(idTrabx));
                    parametros.put("SUBREPORT_DIR", request.getServletContext().getRealPath(""));

                    if(idTrabx!=null || !idTrabx.equals("") || idTrabx!="")  {
                        parametros.put("P_LOGO", inputStreamlogo);
                        parametros.put("P_FOTO", inputStreamfotoemp);
                    }

                    //Agregamos mas parametros al Reporte que vienen desde la url
                    if (lspreport.size() > 0) {
                        for (ParametroReport item : lspreport) {
                            log.info("item.getNombreParametro(): " + item.getNombreParametro());
                            log.info("item.getValorParametro(): " + item.getValorParametro());

                            parametros.put(item.getNombreParametro(), item.getValorParametro());
                        }
                    }

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

                //######### VER REPORTE EXCEL AWS ########################################################################
                if (accion.equals("decargarDocumento")) {
                    log.info("#### AWS decargarDocumento ####");

                    String Path=codciax+"/"+carpetaLecturax;
                    fileName = codciax+"_"+idGrpfileLegajox+"_"+idImageLegajox+".pdf";
                    String rutaCompleta=Path+"/"+fileName;
                    log.info("rutaCompleta: " + rutaCompleta);

                    credentials = new BasicAWSCredentials(key_name, passPhrase);
                    s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                    S3Object o = s3.getObject(bucket_name, rutaCompleta);
                    log.info("Path: " + rutaCompleta);

                    InputStream inputStream1 = new BufferedInputStream(o.getObjectContent());
                    log.info("inputStream1: "+inputStream1);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();

                    byte[] buf = new byte[1024];
                    int n = 0;

                    while (-1 != (n = inputStream1.read(buf))) {
                        out.write(buf, 0, n);
                    }

                    out.close();
                    inputStream1.close();
                    byte[] bytesresponse = out.toByteArray();
                    //FileOutputStream fos = new FileOutputStream("C:/Users/HP/Downloads/hola.pdf");

                    response.setHeader("Content-Disposition", "attachment; filename="+fileName);
                    response.setHeader("Cache-Control", "no-store");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                    response.setContentType("application/" + "pdf");
                    ServletOutputStream responseOutputStream = response.getOutputStream();
                    responseOutputStream.write(bytesresponse);
                    responseOutputStream.flush();
                    responseOutputStream.close();
                }

                //######### ELIMINAR DOCUMENTO AWS ###########################################################################
                if (accion.equals("eliminarDocumento")) {
                    log.info("#### AWS eliminarDocumento ####");

                    String Path=codciax+"/"+carpetaLecturax;
                    fileName = codciax+"_"+idGrpfileLegajox+"_"+idImageLegajox+".pdf";
                    String rutaCompleta=Path+"/"+fileName;
                    log.info("rutaCompleta: " + rutaCompleta);

                    credentials = new BasicAWSCredentials(key_name, passPhrase);
                    s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                    //S3Object o = s3.getObject(bucket_name, rutaCompleta);
                    log.info("Path: " + rutaCompleta);

                    s3.deleteObject(new DeleteObjectRequest(bucket_name, rutaCompleta));

                    //Elimina en base datos solo para legajos
                    if(carpetaLecturax.equals("legajo")){
                        log.info("######### Elimina en tabla caso es legajo #######");

                        FileImageLegajo img12  = new FileImageLegajo();
                        img12.setIexcodcia(Integer.valueOf(codciax));
                        img12.setIexcodgrpfile(Integer.valueOf(idGrpfileLegajox));
                        img12.setIexcodimage(Integer.valueOf(idImageLegajox));

                        legajoService.eliminarImage(img12);

                        return new ModelAndView("redirect:/buscarLegajoAtras@"+idTrabx+"@"+idGrpfileLegajox);
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
                System.out.println("   FTP Connection");
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

                //######### VER LOGO FTP ##################################################################################
                if (accion.equals("verLogo")) {
                    log.info("#### FTP verLogo ####");

                    try {
                        FTPClient ftpClient = new FTPClient();
                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.setFileType(2);

                        String remoteFile = "/img/" + fileurl;
                        OutputStream outputStream = response.getOutputStream();
                        InputStream inputStream = ftpClient.retrieveFileStream(remoteFile);
                        log.info("Path: " + remoteFile);

                        byte[] bytesArray = new byte[4096];
                        int bytesRead = -1;

                        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                            outputStream.write(bytesArray, 0, bytesRead);
                        }

                        log.info("File # has been downloaded successfully!");

                        outputStream.close();
                        inputStream.close();
                    } catch (IOException e) {
                        log.info(e.getMessage());
                    }
                }

                //######### VER FOTO EMPL FTP ########################################################################
                if (accion.equals("verFotoEmpl")) {
                    log.info("#### FTP verFotoEmpl ####");

                    try {
                        FTPClient ftpClient = new FTPClient();
                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.setFileType(2);

                        //String remoteFile = "/img/" + fileurl;
                        String remoteFile = codciax + "/fotoemp/" + fileurl;
                        OutputStream outputStream = response.getOutputStream();
                        InputStream inputStream = ftpClient.retrieveFileStream(remoteFile);
                        log.info("Path: " + remoteFile);

                        byte[] bytesArray = new byte[4096];
                        int bytesRead = -1;

                        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                            outputStream.write(bytesArray, 0, bytesRead);
                        }

                        log.info("File # has been downloaded successfully!");

                        outputStream.close();
                        inputStream.close();
                    } catch (IOException e) {
                        log.info(e.getMessage());
                    }

                }

                //######### VER FOTO DERECHO HABIENTE FTP ########################################################################
                if (accion.equals("verFotoDerechoHab")) {
                    log.info("#### FTP verFotoDerechoHab ####");

                    try {
                        FTPClient ftpClient = new FTPClient();
                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.setFileType(2);

                        String remoteFile = "/" + codciax + "/fotoderhab/" + idDerHabx + "/" + fileurl;
                        OutputStream outputStream = response.getOutputStream();
                        InputStream inputStream = ftpClient.retrieveFileStream(remoteFile);
                        log.info("Path: " + remoteFile);

                        byte[] bytesArray = new byte[4096];
                        int bytesRead = -1;

                        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                            outputStream.write(bytesArray, 0, bytesRead);
                        }

                        log.info("File # has been downloaded successfully!");

                        outputStream.close();
                        inputStream.close();
                    } catch (IOException e) {
                        log.info(e.getMessage());
                    }

                }

                //######### VER REPORTE EXCEL FTP ########################################################################
                if (accion.equals("verReporteExcel")) {
                    log.info("#### FTP verReporte Excel ####");

                    FTPClient ftpClient = new FTPClient();
                    ftpClient.connect(server, port);
                    ftpClient.login(user, pass);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(2);

                    response.setHeader("Content-Disposition", "attachment; filename=" + nombreJasper + ".xls");

                    String remoteFile = "/reportes/" + nombreJasper + ".jasper";
                    OutputStream outputStream = response.getOutputStream();
                    InputStream inputStream = ftpClient.retrieveFileStream(remoteFile);
                    log.info("Path: " + remoteFile);

                    //Parametros de Reporte
                    Map parametros = new HashMap();
                    parametros.put("P_CODCIA", Integer.valueOf(codciax));
                    parametros.put("P_CODTRA", -1);
                    parametros.put("SUBREPORT_DIR", request.getServletContext().getRealPath(""));
                    //Agregamos mas parametros al Reporte que vienen desde la url
                    if (lspreport.size() > 0) {
                        for (ParametroReport item : lspreport) {
                            log.info("item.getNombreParametro(): " + item.getNombreParametro());
                            log.info("item.getValorParametro(): " + item.getValorParametro());

                            parametros.put(item.getNombreParametro(), item.getValorParametro());
                        }
                    }

                    Connection conn = template.getDataSource().getConnection();

                    try {
                        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conn);

                        log.info("jasperPrint: " + jasperPrint.getName());

                        if (jasperPrint != null) {
                            log.info("Se encontró jasper");

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

                    outputStream.close();
                    inputStream.close();
                }

                //######### VER REPORTE PDF FTP ########################################################################
                if (accion.equals("verReportePDF")) {
                    log.info("#### FTP verReporte PDF ####");

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

                    log.info("logo: " + logo);

                    if (empleado.getIexlogo() == null || empleado.getIexlogo().equals("")) {
                        fotoemp = "fotoemp.png";
                    } else {
                        fotoemp = empleado.getIexlogo();
                    }

                    InputStream inputStreamfotoemp = null;
                    InputStream inputStreamlogo = null;
                    InputStream inputStreamRep = null;

                    try {
                        FTPClient ftpClient = new FTPClient();
                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                        String remoteFile = "/" + codciax + "/fotoemp/" + fotoemp;
                        inputStreamfotoemp = ftpClient.retrieveFileStream(remoteFile);
                        log.info("Obtiene FotoEmpl Path: " + remoteFile);

                        FTPClient ftpClient2 = new FTPClient();
                        ftpClient2.connect(server, port);
                        ftpClient2.login(user, pass);
                        ftpClient2.enterLocalPassiveMode();
                        ftpClient2.setFileType(FTP.BINARY_FILE_TYPE);
                        String remoteFile2 = "/img/" + logo;
                        inputStreamlogo = ftpClient2.retrieveFileStream(remoteFile2);
                        log.info("Obtiene Logo Path: " + remoteFile2);

                        FTPClient ftpClient3 = new FTPClient();
                        ftpClient3.connect(server, port);
                        ftpClient3.login(user, pass);
                        ftpClient3.enterLocalPassiveMode();
                        ftpClient3.setFileType(FTP.BINARY_FILE_TYPE);
                        String remoteFile3 = "/reportes/" + nombreJasper + ".jasper";
                        inputStreamRep = ftpClient3.retrieveFileStream(remoteFile3);
                        log.info("Obtiene Reporte jasper Path: " + remoteFile3);

                        Map parametros = new HashMap();
                        parametros.put("P_CODCIA", Integer.valueOf(codciax));
                        parametros.put("P_CODTRA", Integer.parseInt(idTrabx));
                        parametros.put("SUBREPORT_DIR", request.getServletContext().getRealPath(""));
                        parametros.put("P_LOGO", inputStreamlogo);
                        parametros.put("P_FOTO", inputStreamfotoemp);

                        Connection conn = template.getDataSource().getConnection();
                        OutputStream out = response.getOutputStream();

                        JasperReport reporte = (JasperReport) JRLoader.loadObject(inputStreamRep);
                        byte[] bytes = JasperRunManager.runReportToPdf(reporte, parametros, conn);

                        int len = bytes.length;
                        response.setContentLength(len);
                        out.write(bytes, 0, len);
                        out.flush();

                        inputStreamfotoemp.close();
                        inputStreamlogo.close();
                        inputStreamRep.close();
                    } catch (IOException e) {
                        log.info(e.getMessage());
                    }
                }
            }
        }

        return null;
    }

}

