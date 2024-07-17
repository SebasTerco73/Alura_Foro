<h1 align="center"> Alura Foro  </h1>
<p align="center">
   <img src="https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green">
</p>

## :pencil: Descripcion del proyecto
Challenge final de la cursada en Alura 2024 Java Back End. Creando una API REST usando Spring sobre un foro en el cual se puede hacer un CRUD de 
topicos con una base de datos relacional en mysql para persistir los datos y un servicio de autenticación/autorización para restringir el acceso a la información.

![Sin título](https://github.com/user-attachments/assets/c2ad99ce-f9de-4b5c-bcf6-bd51eaacb768)


## :hammer: Funcionalidades del proyecto
- Post para crear un nuevo topico ingresando: titulo, mensaje, autor, curso. Para la fecha se usa el metodo now() de LocalDate y el estado del topico se inicia en true.
  No pueden haber 2 topicos con el mismo titulo y mensaje.
- Get para listar todos los topicos usando @PageableDefault para listar desde el mas actual al mas antiguo y mostrar 5 elementos por pagina
- Get para detallar un topico por ID, usando la anotacion @PathVariable
- Put para actualizar un topico
- Delete para hacer un borrado logico de un topico
- Anotacion @Valid para validar los datos ingresados
- Pruebas hechas en Postman e Insomnia
- Contraseña de usuarios guardadas usando Hash de BCrypt
- JSON Web Token para la autenticacion y seguridad de la app 
- SpringFox Swagger para generar una interfaz amigable y accesible

## :heavy_check_mark: Tecnologias utilizadas
[![My Skills](https://skillicons.dev/icons?i=java,spring,hibernate,maven,idea,github,mysql,postman)](https://skillicons.dev)

## :gear: Base de datos
- MySQL
- 2 tablas: Usuarios, topicos

