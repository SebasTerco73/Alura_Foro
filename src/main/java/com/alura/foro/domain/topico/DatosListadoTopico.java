package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico (
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String autor,
        String curso,
        Boolean estado
) {

    public  DatosListadoTopico (Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getAutor(),
                topico.getCurso(),topico.getTopicoStatus());
    }
}
