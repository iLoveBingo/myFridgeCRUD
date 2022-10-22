package com.ilovebingo.myfridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public List<Product> getAll() {
        return productRepository.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") int id) {
        return productRepository.getProductById(id);
    }

    @PostMapping("")
    public boolean addProducts(@RequestBody List<Product> productList) {
        return productRepository.addProducts(productList);
    }

    @PutMapping("/{id}")
    public boolean fullyUpdateProduct(@PathVariable("id") int id, @RequestBody Product updatedProduct) {
        Product tmpProduct = productRepository.getProductById(id);
        if(tmpProduct == null) { return false; }
        tmpProduct.setName(updatedProduct.getName());
        tmpProduct.setWeight(updatedProduct.getWeight());
        if (tmpProduct.getWeight() == 0) { return false; }

        productRepository.updateProductById(tmpProduct);
        return true;
    }

    @PatchMapping("/{id}")
    public boolean partialUpdateProduct(@PathVariable("id") int id, @RequestBody Product updatedProduct) {
        Product tmpProduct = productRepository.getProductById(id);
        if(tmpProduct == null) { return false; }
        if(updatedProduct.getName() != null) { tmpProduct.setName(updatedProduct.getName()); }
        if(updatedProduct.getWeight() > 0) {
            tmpProduct.setWeight(updatedProduct.getWeight());
        } else {
            productRepository.delete(id);
        }
        productRepository.updateProductById(tmpProduct);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable("id") int id) {
        return productRepository.delete(id);
    }


}
