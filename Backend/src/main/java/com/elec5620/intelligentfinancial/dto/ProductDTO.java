package com.elec5620.intelligentfinancial.dto;

import com.elec5620.intelligentfinancial.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor  // 无参构造函数
@AllArgsConstructor // 全参构造函数
public class ProductDTO {
    private Long id;
    private String productName;
    private String supplierName;
    private String productInfo;
    private Long bankId;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.supplierName = product.getSupplierName();
        this.productInfo = product.getProductInfo();
    }
}
