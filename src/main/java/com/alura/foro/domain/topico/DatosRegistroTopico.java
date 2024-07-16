package com.alura.foro.domain.topico;


import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico (
@NotBlank (message = "Necesita ingresar un titulo")
String titulo,
@NotBlank (message = "Necesita ingresar un mensaje")
String mensaje,
@NotBlank (message = "Necesita ingresar su nombre")
String autor,
@NotBlank (message = "Necesita ingresar el curso")
String curso
){
}
