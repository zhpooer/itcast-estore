package io.zhpooer.estore.service.impl;

import java.util.List;

import io.zhpooer.estore.dao.ProductDao;
import io.zhpooer.estore.dao.impl.ProductDaoImpl;
import io.zhpooer.estore.entity.Product;
import io.zhpooer.estore.service.ProductService;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    
    @Override
    public void addProduct(Product p) {
        productDao.insert(p);
    }

    @Override
    public List<Product> listAllProducts() {
        return productDao.listAll();
    }

    @Override
    public Product findProduct(int id) {
        return productDao.findById(id);
    }

    @Override
    public int findProductNumById(int id) {
        return productDao.findProductNumById(id);
    }

}
