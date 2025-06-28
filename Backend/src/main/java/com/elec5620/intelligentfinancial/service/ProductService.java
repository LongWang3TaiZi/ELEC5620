package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.ProductDTO;
import com.elec5620.intelligentfinancial.model.Bank;
import com.elec5620.intelligentfinancial.model.Product;
import com.elec5620.intelligentfinancial.repository.BankRepository;
import com.elec5620.intelligentfinancial.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BankRepository bankRepository;

    public ProductService(ProductRepository productRepository, BankRepository bankRepository) {
        this.productRepository = productRepository;
        this.bankRepository = bankRepository;
    }

    /**
     * 创建新的产品。
     */
    public Product createProduct(ProductDTO productDTO) {
        // 查找关联的 Bank 实体
        Bank bank = bankRepository.findById(productDTO.getBankId())
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));

        // 使用 Lombok 自动生成的 setter 创建 Product 实例
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setSupplierName(productDTO.getSupplierName());
        product.setProductInfo(productDTO.getProductInfo());
        product.setBank(bank);

        // 保存并返回 Product 实例
        return productRepository.save(product);
    }

    /**
     * 根据 bank_id 查询所有产品。
     */
    public List<Product> getProductsByBankId(Long bankId) {
        return productRepository.findByBank_Id(bankId);
    }

    /**
     * 查询所有产品。
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 更新产品信息。
     */
    public Product updateProduct(Long id, ProductDTO productDTO) {
        // 查找现有产品
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // 查找新的 Bank 实体
        Bank bank = bankRepository.findById(productDTO.getBankId())
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));

        // 更新产品信息
        product.setProductName(productDTO.getProductName());
        product.setSupplierName(productDTO.getSupplierName());
        product.setProductInfo(productDTO.getProductInfo());
        product.setBank(bank);

        // 保存并返回更新后的产品
        return productRepository.save(product);
    }

    /**
     * 删除产品。
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }

    /**
     * 产品推荐。
     */
    public List<Product> getRecommendedProducts() {
        return productRepository.findAll();
    }
}
