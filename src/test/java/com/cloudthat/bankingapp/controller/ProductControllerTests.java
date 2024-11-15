
package com.cloudthat.bankingapp.controller;

import com.cloudthat.bankingapp.entity.Category;
import com.cloudthat.bankingapp.model.ProductModel;
import com.cloudthat.bankingapp.model.ProductRequest;
import com.cloudthat.bankingapp.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateProductShouldReturn201Created() throws Exception{

        // Create the requestobject
        ProductRequest productRequest = new ProductRequest("Onion", 23.5, Category.GROCERY);

        // Create the request body from the product Request
        String requestBody = objectMapper.writeValueAsString(productRequest);

        // use mockito to mock method call
        Mockito.when(productService.saveProduct(productRequest)).thenReturn(new ProductModel());

        mockMvc.perform(post("/products").contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print());
    }

}
