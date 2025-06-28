package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.model.*;
import com.elec5620.intelligentfinancial.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToFavorites(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found"));

        Favorite favorite = new Favorite();
        favorite.setCustomer(customer);
        favorite.setProduct(product);
        favoriteRepository.save(favorite);
    }

    public List<ProductDTO> getFavorites(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found"));
        List<Favorite> favorites = favoriteRepository.findByCustomerId(customerId);

        return favorites.stream()
                .map(fav -> new ProductDTO(fav.getProduct()))
                .collect(Collectors.toList());
    }
}
