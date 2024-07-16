package com.alura.foro.controller;

import com.alura.foro.domain.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/foro")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Operation(
            summary = "Registra un topico en la base de datos",
            description = "",
            tags = { "topico", "post" })
    public ResponseEntity<DatosRespuestaTopico> registrarMedico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                          UriComponentsBuilder uriComponentsBuilder){
           try{
                Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
                //Dto para retornar, no recomendado retornar la entidad
                DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                        topico.getFechaCreacion(), topico.getAutor(), topico.getCurso(),topico.getTopicoStatus());
                //Ruta donde encontrar el topico creado
                URI url = uriComponentsBuilder.path("/foro/{id}").buildAndExpand(topico.getId()).toUri();
                return ResponseEntity.created(url).body(datosRespuestaTopico);
           } catch (DataIntegrityViolationException e) {
               throw new ResponseStatusException(HttpStatus.CONFLICT, "Entrada duplicada, ya hay un topico con este titulo y mensaje", e);
           }
    }

//    //SIN PAGINACION
//    @GetMapping
//    public List<DatosListadoTopico> listadoTopicos(){
//        return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
//    }

    //CON PAGINACION
    @GetMapping
    @Operation(
            summary = "Lista todos los topicos de la base de datos",
            description = "",
            tags = { "topico", "get" })
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(@PageableDefault(sort = "fechaCreacion",
            direction = Sort.Direction.DESC ,size = 5) Pageable paginacion) {
       // return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(topicoRepository.findByTopicoStatusTrue(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Detalla un topico buscado por ID en la base de datos",
            description = "",
            tags = { "topico", "get" })
    public ResponseEntity<DatosRespuestaTopico> obtenerTopicoPorId(@PathVariable Long id) {
        //Topico topico =topicoRepository.getReferenceById(id);
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()){
            Topico topico = optionalTopico.get();
            DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                    topico.getFechaCreacion(), topico.getAutor(), topico.getCurso(),topico.getTopicoStatus());
            return ResponseEntity.ok(datosRespuestaTopico);
        }else{
            throw new EntityNotFoundException("Topico no encontrado");
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Editar un topico de la base de datos",
            description = "",
            tags = { "topico", "put" })
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id,@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        //Topico topico =topicoRepository.getReferenceById(id);
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if(optionalTopico.isPresent()){
            Topico topico = optionalTopico.get();
            topico.actualizarTopico(datosActualizarTopico);
            return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                    topico.getFechaCreacion(), topico.getAutor(), topico.getCurso(),topico.getTopicoStatus()));
        }else {
            throw new EntityNotFoundException("Topico no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Elimina un topico de la base de datos",
            description = "",
            tags = { "topico", "delete" })
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        //getReferenceById(id)
//        Topico topico =topicoRepository.getReferenceById(id);
//        topico.statusOff();
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if(optionalTopico.isPresent()){
            Topico topico = optionalTopico.get();
            topico.statusOff();
            return ResponseEntity.noContent().build();
        }else {
            throw new EntityNotFoundException("Topico no encontrado");
        }
    }

    //DELETE FISICO
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarTopico(@PathVariable Long id){
//        Topico topico =topicoRepository.getReferenceById(id);
//        topicoRepository.delete(topico);
//    }
}