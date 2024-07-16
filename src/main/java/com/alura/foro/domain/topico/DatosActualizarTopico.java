package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosActualizarTopico(String titulo,
                                    String mensaje,
                                    LocalDateTime fecha,
                                    String autor,
                                    String curso,
                                    Boolean estado) {
}
