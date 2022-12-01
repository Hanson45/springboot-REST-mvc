package com.example.app1.service.impl;

import com.example.app1.entities.Salario;
import com.example.app1.repository.SalarioRepository;
import com.example.app1.service.SalarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalarioServiceImpl implements SalarioService {

    @Autowired
    private SalarioRepository salarioRepository;

    @Override
    public List<Salario> getAll() {
        return salarioRepository.findAll();
    }

    @Override
    public Salario add(Salario salario) {
        return salarioRepository.save(salario);
    }

    @Override
    public Salario edit(Salario salario) {
        return salarioRepository.save(salario);
    }

    @Override
    public void deleteAll() {
        salarioRepository.deleteAll();
    }

    @Override
    public void deleteById (Long id){
        salarioRepository.deleteById(id);
    }

    //METODO PARA CORROBORAR ID
    @Override
    public boolean existsById(Long id){
        return salarioRepository.existsById(id);
    }

    @Override
    public Optional<Salario> findById(Long id){
        return salarioRepository.findById(id);
    }
}
