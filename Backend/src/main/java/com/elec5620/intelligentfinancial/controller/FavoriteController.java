package com.elec5620.intelligentfinancial.controller;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/{customerId}/favorites")
@CrossOrigin(origins = "http://localhost:3001")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<String> addToFavorites(@PathVariable Long customerId, @RequestBody FavoriteDTO favoriteDTO) {
        try {
            favoriteService.addToFavorites(customerId, favoriteDTO.getProductId());
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added to favorites");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to add product to favorites: " + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getFavorites(@PathVariable Long customerId) {
        List<ProductDTO> favorites = favoriteService.getFavorites(customerId);
        if (favorites.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        }
        return ResponseEntity.ok(favorites);
    }
}
