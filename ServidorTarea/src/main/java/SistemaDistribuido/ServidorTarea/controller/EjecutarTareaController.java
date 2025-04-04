package SistemaDistribuido.ServidorTarea.controller;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;
import SistemaDistribuido.ServidorTarea.services.TareaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejecutarTarea")
@RequiredArgsConstructor
public class EjecutarTareaController {

    private final TareaService tareaService;

    @PostMapping()
    public ResponseEntity<ResultadoTareaDTO> ejecutarTarea(@Valid @RequestBody ParametroTareaDTO tareaDTO) {
        ResultadoTareaDTO resultado = tareaService.realizarTarea(tareaDTO);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/actuator/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
