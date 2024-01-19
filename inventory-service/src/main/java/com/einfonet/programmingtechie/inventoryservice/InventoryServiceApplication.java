package com.einfonet.programmingtechie.inventoryservice;

import com.einfonet.programmingtechie.inventoryservice.model.Inventory;
import com.einfonet.programmingtechie.inventoryservice.repository.InventoryRepository;
import com.einfonet.programmingtechie.inventoryservice.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//        return args -> {
//            Inventory inv1 = new Inventory();
//            inv1.setSkuCode("iphone_13");
//            inv1.setQuantity(100);
//
//            Inventory inv2 = new Inventory();
//            inv2.setSkuCode("iphone_red");
//            inv2.setQuantity(0);
//
//            inventoryRepository.save(inv1);
//            inventoryRepository.save(inv2);
//        };
//    }
}
