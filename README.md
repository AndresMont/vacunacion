# Vacunación de empleados

Aplicación de inventario de empleados vacunados.

**Requisitos:**
  * Plataforma: Java 11
  * DBMS: Postgres 14.0
  * Servidor de aplicaciones: Apache Maven 3.8.5

# 1. Instalación
  1.1 Crear una nueva base de datos con el nombre "vacunacion".
  
  ![image](https://user-images.githubusercontent.com/23529255/173403419-eeb99463-0b4f-4474-907e-30ca5fddc782.png)
  
  1.2 Acceder por terminal al directorio del proyecto y restaurar el respaldo de base de datos mediante el comando:
  
  `pg_restore -p 5432 -U postgres -d vacunacion -v -O "model/vacunacion.dmp"`
  
  Esta restauración también la podemos hacer por la interfaz de PgAdmin

  ![image](https://user-images.githubusercontent.com/23529255/173433368-927de1a9-2c49-4741-9ed6-2670b8d46a8b.png)
  ![image](https://user-images.githubusercontent.com/23529255/173433193-115db456-a9dd-4aaa-afdd-0327044d6f05.png)  

  
  1.2 Ingresar al directorio del ambiente mediante línea de comandos e iniciar la aplicación mediante el comando:
  
  `mvnw.cmd spring-boot:run`
  
  ![image](https://user-images.githubusercontent.com/23529255/173405089-feea6e51-098a-4d8d-acde-8ae0862685ba.png)
  
# 2. Ejecución

  2.1 Obtener el código de acceso en la siguiente URL, accedemos con el usuario y contraseña
  
  http://localhost:8080/oauth/authorize?client_id=krugerclient&response_type=code&scope=read_profile_info
  
  * usuario: krugeradmin
  * pass: 123456
  
  
  ![image](https://user-images.githubusercontent.com/23529255/173430623-7ccf4e6c-ff40-4cc1-816d-ed2afacc4fe0.png)

  Presionamos en "Approve" para obtener el código de acceso
  
  ![image](https://user-images.githubusercontent.com/23529255/173401101-3300c0e3-6b1e-4218-9ba3-88f9a7b42877.png)
  
  2.2 Mediante este código y el usuario y contraseña del cliente podemos obtener el token de acceso:
  
  * usuario: krugerclient
  * pass: 123456
  
  ![image](https://user-images.githubusercontent.com/23529255/173407179-72c0257b-3968-4643-923f-84057966afba.png)
  
  ![image](https://user-images.githubusercontent.com/23529255/173405823-8d758075-b32e-4bb0-80d3-d6ab6b1d620f.png)
  
  
  2.3 Y finalmente con este token de acceso podemos hacer uso del los endpoints especificados en la documentación (Colección adjunta ).
  
  ![image](https://user-images.githubusercontent.com/23529255/173407270-43d68c81-2669-4117-9702-f012ac632a16.png)

  
# 3. Documentación

  La documentación se puede observar dentro del url:
  
  http://localhost:8080/swagger-ui.html
  
  
