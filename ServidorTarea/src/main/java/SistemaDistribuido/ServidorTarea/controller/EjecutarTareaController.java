package SistemaDistribuido.ServidorTarea.controller;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;
import SistemaDistribuido.ServidorTarea.exceptions.OperacionNoSoportadaException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejecutarTarea")
public class EjecutarTareaController {

    @PostMapping()  // <- Cambiado de @GetMapping a @PostMapping
    public ResponseEntity<ResultadoTareaDTO> ejecutarTarea(@Valid @RequestBody ParametroTareaDTO tareaDTO) {

        ResultadoTareaDTO resultado = realizarTarea(tareaDTO);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/actuator/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    private ResultadoTareaDTO realizarTarea(ParametroTareaDTO tareaDTO) {
        char operador = tareaDTO.operador().charAt(0);  // <- Corregido porque operador ahora es String
        double numero1 = tareaDTO.numero1();
        double numero2 = tareaDTO.numero2();

        switch (operador) {
            case '+':
                return new ResultadoTareaDTO(numero1 + numero2);
            case '-':
                return new ResultadoTareaDTO(numero1 - numero2);
            case '*':
                return new ResultadoTareaDTO(numero1 * numero2);
            case '/':
                if (numero2 == 0) {
                    throw new ArithmeticException("No se puede dividir por cero.");
                }
                return new ResultadoTareaDTO(numero1 / numero2);
            default:
                throw new OperacionNoSoportadaException(operador);
        }
    }
}
