# Gladius2

<!-- ===============================================-->
<!-- Guia de instalación y configuración de Gladius2-->
<!-- ===============================================-->

Realizar un clean install para que descargue los repositorios maven desde el pom.xml

De preferencia poner la configuración de maven la carpeta m2 en el diso C:\.m2 para que no tenga conflictos de permisos al descargar el repositorio o espacio de disco.

Levantar el proyecto con IntelliJ versión Community en mi caso en opciones de arranque Run as Maven y poner comando spring-boot:run en mi caso estoy usando JDK20 y apache-maven-3.9.3-bin

Verificar el puerto en archivo application.properties, digitar en navegador Chrome o Microsoft Edge:
    
    http://localhost:8185/gladius/login2
 
 Para este caso la conexión a la base de datos se realiza con servicio cloud AWS3 no se necesita instalar ni restaurar ningún backup.
 
