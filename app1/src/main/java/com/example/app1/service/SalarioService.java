package com.example.app1.service;

import com.example.app1.entities.Salario;

import java.util.List;
import java.util.Optional;

public interface SalarioService {

    public abstract List<Salario> getAll();

    public abstract Salario add(Salario salario);

    public abstract Salario edit(Salario salario);

    public abstract void deleteAll();

    public abstract void deleteById (Long id);

    //OBTENER ID
    public abstract boolean existsById(Long id);

    public abstract Optional<Salario> findById(Long id);


}
