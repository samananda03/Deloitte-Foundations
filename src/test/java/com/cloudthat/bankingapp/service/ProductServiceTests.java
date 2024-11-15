
package com.cloudthat.bankingapp.service;

import com.cloudthat.bankingapp.repository.ProductRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

public class ProductServiceTests {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

//    @Test
//    public void whenSaveProductShouldReturnUser() throws Exception{
//        ProductRequest product = new ProductRequest("Onion", 23.5, Category.GROCERY);
//
//        Mockito.when(productRepository.save(any(Product.class))).thenReturn(new Product());
//
//        Product product1 = productService.saveProduct(product);
//    }
}
