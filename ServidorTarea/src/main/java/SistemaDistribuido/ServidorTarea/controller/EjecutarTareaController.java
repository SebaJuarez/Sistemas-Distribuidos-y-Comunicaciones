package SistemaDistribuido.ServidorTarea.controller;

import SistemaDistribuido.ServidorTarea.dtos.ParametroTareaDTO;
import SistemaDistribuido.ServidorTarea.dtos.ResultadoTareaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ejecutarTarea")
public class EjecutarTareaController {

    @GetMapping()
    public ResponseEntity<ResultadoTareaDTO> ejecutarTarea(@RequestBody ParametroTareaDTO tareaDTO){

        ResultadoTareaDTO resultado = realizarTarea(tareaDTO);

        return new ResponseEntity<ResultadoTareaDTO>(resultado, HttpStatus.OK);

    }

    @GetMapping("/actuator/health")
    public ResponseEntity<String> health(){
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    private ResultadoTareaDTO realizarTarea(ParametroTareaDTO tareaDTO) {

        Character operador = tareaDTO.operador();
        Number numero1 = tareaDTO.numero1();
        Number numero2 = tareaDTO.numero2();

        switch (operador){

            case '+':
                return new ResultadoTareaDTO(numero1.doubleValue() + numero2.doubleValue());

            case '-':
                return new ResultadoTareaDTO(numero1.doubleValue() - numero2.doubleValue());

            case '*':
                return new ResultadoTareaDTO(numero1.doubleValue() * numero2.doubleValue());

            case '/':
                return new ResultadoTareaDTO(numero1.doubleValue() / numero2.doubleValue());
        }

        throw new UnsupportedOperationException("Solo aceptamos + , - , * , /");
    }

}
