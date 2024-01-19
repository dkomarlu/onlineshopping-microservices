package com.einfonet.programmingtechie.inventoryservice.service;

import com.einfonet.programmingtechie.inventoryservice.dto.InventoryResponse;
import com.einfonet.programmingtechie.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock (List<String> skuCodes) {
       return inventoryRepository.findBySkuCodeIn(skuCodes)
               .stream()
               .map(inventory -> InventoryResponse.builder()
                           .skuCode(inventory.getSkuCode())
                           .isInStock(inventory.getQuantity() > 0)
                           .build()
               ).toList();
    }
}
