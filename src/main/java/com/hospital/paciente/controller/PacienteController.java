package com.hospital.paciente.controller;

import com.hospital.paciente.model.Paciente;
import com.hospital.paciente.sevice.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PacienteController {

    @Autowired
    public PacienteService pacienteService;

    @GetMapping("/api/v1/paciente")
    public ResponseEntity<?> listarPacientes(){
        List<Paciente> pacientes = pacienteService.listaPacientes();
        return ResponseEntity.ok().body(pacientes);
    }
    @GetMapping("/api/v1/paciente/{id}")
    public ResponseEntity<?> consultarPaciente(@PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPaciente(id);
        if(paciente==null){
            return ResponseEntity.status(404).body("Paciente no encontrado");
        }
        return ResponseEntity.status(200).body("Paciente: \n" + "ID: " + paciente.getId() + "\n" +"Nombre: " + paciente.getName() + "\n" + "Apellido: " + paciente.getApellido() + "\n" + "Correo: " + paciente.getCorreo() + "\n" + "Rut: " + paciente.getRut() + "\n" + "Fecha Nacimiento: " + paciente.getFechaNacimiento());
    }

    @GetMapping("/api/v1/paciente/buscar-correo/{correo}")
    public ResponseEntity<?> buscarPacientePorCorreo(@PathVariable String correo){
        Paciente pacienteBuscado = pacienteService.buscarPacientePorCorreo(correo);
        return ResponseEntity.status(200).body("Paciente: \n" + "ID: " + pacienteBuscado.getId() + "\n" +"Nombre: " + pacienteBuscado.getName() + "\n" + "Apellido: " + pacienteBuscado.getApellido() + "\n" + "Correo: " + pacienteBuscado.getCorreo() + "\n" + "Rut: " + pacienteBuscado.getRut() + "\n" + "Fecha Nacimiento: " + pacienteBuscado.getFechaNacimiento());
    }

    @DeleteMapping("/api/v1/paciente/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPaciente(id);
        if(paciente == null) {
            return ResponseEntity.status(404).body("Paciente no encontrado");
        }
        pacienteService.deletePaciente(id);
        return ResponseEntity.status(200).body("Paciente eliminado");
    }

    @PostMapping("/api/v1/paciente")
    public ResponseEntity<?> guardarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteCreado = pacienteService.guardarPaciente(paciente);
        pacienteService.guardarPaciente(paciente);
        return ResponseEntity.status(201).body("Paciente guardado: \n" + "ID: " + pacienteCreado.getId() + "\n" +"Nombre: " + pacienteCreado.getName() + "\n" + "Apellido: " + pacienteCreado.getApellido() + "\n" + "Correo: " + pacienteCreado.getCorreo() + "\n" + "Rut: " + pacienteCreado.getRut() + "\n" + "Fecha Nacimiento: " + pacienteCreado.getFechaNacimiento());
    }

}
