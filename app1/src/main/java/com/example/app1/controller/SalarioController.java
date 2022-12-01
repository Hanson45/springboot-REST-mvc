package com.example.app1.controller;

import com.example.app1.entities.Salario;
import com.example.app1.service.SalarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SalarioController {

    @Autowired
    private SalarioService salarioService;

    @GetMapping("/showall")
    public List<Salario> getAll(){
        return salarioService.getAll();
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Salario> create(@RequestBody @Valid Salario salario){ // se quita @RequestBody
        if(salario.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(salarioService.add(salario));
    }

    // READ -> FIND BY ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Salario> findById(@PathVariable Long id){
        Optional<Salario> salarioOptional = salarioService.findById(id);
        if(salarioOptional.isPresent()){
            return ResponseEntity.ok(salarioOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE

    @PutMapping("/edit")
    public ResponseEntity<Salario> update( @RequestBody Salario salario){ //
        if (salario.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!salarioService.existsById(salario.getId())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(salarioService.add(salario));

    }



    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Salario> deleteById(@PathVariable Long id){
        if(!salarioService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        salarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<Salario> deleteAll(){
        salarioService.deleteAll();
        return ResponseEntity.noContent().build();
    }



}
