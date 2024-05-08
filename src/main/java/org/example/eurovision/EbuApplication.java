package org.example.eurovision;

import org.example.eurovision.game.GameController;
import org.example.eurovision.game.Question;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class EbuApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbuApplication.class, args);
    }



}



