package com.ilovebingo.myfridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("SELECT id, name, weight, insertDate FROM product;",
                BeanPropertyRowMapper.newInstance(Product.class));
    }

    public Product getProductById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, weight, insertDate FROM product WHERE id=?",
                BeanPropertyRowMapper.newInstance(Product.class), id);
    }

    public boolean addProducts(List<Product> productList) {
        productList.forEach(movie -> jdbcTemplate
                .update("INSERT INTO product(name,weight) VALUES (?,?);",
                        movie.getName(), movie.getWeight())
                );
        return true;
    }

    public boolean updateProductById(Product product) {
        jdbcTemplate.update("UPDATE product SET name=?, weight=? WHERE id=?",
                product.getName(), product.getWeight(), product.getId());
        return true;
    }

    public boolean delete(int id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
        return true;
    }
}
