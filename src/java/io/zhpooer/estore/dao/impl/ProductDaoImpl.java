package io.zhpooer.estore.dao.impl;

import io.zhpooer.estore.dao.ProductDao;
import io.zhpooer.estore.entity.Product;
import io.zhpooer.estore.utils.TransactionManager;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class ProductDaoImpl implements ProductDao {
    QueryRunner qr = new QueryRunner();

    @Override
    public void insert(Product p) {
        String sql = "insert into product(name, category, pnum, "
                + "marketprice, estoreprice, imgurl, description) "
                + "values (?,?,?,?,?,?,?)";
        try {
            qr.update(TransactionManager.getConnection(), sql, p.getName(),
                    p.getCategory(), p.getPnum(), p.getMarketprice(),
                    p.getEstoreprice(), p.getImgurl(), p.getDescription());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> listAll() {
        String sql = "select * from product";
        try {
            return qr.query(TransactionManager.getConnection(), sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(int id) {
        String sql = "select * from product where id=?";
        try {
            return qr.query(TransactionManager.getConnection(), sql, new BeanHandler<>(Product.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findProductNumById(int id) {
        String sql = "select * from product where id=?";
        try {
            return qr.query(TransactionManager.getConnection(), sql, new ScalarHandler<Integer>("pnum"), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
