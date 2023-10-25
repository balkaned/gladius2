package com.balkaned.gladius;

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
import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.EmpleadoService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.System.out;

@RestController
public class IndexController {
    static Logger logger = Logger.getLogger(IndexController.class.getName());

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    EmpleadoService empleadoService;

    private static final String UPLOAD_DIRECTORY = "C:\\gladius";
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @RequestMapping("/login2")
    public ModelAndView login(ModelMap model, HttpServletRequest request) {

        UsuarioConeccion uc = new UsuarioConeccion();
        model.addAttribute("usuarioConeccion", uc);
        String tip = "";

        if (request.getSession().getAttribute("tiposession") == null) {
            tip = "0";
        } else {

            if (request.getSession().getAttribute("tiposession").equals("2")) {
                model.addAttribute("mensaje", "Contraseña Erronea");
            }

            if (request.getSession().getAttribute("tiposession").equals("3")) {
                model.addAttribute("mensaje", "Usuario o Contraseña Incorrecta");
            }

            if (request.getSession().getAttribute("tiposession").equals("4")) {
                model.addAttribute("mensaje", "No se puede Ingresar Campos Vacíos, por favor ingrese Usuario y Contraseña");
            }

            if (request.getSession().getAttribute("tiposession").equals("5")) {
                model.addAttribute("mensaje", "Alerta!, Hemos detectado que no es el Admnistrador de la aplicación. "
                        + "Este software es un producto Licenciado y Registrado en Indecopi  Copyright© 2023 Balkaned www.balkaned.com Todos los derechos reservados. Derechos de autor. Todos los contenidos de este Sitio No se va a poder "
                        + "instalar puede conectar con Base de Datos Postgres(por defecto), Mysql, SQl Server, revise que los parámetros de conexión en la aplicación "
                        + "para el archivo de configuración application.properties coincidan con la configuración del motor de base Datos, "
                        + "puede que la base de datos no este aún restaurada o el proceso de restauración falló, "
                        + "no olvide copiar el Driver de Conexión Ojo! Postgres com.mysql.jdbc_5.1.5.jar con extensión jar, en la carpeta del Tomcat "
                        + "C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Gladius\\WEB-INF\\lib\\ "
                        + "luego debe Parar y Volver a Desplegar el WAR, esta aplicación esta configurada para Apache Tomcat v7, en caso el problema persista consulte con el administrador del "
                        + "sistema balkanedperu@gmail.com.");
            }
        }

        return new ModelAndView("public/login2a");
    }

    @RequestMapping("/verificarLogin2")
    public ModelAndView verificarLogin2(ModelMap model, HttpServletRequest request, @ModelAttribute("usuarioConeccion") UsuarioConeccion uc, BindingResult result, SessionStatus status) {

        UsuarioConeccion uc2 = usuarioConeccionService.obtenerUsuarioConeccionByName(uc);

        if (uc2.getUser().equals("sinbd")) {
            request.getSession().setAttribute("tiposession", "5");

            return new ModelAndView("redirect:/login2");
        } else {
            if (uc.getUser().equals("")) {
                out.println("Campos Vacios");
                request.getSession().setAttribute("tiposession", "4");
                //model.addAttribute("mensaje","Contraseña Erronea");

                return new ModelAndView("redirect:/login2");
            } else {
                if (uc2.getUser().equals("noecontrado")) {
                    logger.info("Usuario o Contraseña Incorrecta");
                    request.getSession().setAttribute("tiposession", "3");
                    //model.addAttribute("mensaje","Usuario o Contraseña Incorrecta");

                    return new ModelAndView("redirect:/login2");
                } else {
                    if (uc2.getPass().equals(uc.getPass())) {

                        UsuarioConeccion uc3 = usuarioConeccionService.obtenerUsuarioConeccionById(uc2.getId_usuario());

                        logger.info("ID_Usuario: " + uc2.getId_usuario());

                        char firstCharacter = uc3.getUser().charAt(0);
                        char char1UpperCase = Character.toUpperCase(firstCharacter);
                        String cast1 = String.valueOf(char1UpperCase);

                        String nombre = uc3.getUser();
                        String resultado = nombre.toUpperCase().charAt(0) + nombre.substring(1, nombre.length()).toLowerCase();

                        //###### SETEAMOS VARIABLES DE SESION###########
                        request.getSession().setAttribute("user", resultado);
                        request.getSession().setAttribute("idUser", uc3.getId_usuario());
                        request.getSession().setAttribute("email", uc3.getEmail());
                        request.getSession().setAttribute("firstCharacter", cast1);
                        request.getSession().setAttribute("idCompania", uc3.getCodCia());
                        request.getSession().setAttribute("tiposession", "1");
                        request.getSession().setAttribute("nombrecomp", uc3.getDesCia());
                        request.getSession().setAttribute("ruccomp", uc3.getRuccia());

                        String sqlURL = "jdbc:postgresql://ec2-18-191-189-102.us-east-2.compute.amazonaws.com:5432/" + uc3.getSourceDes();

                        String redirect = "redirect:/selcompanias@" + uc3.getId_usuario();
                        return new ModelAndView(redirect);
                    } else {
                        logger.info("Contraseña Erronea: ");
                        request.getSession().setAttribute("tiposession", "2");
                        //model.addAttribute("mensaje","Contraseña Erronea");

                        return new ModelAndView("redirect:/login2");
                    }
                }
            }
        }
    }


