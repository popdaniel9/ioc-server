package com.onlineshop.onlineshop.controllers;

import com.onlineshop.onlineshop.exceptions.ResourceNotFoundException;
import com.onlineshop.onlineshop.model.ProdusDto;
import com.onlineshop.onlineshop.services.ProdusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produse")
public class ProdusController {

    private final ProdusService produsService;

    public ProdusController(ProdusService produsService) {
        this.produsService = produsService;
    }

    @GetMapping("/categorie/{categ}")
    public ResponseEntity getProductsByCategory(@PathVariable String categ) {
        return ResponseEntity.ok(produsService.getProductsByCategory(categ));
    }

    @GetMapping("/ruleta")
    public ResponseEntity getProductsForDiscount() {
        return ResponseEntity.ok(produsService.getProductsForDiscount());
    }

    @PostMapping
    public ResponseEntity add(@RequestBody ProdusDto produsDto) {
        try {
            return ResponseEntity.ok(produsService.add(produsDto));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), ex.getHttpStatus());
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(produsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produsService.findById(id));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), ex.getHttpStatus());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produsService.delete(id));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), ex.getHttpStatus());
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ProdusDto produsDto) {
        try {
            return ResponseEntity.ok(produsService.update(produsDto));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), ex.getHttpStatus());
        }
    }

}
