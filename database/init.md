# Cómo generar la base de Datos

En este proyecto la base de datos ya viene configurada con JPA para la generación automática de todas las tablas y relaciones. 

Pero, rreviamente se debe configurar lo siguiente:

1. Tener una instancia de MySQL (puede ser Docker) corriendo en el puerto 3309
2. Crear una base de datos llamada `smart_calendar`
3. Crear un usuario para springboot de la siguiente forma
```sql
CREATE USER 'springboot'@'%' IDENTIFIED BY 'miPassword';
GRANT ALL PRIVILEGES ON smart_calendar.* TO 'springboot'@'%';
```
4. Verificar que las credenciales coincidan con los parámetros especificados en el [application properties](..\src\main\resources\application.properties). También verificar que el puerto de la base de datos sea el correcto y el modo de hibernate sea `update`.
5. Correr la aplicación y verificar que se hayan creado las tablas en la base de datos
6. Crear la vista Planification en la base de datos usando el archivo [planification_view.sql](crear_vista_planificacion.sql)
7. (opcional) Añadir los datos de prueba usando el archivo [mockdata.sql](mockdata.sql)
8. (opcional) Revocar todos los permisos al usuario springboot y solo dejarle permisos de SELECT, INSERT, UPDATE, DELETE sobre las tablas de la base de datos
```sql
REVOKE ALL PRIVILEGES ON smart_calendar.* FROM 'springboot'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON smart_calendar.* TO 'springboot'@'%';
```
8.1. (opcional) Cambiar el modo de hibernate de `update` a `none` en [application properties](..\src\main\resources\application.properties)
