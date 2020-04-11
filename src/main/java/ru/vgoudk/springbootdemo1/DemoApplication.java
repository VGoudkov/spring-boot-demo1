package ru.vgoudk.springbootdemo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class DemoApplication {

    Logger logger = LoggerFactory.getLogger(DemoApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            logger.info("Started, begin persistence");
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            logger.info("Finished persistence");

            logger.info("Staring query");
            repository.findByLastName("Bauer").stream()
                    .map(Customer::toString)
                    .forEach(logger::info);
            logger.info("Finished query");

            logger.info("Clearing db");
            repository.deleteAll();
        };
    }
}
