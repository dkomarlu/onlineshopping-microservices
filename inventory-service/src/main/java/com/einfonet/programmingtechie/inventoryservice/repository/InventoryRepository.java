package com.einfonet.programmingtechie.inventoryservice.repository;

import com.einfonet.programmingtechie.inventoryservice.dto.InventoryResponse;
import com.einfonet.programmingtechie.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
