package com.hospital.paciente.sevice;

import com.hospital.paciente.model.Paciente;
import com.hospital.paciente.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PacienteService {

    @Autowired
    public PacienteRepository pacienteRepository;

    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }

    public Paciente findById(Integer id){
        return pacienteRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id){
        pacienteRepository.deleteById(id);
    }
}
