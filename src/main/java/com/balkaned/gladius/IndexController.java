package com.balkaned.gladius;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static java.lang.System.out;

@RestController
public class IndexController {
    static Logger logger = Logger.getLogger(IndexController.class.getName());

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @RequestMapping("/login2")
    public ModelAndView login(ModelMap model, HttpServletRequest request) {

        UsuarioConeccion uc = new UsuarioConeccion();
        model.addAttribute("usuarioConeccion", uc);
        String tip="";

        if(request.getSession().getAttribute("tiposession")==null){
            tip="0";
        }else {

            if(request.getSession().getAttribute("tiposession").equals("2")) {
                model.addAttribute("mensaje","Contraseña Erronea");
            }

            if(request.getSession().getAttribute("tiposession").equals("3")) {
                model.addAttribute("mensaje","Usuario o Contraseña Incorrecta");
            }

            if(request.getSession().getAttribute("tiposession").equals("4")) {
                model.addAttribute("mensaje","No se puede Ingresar Campos Vacíos, por favor ingrese Usuario y Contraseña");
            }

            if(request.getSession().getAttribute("tiposession").equals("5")) {
                model.addAttribute("mensaje","Alerta!, Hemos detectado que no es el Admnistrador de la aplicación. "
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
    public ModelAndView verificarLogin2(ModelMap model, HttpServletRequest request,@ModelAttribute("usuarioConeccion") UsuarioConeccion uc,BindingResult result,SessionStatus status) {

        UsuarioConeccion uc2=usuarioConeccionService.obtenerUsuarioConeccionByName(uc);

        if(uc2.getUser().equals("sinbd")) {
            request.getSession().setAttribute("tiposession", "5");

            return new ModelAndView("redirect:/login2");
        }else {
            if(uc.getUser().equals("")) {
                out.println("Campos Vacios");
                request.getSession().setAttribute("tiposession", "4");
                //model.addAttribute("mensaje","Contraseña Erronea");

                return new ModelAndView("redirect:/login2");
            }else {
                if(uc2.getUser().equals("noecontrado")){
                    logger.info("Usuario o Contraseña Incorrecta");
                    request.getSession().setAttribute("tiposession", "3");
                    //model.addAttribute("mensaje","Usuario o Contraseña Incorrecta");

                    return new ModelAndView("redirect:/login2");
                }else{
                    if(uc2.getPass().equals(uc.getPass())){

                        UsuarioConeccion uc3=usuarioConeccionService.obtenerUsuarioConeccionById(uc2.getId_usuario());

                        logger.info("ID_Usuario: "+uc2.getId_usuario());

                        char firstCharacter = uc3.getUser().charAt(0);
                        char char1UpperCase = Character.toUpperCase(firstCharacter);
                        String cast1= String.valueOf(char1UpperCase);

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


                        String sqlURL = "jdbc:postgresql://ec2-18-191-189-102.us-east-2.compute.amazonaws.com:5432/"+uc3.getSourceDes();

                        String redirect="redirect:/selcompanias@"+uc3.getId_usuario();
                        return new ModelAndView(redirect);
                    }else{
                        logger.info("Contraseña Erronea: ");
                        request.getSession().setAttribute("tiposession", "2");
                        //model.addAttribute("mensaje","Contraseña Erronea");

                        return new ModelAndView("redirect:/login2");
                    }
                }
            }
        }
    }


    @RequestMapping(value="/selcompanias@{idUser}",method=RequestMethod.GET)
    public ModelAndView selcompanias(ModelMap model, HttpServletRequest request,@PathVariable String idUser) {

        logger.info("idUser: "+idUser);
        List<Compania> companiaList=usuarioConeccionService.listarCompaniasBycodUsu(idUser);

        model.addAttribute("compList",companiaList);
        model.addAttribute("schema","dark");

        return new ModelAndView("public/ecompanias");
    }

    @RequestMapping(value="/selcompaniasChange@{idUser}",method=RequestMethod.GET)
    public ModelAndView selcompaniasChange(ModelMap model, HttpServletRequest request,@PathVariable String idUser) {

        logger.info("idUser: "+idUser);
        List<Compania> companiaList=usuarioConeccionService.listarCompaniasBycodUsu(idUser);

        model.addAttribute("compList",companiaList);
        model.addAttribute("schema","dark");

        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("firstCharacter", null);
        request.getSession().setAttribute("idCompania", null);
        request.getSession().setAttribute("nombrecomp", null);
        request.getSession().setAttribute("ruccomp", null);

        return new ModelAndView("public/ecompanias");
    }

    @RequestMapping("/logoff")
    public ModelAndView logoff(ModelMap model, HttpServletRequest request){

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
    public ModelAndView home(ModelMap model, HttpServletRequest request,@PathVariable String idComp,@PathVariable String idUser) {

        logger.info("idComp: "+idComp);
        logger.info("idUser: "+idUser);

        UsuarioConeccion uc1=usuarioConeccionService.obtenerUsuarioConeccionById(idUser);
        Compania comp1=companiaService.getCompaniaAll(Integer.parseInt(idComp));

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");

        request.getSession().setAttribute("idCompania", comp1.getIdCodcia());
        request.getSession().setAttribute("urlLogo", comp1.getUrlLogo());
        request.getSession().setAttribute("nombrecomp", comp1.getDescCia());
        request.getSession().setAttribute("ruccomp", comp1.getNroRuc());
        request.getSession().setAttribute("schema", comp1.getSchema());

        model.addAttribute("idComp",idComp);
        model.addAttribute("urlLogo",comp1.getUrlLogo());
        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", comp1.getDescCia());
        model.addAttribute("rucComp",comp1.getNroRuc());
        model.addAttribute("schema",comp1.getSchema());

        return new ModelAndView("public/home");
    }

    @RequestMapping("/verFoto@{logo}@{idComp}@{urlLogo}")
    public ModelAndView verFoto(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String logo, @PathVariable String idComp, @PathVariable String urlLogo) throws IOException {

        logger.info("logo: "+logo);
        String accion=logo;
        String codciax=idComp;
        String fileurl=urlLogo;
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

        Integer outrep=1;
        // ftp code  FTPClient ftpClient = new FTPClient();

        System.out.println("test");
        //AmazonS3 s3 = new AmazonS3Client(new PropertiesCredentials(
        //     S3Sample.class.getResourceAsStream("AwsCredentials.properties")));
        byte[] salt = {
                (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
                (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
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
        String key_name ="";
        String passPhrase = "";

        String fileName = "";
        AWSCredentials credentials =null;
        AmazonS3 s3 = null;

        Compania ciainfo=companiaService.getCompaniaAll(Integer.parseInt(codciax));
        //Compania ciainfo= daocia.getCompaniaAll(Integer.parseInt(codciax));

        if(accion.equals("LOGO")){
            outrep=0;

            //    pathfile = (String)session.getAttribute("GLADIUS_IMG")+fileurl;

            // upload file to folder and set it to public
            fileName = "img/"+fileurl;;

            /// System.out.format("Downloading %s from S3 bucket %s...\n", key_name, bucket_name);
            //  AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegionalUsEast1EndpointEnabled(Boolean.FALSE).credentialsProvider(credentials).build();
            //AmazonS3 s3 = new AmazonS3Client(new PropertiesCredentials(awsCreds ));
            //   AmazonS3Client s3 = new AmazonS3Client(awsCreds);

            if(ciainfo.getUrlflgsource().equals("1")) {

                //clientRegion = Regions.US_EAST_2;
                clientRegion = Regions.valueOf(ciainfo.getIexregiondes().trim());

                System.out.format("Entro a la CIAINFO");
                bucket_name = ciainfo.getIexsourcedes().trim();
                key_name =ciainfo.getIexususource().trim();
                passPhrase = ciainfo.getIexpasssource().trim();
                // Regionname = ciainfo.getIexregiondes().trim();

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
                    //    Logger.getLogger(AmazonS3DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.format("Fallo");
                }

            }else if(ciainfo.getUrlflgsource().equals("2")){

                String path_img=ciainfo.getIexurlfileimg() ;

                //pathfile = (String)session.getAttribute("GLADIUS_IMG")+fileurl;
                pathfile = path_img+fileurl;

                ServletOutputStream out;
                out = response.getOutputStream();
                // FileInputStream fin = new FileInputStream("c:\\test\\java.jpg");
                FileInputStream fin = new FileInputStream(pathfile);

                BufferedInputStream bin = new BufferedInputStream(fin);
                BufferedOutputStream bout = new BufferedOutputStream(out);
                int ch =0; ;
                while((ch=bin.read())!=-1)
                {
                    bout.write(ch);
                }

                bin.close();
                fin.close();
                bout.close();
                out.close();
            }

            /* ftp code
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(2);
     //     ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String remoteFile1 = "/gladius/img/1.jpg";
            OutputStream outputStream2 = response.getOutputStream();
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile1);

            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }
                System.out.println("File #2 has been downloaded successfully.");

            outputStream2.close();
            inputStream.close();
            */

        }

        return null;
    }

}
