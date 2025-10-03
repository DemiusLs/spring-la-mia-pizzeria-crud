package org.lesson.java.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lesson.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lesson.java.spring.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    PizzaRepository repo;
    
    @GetMapping("/index")
    public String index(Model model) {
        List<Pizza> pizzas = repo.findAll();
        model.addAttribute("pizzas" , pizzas );
        return "/pizzas/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id , Model model ){

        Pizza pizza = repo.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "/pizzas/show";
    }


    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name ="name", required = false) String name , Model model ){

        List<Pizza> pizzas ;
        if(name != null && !name.isEmpty()){
            pizzas = repo.findByNameIgnoreCaseContaining(name); 
        }else{
            pizzas = repo.findAll();
        }
        model.addAttribute("pizzas", pizzas);
        return "/pizzas/index";
    }
    
}
