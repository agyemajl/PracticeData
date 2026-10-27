package com.elkabani.mydb;


import com.elkabani.mydb.entities.Category;
import com.elkabani.mydb.entities.Product;
import com.elkabani.mydb.repositories.CategoryRepository;
import com.elkabani.mydb.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class MyDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDbApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {

        return args -> {

            Category electronics =
                    categoryRepository.save(new Category("Electronics"));

            Product camera = productRepository.save(
                    new Product(
                            "Camera",
                            new BigDecimal("499.99"),
                            electronics
                    )
            );

            System.out.println("Created product: " + camera.getName());


            Category existingCategory = categoryRepository
                    .findById(electronics.getId())
                    .orElseThrow();

            Product headphones = productRepository.save(
                    new Product(
                            "Headphones",
                            new BigDecimal("79.99"),
                            existingCategory
                    )
            );

            System.out.println("Created product: " + headphones.getName());
            System.out.println("Category: " +
                    headphones.getCategory().getName());


            Long headphonesId = headphones.getId();
            productRepository.deleteById(headphonesId);

            System.out.println("Headphones deleted: " +
                    !productRepository.existsById(headphonesId));

            System.out.println("Final products:");

            productRepository.findAll().forEach(product ->
                    System.out.println(
                            product.getName() + " - " +
                                    product.getPrice()
                    )
            );
        };
    }
}

