package com.module.controller;

import com.module.model.Product;
import com.module.model.ProductForm;
import com.module.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.module.common.Constant.UPLOAD_LOCATION;

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

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public ModelAndView deleteProduct(@PathVariable("id") Integer id) {
    productService.deleteProduct(id);

    return new ModelAndView("redirect:/product/list");
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ModelAndView addProduct() {
    ModelAndView model = new ModelAndView("/product_form");
    ProductForm productForm = new ProductForm();
    model.addObject("productFrom", productForm);

    return model;
  }

  @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
  public ModelAndView updateProduct(@PathVariable("id") Integer id) {
    ModelAndView model = new ModelAndView("/product_form");
    Product product = productService.findProductById(id);
    model.addObject("productFrom", product);

    return model;
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ModelAndView list(@ModelAttribute("productFrom") ProductForm productform) {
    // lay ten file
    MultipartFile multipartFile = productform.getImage();
    String fileName = multipartFile.getOriginalFilename();

    // luu file len server
    try {
      FileCopyUtils.copy(productform.getImage().getBytes(), new File(UPLOAD_LOCATION + fileName));
    } catch (IOException ex) {
      ex.printStackTrace();
    }

//     tao doi tuong de luu vao db
    Product product = new Product(productform.getId(), productform.getName(),productform.getDescription(),productform.getQuantity(), productform.getPrice(), fileName);

//     luu vao db
    productService.addProduct(product);

//    if (productform != null && productform.getId() != null) {
//      productService.updateProduct(productform);
//    } else {
//      productService.addProduct(productform);
//    }

    return new ModelAndView("redirect:/product/list");
  }

}
