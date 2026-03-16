package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TesteController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping
    public void teste(){ //só pra brincar hihi

        Teste teste = new Teste();
        teste.setDescricao("piririmpiririm");
        testRepository.save(teste);

        System.out.println("passou");
    }
}
