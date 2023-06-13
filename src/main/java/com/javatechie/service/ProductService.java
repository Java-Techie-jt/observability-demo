package com.javatechie.service;

import com.javatechie.entity.Product;
import com.javatechie.repository.ProductRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Observed(name = "add.products")
    public Product addProduct(Product product) {
        return repository.save(product);

    }

    @Observed(name = "get.product")
    public Product getProduct(int id) {
        return repository.findById(id).get();
    }

    @Observed(name = "get.products")
    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product updateProduct(int id, Product productRequest) {
        // get the product from DB by id
        // update with new value getting from request
        Product existingProduct = repository.findById(id).get(); // DB
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(productRequest.getProductType());
        return repository.save(existingProduct);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product deleted";
    }



}
