package com.whitbread.venuesearch.bootstrap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.whitbread.venuesearch")
public class VenueSearchServiceApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(VenueSearchServiceApplication.class, args);
    }
}
