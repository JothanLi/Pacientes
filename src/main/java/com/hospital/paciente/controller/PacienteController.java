package com.hospital.paciente.controller;

import com.hospital.paciente.model.Paciente;
import com.hospital.paciente.sevice.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PacienteController {

    @Autowired
    public PacienteService pacienteService;

    @GetMapping("/api/v1/pacientes")
    public ResponseEntity<?> obtenerPacientes(){
        List<Paciente> pacientes = pacienteService.findAll();
        return ResponseEntity.ok().body(pacientes);
    }
    @GetMapping("/api/v1/pacientes/{id}")
    public ResponseEntity<?> buscarPaciente(@PathVariable Integer id){
        Paciente paciente = pacienteService.findById(id);
        if(paciente==null){
            return ResponseEntity.status(404).body("Paciente no encontrado");
        }
        return ResponseEntity.status(200).body(paciente);

    }
    @DeleteMapping("/api/v1/pacientes/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
        Paciente paciente = pacienteService.findById(id);
        if(paciente != null) {
            pacienteService.deleteById(id);
            return ResponseEntity.status(200).body("Paciente eliminado");
        }
        return ResponseEntity.status(404).body("El paciente no fue encontrado");
    }
}
