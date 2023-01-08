package com.Simphony.Hotels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelsApplication.class, args);
    }


    public class View {
        public static class FavoritesBack {
        }
    }
}
