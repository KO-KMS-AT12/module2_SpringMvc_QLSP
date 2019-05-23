package com.module.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
  private Integer id;
  private String name;
  private String description;
  private Integer quantity;
  private Double price;
  private MultipartFile image;

  public ProductForm(Integer id, String name, String description, Integer quantity, Double price, MultipartFile image) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.quantity = quantity;
    this.price = price;
    this.image = image;
  }

  public ProductForm() {

  }

  public ProductForm(Integer id) {
    super();
    this.id = id;
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

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }
}
