package SistemaDistribuido.Servidor.controller;

import SistemaDistribuido.Servidor.dtos.RespuestaTareaDTO;
import SistemaDistribuido.Servidor.services.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarea")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @PostMapping()
    public ResponseEntity<RespuestaTareaDTO> ejecutarTarea(@RequestBody Object parametroTarea) {
        return ResponseEntity.ok(tareaService.realizarTarea(parametroTarea));
    }

}
