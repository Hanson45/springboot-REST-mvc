package com.example.app1.repository;

import com.example.app1.entities.Salario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface SalarioRepository extends JpaRepository<Salario, Long> {

}
