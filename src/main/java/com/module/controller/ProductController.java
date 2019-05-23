package com.module.controller;


import com.module.model.Product;
import com.module.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ModelAndView listProduct() {
    ModelAndView model = new ModelAndView("/product_page");
    List<Product> list = productService.listAllProducts();
    model.addObject("listProduct", list);
    return model;
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ModelAndView addProduct() {
    ModelAndView model = new ModelAndView("/product_form");
    Product product = new Product();
    model.addObject("productFrom", product);

    return model;
  }

  @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
  public ModelAndView updateProduct(@PathVariable("id") int id) {
    ModelAndView model = new ModelAndView("/product_form");
    Product product = productService.findProductById(id);
    model.addObject("productFrom", product);

    return model;
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ModelAndView list(@ModelAttribute("productFrom") Product product) {

    if (product != null && product.getId() != null) {
      productService.updateProduct(product);
    } else {
      productService.addProduct(product);
    }

    return new ModelAndView("redirect:/product/list");
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public ModelAndView deleteProduct(@PathVariable("id") int id) {
    productService.deleteProduct(id);

    return new ModelAndView("redirect:/product/list");
  }

}
