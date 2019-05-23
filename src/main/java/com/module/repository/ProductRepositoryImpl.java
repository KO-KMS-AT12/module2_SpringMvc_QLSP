package com.module.repository;

import com.module.connect.DBConnect;
import com.module.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setNamedParameterJdbcTemplate(
      NamedParameterJdbcTemplate namedParameterJdbcTemplate)
      throws DataAccessException {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public List<Product> listAllProducts() {
    String sql = "SELECT * FROM products";

    List<Product> list = namedParameterJdbcTemplate
        .query(sql, getSqlParameterByModel(null), new ProductMapper());

    return list;
  }

  private SqlParameterSource getSqlParameterByModel(Product product) {
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();

    if (product != null) {
      parameterSource.addValue("id", product.getId());
      parameterSource.addValue("name", product.getName());
      parameterSource.addValue("description", product.getDescription());
      parameterSource.addValue("quantity", product.getQuantity());
      parameterSource.addValue("price", product.getPrice());
    }

    return parameterSource;
  }

  private static final class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
      Product product = new Product();
      product.setId(rs.getInt("id"));
      product.setName(rs.getString("name"));
      product.setDescription(rs.getString("description"));
      product.setQuantity(rs.getInt("quantity"));
      product.setPrice(rs.getDouble("price"));

      return product;
    }
  }

  @Override
  public void addProduct(Product product) {
    String sql =
        "INSERT INTO products(name,description,quantity,price) VALUES (:name,:description,:quantity,:price)";

    namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(product));
  }

  @Override
  public void updateProduct(Product product) {
    String sql =
        "UPDATE products SET name = :name,description=:description,quantity=:quantity,price=:price WHERE id=:id";

    namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(product));
  }

  @Override
  public void deleteProduct(int id) {
    String sql = "DELETE FROM products WHERE id=:id";

    namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(new Product(id)));
  }

  @Override
  public Product findProductById(int id) {
    String sql = "SELECT * FROM products WHERE id=:id";

    return namedParameterJdbcTemplate.queryForObject(sql,
        getSqlParameterByModel(new Product(id)),
        new ProductMapper());
  }
}
