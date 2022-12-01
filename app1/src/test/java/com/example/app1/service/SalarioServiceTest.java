package com.example.app1.service;
import static org.mockito.BDDMockito.*;
import com.example.app1.entities.Salario;
import com.example.app1.repository.SalarioRepository;
import com.example.app1.service.impl.SalarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SalarioServiceTest {

    @Mock
    private SalarioRepository salarioRepository;
    @InjectMocks
    private SalarioServiceImpl salarioService;

    private Salario salario, salario1;
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

    @DisplayName("Test - SAVE")
    @Test
    void saveTest(){
        // given

        salarioRepository.findById(salario.getId());

        given(salarioRepository.save(salario)).willReturn(salario);

        //when
        Salario salarioSaved = salarioService.add(salario);

        // then
        assertThat(salarioSaved).isNotNull();

    }

    @Test
    void listAllTest(){
        given(salarioRepository.save(salario)).willReturn(salario);
        given(salarioRepository.save(salario1)).willReturn(salario1);
        salarioService.add(salario);
        salarioService.add(salario1);
        List<Salario> listSav = salarioRepository.findAll();
        given(salarioRepository.findAll()).willReturn(listSav);

        List<Salario> listSaved = salarioService.getAll();



        assertThat(listSaved.size()).isEqualTo(2);
    }

}
