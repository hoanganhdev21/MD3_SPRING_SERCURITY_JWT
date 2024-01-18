package com.securityjwt.model.dto.response;

import com.securityjwt.model.entity.Category;
import com.securityjwt.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private Long id;
    private String productName;
    private Double price;
    private String image;
    private Category category;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.image = product.getImage();
        this.category = product.getCategory();
    }
}
