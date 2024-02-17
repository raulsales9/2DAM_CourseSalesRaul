package jaume.ad.infrestructure.rest;

import jaume.ad.aplicattion.dto.EquipoDto;
import jaume.ad.aplicattion.service.EquipoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/Equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Bienvenido a el Vardrid. Fecha actual: " + LocalDate.now(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EquipoDto>> getAllEquipos() {
        List<EquipoDto> equipos = equipoService.getAllEquipos();
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @GetMapping("/{idEquipo}")
    public ResponseEntity<EquipoDto> getEquipoById(@PathVariable Long idEquipo) {
        EquipoDto equipo = equipoService.findById(idEquipo);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    @GetMapping("/jugador")
    public ResponseEntity<EquipoDto> getEquipoByJugadorId(@RequestParam Long idJugador) {
        EquipoDto equipo = equipoService.findByJugadoresId(idJugador);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<EquipoDto>> getEquiposByCiudad(@PathVariable String ciudad) {
        List<EquipoDto> equipos = equipoService.findByCiudad(ciudad);
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EquipoDto> createEquipo(@RequestBody EquipoDto equipoDto) {
        EquipoDto createdEquipo = equipoService.insertEquipo(equipoDto);
        return new ResponseEntity<>(createdEquipo, HttpStatus.CREATED);
    }
}

