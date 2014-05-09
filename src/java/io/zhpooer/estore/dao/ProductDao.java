package io.zhpooer.estore.dao;

import java.util.List;

import io.zhpooer.estore.entity.Product;

public interface ProductDao {

    void insert(Product p);

    List<Product> listAll();

    Product findById(int id);

    int findProductNumById(int id);

}