    @RequestMapping(value = "/selcompanias@{idUser}", method = RequestMethod.GET)
    public ModelAndView selcompanias(ModelMap model, HttpServletRequest request, @PathVariable String idUser) {

        logger.info("idUser: " + idUser);
        List<Compania> companiaList = usuarioConeccionService.listarCompaniasBycodUsu(idUser);

        model.addAttribute("compList", companiaList);
        model.addAttribute("schema", "dark");

        return new ModelAndView("public/ecompanias");
    }

    @RequestMapping(value = "/selcompaniasChange@{idUser}", method = RequestMethod.GET)
    public ModelAndView selcompaniasChange(ModelMap model, HttpServletRequest request, @PathVariable String idUser) {

        logger.info("idUser: " + idUser);
        List<Compania> companiaList = usuarioConeccionService.listarCompaniasBycodUsu(idUser);

        model.addAttribute("compList", companiaList);
        model.addAttribute("schema", "dark");

        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("firstCharacter", null);
        request.getSession().setAttribute("idCompania", null);
        request.getSession().setAttribute("nombrecomp", null);
        request.getSession().setAttribute("ruccomp", null);

        return new ModelAndView("public/ecompanias");
    }

    @RequestMapping("/logoff")
    public ModelAndView logoff(ModelMap model, HttpServletRequest request) {

        //model.addAttribute("usuario", new Usuario());

        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("idUser", null);
        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("firstCharacter", null);
        request.getSession().setAttribute("idCompania", null);
        request.getSession().setAttribute("nombrecomp", null);
        request.getSession().setAttribute("ruccomp", null);

        return new ModelAndView("redirect:/login2");
    }

    @RequestMapping("/home@{idComp}@{idUser}")
    public ModelAndView home(ModelMap model, HttpServletRequest request, @PathVariable String idComp, @PathVariable String idUser) {

        logger.info("idComp: " + idComp);
        logger.info("idUser: " + idUser);

        UsuarioConeccion uc1 = usuarioConeccionService.obtenerUsuarioConeccionById(idUser);
        Compania comp1 = companiaService.getCompaniaAll(Integer.parseInt(idComp));

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");

        request.getSession().setAttribute("idCompania", comp1.getIdCodcia());
        request.getSession().setAttribute("urlLogo", comp1.getUrlLogo());
        request.getSession().setAttribute("nombrecomp", comp1.getDescCia());
        request.getSession().setAttribute("ruccomp", comp1.getNroRuc());
        request.getSession().setAttribute("schema", comp1.getSchema());

        model.addAttribute("idComp", idComp);
        model.addAttribute("urlLogo", comp1.getUrlLogo());
        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", comp1.getDescCia());
        model.addAttribute("rucComp", comp1.getNroRuc());
        model.addAttribute("schema", comp1.getSchema());

        return new ModelAndView("public/kanban");
    }

