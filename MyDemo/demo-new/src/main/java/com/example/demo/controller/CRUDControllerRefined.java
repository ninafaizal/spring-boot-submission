package com.example.demo.controller;

import java.util.*;
import lombok.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import com.example.demo.service.ItemServiceAnalysis;
import com.example.demo.util.ResponseEntityUtil;
import com.example.demo.validation.ItemValidation;

@AllArgsConstructor
@RestController
@RequestMapping("/demo/v2")
public class CRUDControllerRefined {

	private final ItemService itemService;
	private final ItemServiceAnalysis itemServiceAnalysis;
	
	private static final String ID_ITEM = "Item with ID ";

//	public CRUDControllerRefined(ItemService itemService, ItemServiceAnalysis itemServiceAnalysis) {
//	    this.itemService = itemService;
//	    this.itemServiceAnalysis = itemServiceAnalysis;
//	}

    // --- CREATE (Auto-generated ID) ---
    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody String newItemName) {
        ItemValidation.validateItemName(newItemName);
        Item item = itemService.createItem(newItemName);
        return ResponseEntityUtil.buildResponse("Item created successfully with ID: " + item.id() + " and data: " + item.value(), HttpStatus.CREATED);
    }

    // --- READ (All Items) ---
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }
    
	 // --- READ (All Items) with "demo" ---
    @GetMapping("/search/demo")
	public ResponseEntity<List<Item>> getAllItemsWithDemo() {
    	return ResponseEntity.ok(itemServiceAnalysis.getAllItemsWithDemo());
	}

    // --- READ (By ID) ---
    @GetMapping("/{id}")
    public ResponseEntity<String> getItemById(@PathVariable String id) {
    	ItemValidation.parseAndValidateLongId(id);
    	
        return itemService.getItemById(Long.valueOf(id))
                .map(item -> ResponseEntityUtil.buildResponse("Found item with ID: " + item.id() + " and data: " + item.value(), HttpStatus.OK))
                .orElseGet(() -> ResponseEntityUtil.buildResponse(CRUDControllerRefined.ID_ITEM + id + " not found.", HttpStatus.NOT_FOUND));
    }

    // --- UPDATE ---
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody String updatedName) {
        ItemValidation.validateItemName(updatedName);
        return itemService.updateItem(id, updatedName)
                .map(item -> ResponseEntityUtil.buildResponse("Item with ID: " + item.id() + " updated successfully to: " + item.value(), HttpStatus.OK))
                .orElseGet(() -> ResponseEntityUtil.buildResponse("Item with ID: " + id + " not found for update.", HttpStatus.NOT_FOUND));
    }

    // --- DELETE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id)
                ? ResponseEntityUtil.buildResponse("Item with ID: " + id + " deleted successfully.", HttpStatus.NO_CONTENT)
                : ResponseEntityUtil.buildResponse("Item with ID: " + id + " not found for deletion.", HttpStatus.NOT_FOUND);
    }


}
