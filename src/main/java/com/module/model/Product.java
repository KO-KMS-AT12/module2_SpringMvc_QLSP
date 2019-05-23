package com.module.model;

public class Product {
  private Integer id;
  private String name;
  private String description;
  private Integer quantity;
  private Double price;

  public Product() {

  }

  public Product(Integer id) {
    super();
    this.id = id;
  }

  public Product(Integer id, String name, String description, Integer quantity, Double price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.quantity = quantity;
    this.price = price;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
