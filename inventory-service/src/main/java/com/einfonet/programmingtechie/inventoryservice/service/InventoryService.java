package com.einfonet.programmingtechie.inventoryservice.service;

import com.einfonet.programmingtechie.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock (String skuCode) {
       return inventoryRepository.findBySkuCode(skuCode).isPresent();

    }
}
