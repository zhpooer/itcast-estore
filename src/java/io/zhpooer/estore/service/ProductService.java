package io.zhpooer.estore.service;

import java.util.List;

import io.zhpooer.estore.entity.Product;

public interface ProductService {

    void addProduct(Product p);

    List<Product> listAllProducts();

    Product findProduct(int id);

    int findProductNumById(int id);

}
