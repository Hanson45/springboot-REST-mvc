package com.example.app1.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "salarios")
public class Salario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=30)
    @Column
    private String nombreSalario;
    @Column
    private Double remuneracion;


}
