package com.example.app2.controller;

import com.example.app2.component.Result;
import org.apache.coyote.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpHeaders;
import org.thymeleaf.exceptions.TemplateInputException;


import java.util.Arrays;
import java.util.List;

@Controller
public class ApiController {
    @Autowired
    private RestTemplate restTemplate;

    private final String indexView = "index";
    private final String salarioView = "salario";
    private final String formView = "form";
    private final String formEditView = "formedit";
    private final String searchView = "search";



    // READ
    @GetMapping("/alldata")
    public ModelAndView main(){
        String url = "http://localhost:8081/showall";
        //Capturamos info de JSON
        Result[] result = restTemplate.getForObject(url, Result[].class);

        ModelAndView mov = new ModelAndView(indexView);
        mov.addObject("salarioList", Arrays.asList(result));
        return mov;
    }

    @GetMapping("/search")
    public String search(){
        return searchView;
    }

    @GetMapping("/getid")
    public ModelAndView getId(@RequestParam(value = "id", required = false) String id){
        Result result = new Result();
        String url = "http://localhost:8081/find/"+id;
        result = restTemplate.getForObject(url, Result.class);
        ModelAndView mav = new ModelAndView(salarioView);;
        mav.addObject("result", result);
        return mav;
    }

    //CREATE
    @GetMapping("/showform")
    public String createNew(Model model){
        Result result = new Result();
        model.addAttribute("result", result);
        return formView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Result result){
        String url = "http://localhost:8081/create";
        System.out.println(result);
        restTemplate.postForEntity(url, result, Result.class);
        return "redirect:/alldata";
    }

    //UPDATE
    @GetMapping("/edit/{id}")
    public ModelAndView getId(@PathVariable String id, Model model){
        Result result = new Result();
        String url = "http://localhost:8081/find/"+id;
        result = restTemplate.getForObject(url, Result.class);
        ModelAndView mav = new ModelAndView(formEditView);;
        mav.addObject("result", result);
        return mav;
    }


    @GetMapping("/formedit")
    public String edit( Model model){
        Result result = new Result();
        model.addAttribute("result", result);
        return formEditView;
    }


    @PostMapping("/edit")
    public String update(@ModelAttribute Result result){
        try {
            String url = "http://localhost:8081/edit";
            //ResponseEntity<Result> res= restTemplate.postForEntity(url, result, Result.class);
            restTemplate.put(url, result);
            return "redirect:/alldata";
        }catch (Exception exception){
            System.out.println("Trying edit a non-existent user");
            return "404";
        }
    }


    //DELETE
        // VERIFICAR SI USAR @DELETEMAPPING

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") String id) {
        String url = "http://localhost:8081/delete/";
        restTemplate.delete(url + id);
        return "redirect:/alldata";
    }

    @GetMapping("/deleteall")
    public String deleteAll(){
        restTemplate.delete("http://localhost:8081/deleteall");
        return "redirect:/alldata";
    }




}
