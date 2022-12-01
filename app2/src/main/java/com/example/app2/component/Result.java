package com.example.app2.component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Long id;
    private String nombreSalario;
    private Double remuneracion;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSalario() {
        return nombreSalario;
    }

    public void setNombreSalario(String nombreSalario) {
        this.nombreSalario = nombreSalario;
    }

    public Double getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(Double remuneracion) {
        this.remuneracion = remuneracion;
    }
}


