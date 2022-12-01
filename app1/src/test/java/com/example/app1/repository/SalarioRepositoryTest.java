package com.example.app1.repository;

import com.example.app1.entities.Salario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class SalarioRepositoryTest {
    @Autowired
    SalarioRepository salarioRepository;



    Salario salario = new Salario();
    Salario salario1 = new Salario();


    @BeforeEach
    void setup(){
        salario = Salario.builder()
                .nombreSalario("Roberto Gomes")
                .remuneracion(1500d)
                .build();

        salario1 = Salario.builder()
                .nombreSalario("Javier Gutierrez")
                .remuneracion(1500d)
                .build();
    }




    // SAVE
    @DisplayName("Save - test")
    @Test
    void testSave(){

        Salario salario1 = Salario.builder()
                .nombreSalario("Javier Gutierrez")
                .remuneracion(1500d)
                .build();

        Salario newSal = salarioRepository.save(salario1);

        assertThat(newSal).isNotNull();
        assertThat(newSal.getId()).isGreaterThan(0);

    }

    @Test
    @DisplayName("showList - Test")
    void showListTest(){
        Salario salario1 = Salario.builder()
                .nombreSalario("Javier Gutierrez")
                .remuneracion(1500d)
                .build();

        Salario sal1 = salarioRepository.save(salario1);
        Salario sal2 = salarioRepository.save(salario);

        List<Salario> listSalarios = salarioRepository.findAll();

        assertThat(listSalarios).isNotNull();
        assertThat(listSalarios.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Test - obtener por id")
    void getSalById(){
        Salario sal1 = salarioRepository.save(salario);

        Salario salarioTest = salarioRepository.findById(salario.getId()).get();

        assertThat(salarioTest).isNotNull();


    }

    @Test
    @DisplayName("Test - Actualizar por id")
    void updateById(){
        Salario sal1 = salarioRepository.save(salario);

        Salario salarioSave = salarioRepository.findById(salario.getId()).get();
        salarioSave.setNombreSalario("Tomas Ramirez");
        salarioSave.setRemuneracion(950d);

        Salario salUpdated = salarioRepository.save(salarioSave);

        assertThat(salUpdated.getId()).isEqualTo(salario.getId());
        assertThat(salUpdated.getNombreSalario()).isEqualTo("Tomas Ramirez");
        assertThat(salUpdated.getRemuneracion()).isEqualTo(950d);

    }

    @Test
    @DisplayName("Test - Eliminar Salario")
    void deleteSalario(){
        salarioRepository.save(salario);

        salarioRepository.deleteById(salario.getId());

        Optional<Salario> salarioOptional = salarioRepository.findById(salario.getId());

        assertThat(salarioOptional).isEmpty();
    }

    @Test
    @DisplayName("Test - Eliminar todo")
    void deleteAll(){
        salarioRepository.save(salario);
        salarioRepository.save(salario1);

        salarioRepository.deleteAll();

        List<Salario> salarioList = salarioRepository.findAll();

        assertThat(salarioList.size()).isEqualTo(0);




    }



}