    @RequestMapping("/verFoto@{accion2}@{idComp}@{urlLogo}@{idDer}")
    public ModelAndView verFoto(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                @PathVariable String accion2,
                                @PathVariable String idComp,
                                @PathVariable String urlLogo,
                                @PathVariable String idDer) throws IOException {
        logger.info("/verFoto");

        logger.info("accion2: " + accion2);
        String accion = accion2;
        String codciax = idComp;
        String fileurl = urlLogo;
        String idDer2 = idDer;

        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        // response.setContentType("application/octet-stream");

        //String fileurl = (String)request.getParameter("img");
        HttpSession session = request.getSession(true);
        String pathfile = "";
        //String accion = (String)request.getParameter("accion");
        //String codciax = (String)request.getParameter("codciax");

        String server = "ftp.balkaned.com";
        int port = 21;
        String user = "ebaldeon@balkaned.com";
        String pass = "@Kekereke1984";
        // String idimg = (String)request.getParameter("idimg");
        File tmp = null;

        Integer outrep = 1;
        // ftp code  FTPClient ftpClient = new FTPClient();

        System.out.println("test");
        //AmazonS3 s3 = new AmazonS3Client(new PropertiesCredentials(
        //     S3Sample.class.getResourceAsStream("AwsCredentials.properties")));
        byte[] salt = {
                (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
                (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03
        };

        //String bucketName = "my-first-s3-bucket-" + UUID.randomUUID();
        //String key = "MyObjectKey";

        System.out.println("===========================================");
        System.out.println("Getting Started with Amazon S3");
        System.out.println("===========================================\n");

        /*
        Regions clientRegion = Regions.US_EAST_2;
        String bucket_name = "gladiusfileserver";
        String key_name ="AKIAQWQ2VFTRCSCFOZW5";
        String passPhrase = "2QUcAxspuoSXonhItKr5SGntESeh2qymzm0aCQVE";
        */

        Regions clientRegion = null;
        String bucket_name = "";
        String key_name = "";
        String passPhrase = "";

        String fileName = "";
        AWSCredentials credentials = null;
        AmazonS3 s3 = null;

        Compania ciainfo = companiaService.getCompaniaAll(Integer.parseInt(codciax));
        //Compania ciainfo= daocia.getCompaniaAll(Integer.parseInt(codciax));

        if (accion.equals("LOGO")) {
            outrep = 0;

            //    pathfile = (String)session.getAttribute("GLADIUS_IMG")+fileurl;

            // upload file to folder and set it to public
            fileName = "img/" + fileurl;
            ;

            /// System.out.format("Downloading %s from S3 bucket %s...\n", key_name, bucket_name);
            //  AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegionalUsEast1EndpointEnabled(Boolean.FALSE).credentialsProvider(credentials).build();
            //AmazonS3 s3 = new AmazonS3Client(new PropertiesCredentials(awsCreds ));
            //   AmazonS3Client s3 = new AmazonS3Client(awsCreds);

            if (ciainfo.getUrlflgsource().equals("1")) {
                //clientRegion = Regions.US_EAST_2;
                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());

                System.out.format("Entro a la CIAINFO");
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name = ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();
                // Regionname = ciainfo.getIexregiondes().trim();

                logger.info("clientRegion: " + clientRegion);
                logger.info("bucket_name: " + bucket_name);
                logger.info("key_name: " + clientRegion);
                logger.info("passPhrase: " + passPhrase);

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
                    //    Logger.getLogger(AmazonS3DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.format("Fallo");
                }

            } else if (ciainfo.getUrlflgsource().equals("2")) {

                String path_img = ciainfo.getIexurlfileimg();

                //pathfile = (String)session.getAttribute("GLADIUS_IMG")+fileurl;
                pathfile = path_img + fileurl;

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
            }
        } else if (accion.equals("FOTOEMP")) {

            // pathfile = (String)session.getAttribute("GLADIUS_FILE")+"fotoemp/"+fileurl;
            outrep = 0;
            pathfile = codciax + "/fotoemp/" + fileurl;

            logger.info("######LECTOR URL IMG:####### :" + pathfile);

            if (ciainfo.getUrlflgsource().equals("1")) {

                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());

                System.out.format("Entro a la CIAINFO");
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name = ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();
                // Regionname = ciainfo.getIexregiondes().trim();

                fileName = pathfile;
                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                Integer pospoint = fileurl.indexOf(".");
                String extension = fileurl.substring(pospoint);
                String extension2 = fileurl.substring(pospoint + 1);
                System.out.println("Fileurl =" + fileurl);
                System.out.println("Extension file =" + extension);

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
            } else if (ciainfo.getUrlflgsource().equals("2")) {

                String path_img = ciainfo.getIexurlfileserver();

                //pathfile = (String)session.getAttribute("GLADIUS_IMG")+fileurl;
                pathfile = path_img + codciax + "/fotoemp/" + fileurl;

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

            }
        } else if (accion.equals("FOTODER")) {

            // pathfile = (String)session.getAttribute("GLADIUS_FILE")+"fotoemp/"+fileurl;
            outrep = 0;
            pathfile = codciax + "/fotoderhab/" + idDer2 + "/" + fileurl;

            logger.info("######LECTOR URL IMG:####### :" + pathfile);

            if (ciainfo.getUrlflgsource().equals("1")) {

                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());

                System.out.format("Entro a la CIAINFO");
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name = ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();
                // Regionname = ciainfo.getIexregiondes().trim();

                fileName = pathfile;
                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                Integer pospoint = fileurl.indexOf(".");
                String extension = fileurl.substring(pospoint);
                String extension2 = fileurl.substring(pospoint + 1);
                System.out.println("Fileurl =" + fileurl);
                System.out.println("Extension file =" + extension);

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
            } else if (ciainfo.getUrlflgsource().equals("2")) {

                String path_img = ciainfo.getIexurlfileserver();

                //pathfile = (String)session.getAttribute("GLADIUS_IMG")+fileurl;
                pathfile = path_img + codciax + "/fotoderhab/" + idDer2 + "/" + fileurl;

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

            }
        } else if (accion.equals("FOTOORG")) {
            if (ciainfo.getUrlflgsource().equals("2")) {

                pathfile = (String) session.getAttribute("GLADIUS_FILE") + fileurl;

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

            }
        } else if (accion.equals("FOTOLEG")) {

            // pathfile = (String)session.getAttribute("GLADIUS_FILE")+"fotoemp/"+fileurl;

            Integer pos = fileurl.indexOf("pdf");

            System.out.format("posicion pdf : " + pos);

            outrep = 0;
            pathfile = codciax + "/legajo/" + fileurl;

            if (ciainfo.getUrlflgsource().equals("1")) {

                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());

                System.out.format("Entro a la CIAINFO");
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name = ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();
                // Regionname = ciainfo.getIexregiondes().trim();

                fileName = pathfile;
                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

                Integer pospoint = fileurl.indexOf(".");
                String extension = fileurl.substring(pospoint);
                String extension2 = fileurl.substring(pospoint + 1);
                System.out.println("Fileurl =" + fileurl);
                System.out.println("Extension file =" + extension);

                S3Object o = s3.getObject(bucket_name, fileName);
                S3ObjectInputStream s3is = o.getObjectContent();

                //response.setContentType("image/"+extension2);

                if (pos > 0) {
                    System.out.println("Fileurl =" + fileurl);
                    // response.setContentType("application/pdf;charset=UTF-8");
                    response.setContentType("text/html;charset=UTF-8");

                    InputStream is = null;
                    OutputStream os = null;

                    is = s3is;
                    // Copying object

                    long fileLength = o.getObjectMetadata().getContentLength();

                    try {
                        /*Send file direct back in response*/
                        os = response.getOutputStream();
                        response.setContentLength((int) fileLength);
                        response.setHeader("Content-Disposition", "attachment; filename = "
                                + fileurl);

                        final String bufSize = "4096";

                        final byte[] buffer = new byte[Integer.parseInt(bufSize)];
                        int len = is.read(buffer, 0, buffer.length);

                        while (len != -1) {
                            os.write(buffer, 0, len);
                            len = is.read(buffer, 0, buffer.length);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (o != null) {
                                o.close();
                            }

                            if (is != null) {
                                is.close();
                            }

                            if (os != null) {
                                os.close();
                            }

                            if (response != null) {
                                response.getOutputStream().close();
                            }

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                          /* try (final S3Object s3Object = o;
                             final InputStreamReader streamReader = new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.UTF_8);
                             final BufferedReader reader = new BufferedReader(streamReader)) {

                          //reader.flush();
                                 String s = null;
                                    while ((s = reader.readLine()) != null)
                                    {
                                    //    System.out.println(s);
                                        //your business logic here
                                    }


                          //  return reader.lines().collect(Collectors.toSet());
                        } catch (final IOException e) {
                            System.out.println("Fileurl ="+fileurl);
                           // return Collections.emptySet();
                            }*/


                    //**   InputStream reader = new BufferedInputStream(o.getObjectContent());
                    //**     File file = new File("localFilename");

                    // s3.getObject(new GetObjectRequest(bucket_name, fileName), file);
                    //**    tmp = File.createTempFile("s3test", extension);
                    //**    file = tmp;
                    //    Files.copy(s3is, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    //       ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

                                          /*  try {
                                              BufferedImage image = ImageIO.read(tmp);
                                              ImageIO.write(image, extension2, jpegOutputStream);
                                            } catch (IllegalArgumentException e) {
                                              response.sendError(HttpServletResponse.SC_NOT_FOUND);
                                            }*/

                    //**    OutputStream writer = new BufferedOutputStream(new FileOutputStream(file));

                    //   Writer writer = new OutputStreamWriter(new FileOutputStream(file));

                    //**     int read = -1;

                    //**     while ( ( read = reader.read() ) != -1 ) {
                    //**         writer.write(read);
                    //**      }

                    //**writer.flush();
                    //**writer.close();
                    //**reader.close();
                } else {
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
            } else if (ciainfo.getUrlflgsource().equals("2")) {

                String path_img = ciainfo.getIexurlfileserver();

                //pathfile = (String)session.getAttribute("GLADIUS_IMG")+fileurl;
                pathfile = path_img + codciax + "/fotoemp/" + fileurl;

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
            }
        }

        return null;
    }

    @SneakyThrows
    @RequestMapping(value = "/fileUploadServlet@{idTrab}@{idComp}", method = RequestMethod.POST)
    public ModelAndView fileUploadServlet(
            ModelMap model,
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String idTrab,
            @PathVariable String idComp,
            @RequestParam("uploadFile") MultipartFile uploadFile
    ) {
        logger.info("/fileUploadServlet");

        String idimg = request.getParameter("idimg");
        String codciax = request.getParameter("codciax");
        String idTrab2 = request.getParameter("idTrab");
        String accion = request.getParameter("accion");
        String idDer = request.getParameter("idDer");
        logger.info("idTrab: " + idTrab);
        logger.info("idComp: " + idComp);

        if (accion.equals("FOTOEMP")) {
            logger.info("Accion: FOTOEMP");

            Integer codciaxRecup = Integer.valueOf(idComp);
            //      String codciax= String.valueOf(codciaxRecup);

            Empleado emp = empleadoService.recuperarCabecera(codciaxRecup, Integer.parseInt(idTrab));

            //      String accion = "";
            //      String idimg = "";
            String filePath = "";
            String filelink = "";
            String target = "";

            System.out.println("Accion :" + accion);
            // checks if the request actually contains upload file

            String server = "ftp.balkaned.com";
            int port = 21;
            String user = "ebaldeon@balkaned.com";
            String pass = "@Kekereke1984";

            /*if (!ServletFileUpload.isMultipartContent(request)) {
               // if not, we stop here
                PrintWriter writer = response.getWriter();
                writer.println("Error: Form must has enctype=multipart/form-data.");
                System.out.println("No es multipart");
                writer.flush();
               return null;
            }*/

            // configures upload settings
            //DiskFileItemFactory factory = new DiskFileItemFactory();
            // sets memory threshold - beyond which files are stored in disk
            //factory.setSizeThreshold(MEMORY_THRESHOLD);
            // sets temporary location to store files
            //factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            //ServletFileUpload upload = new ServletFileUpload((FileItemFactory) factory);
            //ServletFileUpload upload = new ServletFileUpload(factory);
            // sets maximum size of upload file
            //upload.setFileSizeMax(MAX_FILE_SIZE);
            // sets maximum size of request (include file + form data)
            //upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            // this path is relative to application's directory
            /* String uploadPath = getServletContext().getRealPath("")
            + File.separator + UPLOAD_DIRECTORY;  */
            String uploadPath = "";

            //  amazon s3...
            Regions clientRegion = null;
            String bucket_name = "";
            String key_name = "";
            String passPhrase = "";

            AWSCredentials credentials = null;
            AmazonS3 s3 = null;
            S3Object o = null;
            String nomeArquivo = "";
            String fileName2 = "";
            //String codciax ="";

            // fin de variables de Amazon s3
            // creates the directory if it does not exist
            /*no
             File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                 System.out.println("No existe direccion");
                uploadDir.mkdir();
            }
            */
            System.out.println("Direccion existe");

            // parses the request's content to extract file data
            //@SuppressWarnings("unchecked")

            System.out.println("request: " + request);
            logger.info("request: " + request);

            //          final CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) uploadFile;
            //          final CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) request.getPart("uploadFile");
            //var file = uploadFile;
            //List<FileItem> formItems = upload.parseRequest(request);

            //System.out.println("formItems: "+formItems);
            //logger.info("formItems: "+formItems);
            //if (formItems != null && formItems.size() > 0) {

            Compania ciainfo = null;

            ciainfo = companiaService.getCompaniaAll(Integer.parseInt(codciax));

            clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
            bucket_name = ciainfo.getIexsourcedes().trim();
            key_name = ciainfo.getIexususource().trim();
            passPhrase = ciainfo.getIexpasssource().trim();

            logger.info("ciainfo.getIexsourcedes().trim(): " + ciainfo.getIexsourcedes().trim());

            try {
                //nomeArquivo=(Integer)session.getAttribute("codcia")+"/fotoemp/"+idimg+"."+FilenameUtils.getExtension(item.getName());
                //nomeArquivo=codciax+"/fotoemp/"+idimg+"."+FilenameUtils.getExtension(item.getName());
                nomeArquivo = codciax + "/fotoemp/" + idimg + "." + "jpg";
                logger.info("nomeArquivo: " + nomeArquivo);

                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                //s3.putObject(bucket_name, nomeArquivo, item.getName());
                s3.putObject(bucket_name, nomeArquivo, nomeArquivo);

                //PutObjectRequest request2 = new PutObjectRequest(bucket_name, nomeArquivo, new File(item.getName()));
                //InputStream in=item.getInputStream();
                InputStream in = uploadFile.getInputStream();
                File tmp = null;
                tmp = File.createTempFile("s3test", ".jpeg");
                Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);

                PutObjectRequest request2 = new PutObjectRequest(bucket_name, nomeArquivo, tmp);

                ObjectMetadata metadata = new ObjectMetadata();
                //metadata.setContentType(item.getContentType());
                metadata.setContentType(uploadFile.getContentType());
                //metadata.addUserMetadata("title", "someTitle");
                request2.setMetadata(metadata);
                s3.putObject(request2);

                System.out.println("Arquivo escrito: " + nomeArquivo);
                //emp.setIexlogo(idimg+"."+FilenameUtils.getExtension(item.getName()));
                emp.setIexlogo(idimg + "." + "jpg");
                //dao.actualizarFoto(emp);
                empleadoService.actualizarFoto(emp);
            } catch (final Exception e) {
                System.out.println("You failed to upload " + nomeArquivo + " => " + e.getMessage());
            }
        } else if (accion.equals("FOTODER")) {
            logger.info("Accion: FOTODER");
            Integer codciaxRecup = Integer.valueOf(idComp);
            //      String codciax= String.valueOf(codciaxRecup);

            Empleado emp = empleadoService.recuperarCabecera(codciaxRecup, Integer.parseInt(idTrab));

            //      String accion = "";
            //      String idimg = "";
            String filePath = "";
            String filelink = "";
            String target = "";

            System.out.println("Accion :" + accion);
            // checks if the request actually contains upload file

            String server = "ftp.balkaned.com";
            int port = 21;
            String user = "ebaldeon@balkaned.com";
            String pass = "@Kekereke1984";

            /*if (!ServletFileUpload.isMultipartContent(request)) {
               // if not, we stop here
                PrintWriter writer = response.getWriter();
                writer.println("Error: Form must has enctype=multipart/form-data.");
                System.out.println("No es multipart");
                writer.flush();
               return null;
            }*/

            // configures upload settings
            //DiskFileItemFactory factory = new DiskFileItemFactory();
            // sets memory threshold - beyond which files are stored in disk
            //factory.setSizeThreshold(MEMORY_THRESHOLD);
            // sets temporary location to store files
            //factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            //ServletFileUpload upload = new ServletFileUpload((FileItemFactory) factory);
            //ServletFileUpload upload = new ServletFileUpload(factory);
            // sets maximum size of upload file
            //upload.setFileSizeMax(MAX_FILE_SIZE);
            // sets maximum size of request (include file + form data)
            //upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            // this path is relative to application's directory
            /* String uploadPath = getServletContext().getRealPath("")
            + File.separator + UPLOAD_DIRECTORY;  */
            String uploadPath = "";

            //  amazon s3...
            Regions clientRegion = null;
            String bucket_name = "";
            String key_name = "";
            String passPhrase = "";

            AWSCredentials credentials = null;
            AmazonS3 s3 = null;
            S3Object o = null;
            String nomeArquivo = "";
            String fileName2 = "";
            //String codciax ="";

            // fin de variables de Amazon s3
            // creates the directory if it does not exist
            /*no
             File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                 System.out.println("No existe direccion");
                uploadDir.mkdir();
            }
            */
            System.out.println("Direccion existe");

            // parses the request's content to extract file data
            //@SuppressWarnings("unchecked")

            System.out.println("request: " + request);
            logger.info("request: " + request);

            //          final CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) uploadFile;
            //          final CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) request.getPart("uploadFile");
            //var file = uploadFile;
            //List<FileItem> formItems = upload.parseRequest(request);

            //System.out.println("formItems: "+formItems);
            //logger.info("formItems: "+formItems);
            //if (formItems != null && formItems.size() > 0) {

            Compania ciainfo = null;

            ciainfo = companiaService.getCompaniaAll(Integer.parseInt(codciax));

            clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
            bucket_name = ciainfo.getIexsourcedes().trim();
            key_name = ciainfo.getIexususource().trim();
            passPhrase = ciainfo.getIexpasssource().trim();

            logger.info("ciainfo.getIexsourcedes().trim(): " + ciainfo.getIexsourcedes().trim());

            try {
                //nomeArquivo=(Integer)session.getAttribute("codcia")+"/fotoemp/"+idimg+"."+FilenameUtils.getExtension(item.getName());
                //nomeArquivo=codciax+"/fotoemp/"+idimg+"."+FilenameUtils.getExtension(item.getName());
                nomeArquivo = codciax + "/fotoderhab/" + idDer + "/" + idimg + "." + "jpg";
                logger.info("nomeArquivo: " + nomeArquivo);

                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                //s3.putObject(bucket_name, nomeArquivo, item.getName());
                s3.putObject(bucket_name, nomeArquivo, nomeArquivo);

                //PutObjectRequest request2 = new PutObjectRequest(bucket_name, nomeArquivo, new File(item.getName()));
                //InputStream in=item.getInputStream();
                InputStream in = uploadFile.getInputStream();
                File tmp = null;
                tmp = File.createTempFile("s3test", ".jpeg");
                Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);

                PutObjectRequest request2 = new PutObjectRequest(bucket_name, nomeArquivo, tmp);

                ObjectMetadata metadata = new ObjectMetadata();
                //metadata.setContentType(item.getContentType());
                metadata.setContentType(uploadFile.getContentType());
                //metadata.addUserMetadata("title", "someTitle");
                request2.setMetadata(metadata);
                s3.putObject(request2);

                System.out.println("Arquivo escrito: " + nomeArquivo);
                //emp.setIexlogo(idimg+"."+FilenameUtils.getExtension(item.getName()));
                emp.setIexlogo(idimg + "." + "jpg");
                //dao.actualizarFoto(emp);
                empleadoService.actualizarFoto(emp);
            } catch (final Exception e) {
                System.out.println("You failed to upload " + nomeArquivo + " => " + e.getMessage());
            }
        } else if (accion.equals("LOGO")) {
            logger.info("Accion: LOGO");
            Integer codciaxRecup = Integer.valueOf(idComp);
            //      String codciax= String.valueOf(codciaxRecup);

            //Empleado emp = empleadoService.recuperarCabecera(codciaxRecup, Integer.parseInt(idTrab));

            //      String accion = "";
            //      String idimg = "";
            String filePath = "";
            String filelink = "";
            String target = "";

            System.out.println("Accion :" + accion);
            // checks if the request actually contains upload file

            String server = "ftp.balkaned.com";
            int port = 21;
            String user = "ebaldeon@balkaned.com";
            String pass = "@Kekereke1984";

            /*if (!ServletFileUpload.isMultipartContent(request)) {
               // if not, we stop here
                PrintWriter writer = response.getWriter();
                writer.println("Error: Form must has enctype=multipart/form-data.");
                System.out.println("No es multipart");
                writer.flush();
               return null;
            }*/

            // configures upload settings
            //DiskFileItemFactory factory = new DiskFileItemFactory();
            // sets memory threshold - beyond which files are stored in disk
            //factory.setSizeThreshold(MEMORY_THRESHOLD);
            // sets temporary location to store files
            //factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            //ServletFileUpload upload = new ServletFileUpload((FileItemFactory) factory);
            //ServletFileUpload upload = new ServletFileUpload(factory);
            // sets maximum size of upload file
            //upload.setFileSizeMax(MAX_FILE_SIZE);
            // sets maximum size of request (include file + form data)
            //upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            // this path is relative to application's directory
            /* String uploadPath = getServletContext().getRealPath("")
            + File.separator + UPLOAD_DIRECTORY;  */
            String uploadPath = "";

            //  amazon s3...
            Regions clientRegion = null;
            String bucket_name = "";
            String key_name = "";
            String passPhrase = "";

            AWSCredentials credentials = null;
            AmazonS3 s3 = null;
            S3Object o = null;
            String nomeArquivo = "";
            String fileName2 = "";
            //String codciax ="";

            // fin de variables de Amazon s3
            // creates the directory if it does not exist
            /*no
             File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                 System.out.println("No existe direccion");
                uploadDir.mkdir();
            }
            */
            System.out.println("Direccion existe");

            // parses the request's content to extract file data
            //@SuppressWarnings("unchecked")

            System.out.println("request: " + request);
            logger.info("request: " + request);

            //          final CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) uploadFile;
            //          final CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) request.getPart("uploadFile");
            //var file = uploadFile;
            //List<FileItem> formItems = upload.parseRequest(request);

            //System.out.println("formItems: "+formItems);
            //logger.info("formItems: "+formItems);
            //if (formItems != null && formItems.size() > 0) {

            Compania ciainfo = null;
            logger.info("codciaxRecup: " + codciaxRecup);
            ciainfo = companiaService.getCompaniaAll(codciaxRecup);

            clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());
            bucket_name = ciainfo.getIexsourcedes().trim();
            key_name = ciainfo.getIexususource().trim();
            passPhrase = ciainfo.getIexpasssource().trim();

            logger.info("ciainfo.getIexsourcedes().trim(): " + ciainfo.getIexsourcedes().trim());

            try {
                //nomeArquivo=(Integer)session.getAttribute("codcia")+"/fotoemp/"+idimg+"."+FilenameUtils.getExtension(item.getName());
                //nomeArquivo=codciax+"/fotoemp/"+idimg+"."+FilenameUtils.getExtension(item.getName());
                nomeArquivo = "img/" + idimg + "." + "jpg";
                logger.info("nomeArquivo: " + nomeArquivo);

                credentials = new BasicAWSCredentials(key_name, passPhrase);
                s3 = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
                //s3.putObject(bucket_name, nomeArquivo, item.getName());
                s3.putObject(bucket_name, nomeArquivo, nomeArquivo);

                //PutObjectRequest request2 = new PutObjectRequest(bucket_name, nomeArquivo, new File(item.getName()));
                //InputStream in=item.getInputStream();
                InputStream in = uploadFile.getInputStream();
                File tmp = null;
                tmp = File.createTempFile("s3test", ".jpeg");
                Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);

                PutObjectRequest request2 = new PutObjectRequest(bucket_name, nomeArquivo, tmp);

                ObjectMetadata metadata = new ObjectMetadata();
                //metadata.setContentType(item.getContentType());
                metadata.setContentType(uploadFile.getContentType());
                //metadata.addUserMetadata("title", "someTitle");
                request2.setMetadata(metadata);
                s3.putObject(request2);

                System.out.println("Arquivo escrito: " + nomeArquivo);

                Compania cia = new Compania();
                cia.setIdCodcia(Integer.parseInt(idimg));
                cia.setUrlLogo(idimg + "." + "jpg");
                companiaService.logoCompania(cia);

                return new ModelAndView("redirect:/listCompanias");

            } catch (final Exception e) {
                System.out.println("You failed to upload " + nomeArquivo + " => " + e.getMessage());
            }
        }

        return new ModelAndView("redirect:/detalleEmpl@" + idTrab);
    }

}
