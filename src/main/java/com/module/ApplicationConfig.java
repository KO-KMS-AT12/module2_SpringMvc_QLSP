package com.module;

import com.module.repository.ProductRepository;
import com.module.repository.ProductRepositoryImpl;
import com.module.service.ProductService;
import com.module.service.ProductServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


import javax.sql.DataSource;

import static com.module.common.Constant.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.module"})
public class ApplicationConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Autowired
  DataSource dataSource;

  @Bean
  public NamedParameterJdbcTemplate geNamedParameterJdbcTemplate() {
    return new NamedParameterJdbcTemplate(dataSource);
  }

  @Bean
  public DriverManagerDataSource getDataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(JDBC_DRIVER);
    ds.setUrl(URL_DB);
    ds.setUsername(USER);
    ds.setPassword(PASSWORD);

    return ds;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

  //Thymeleaf Configuration
  @Bean
  public SpringResourceTemplateResolver templateResolver() {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(applicationContext);
    templateResolver.setPrefix("/WEB-INF/views");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    return templateResolver;
  }

  @Bean
  public TemplateEngine templateEngine() {
    TemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    return templateEngine;
  }

  @Bean
  public ThymeleafViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    return viewResolver;
  }

  @Bean
  public ProductRepository productRepository() {
    return new ProductRepositoryImpl();
  }

  @Bean
  public ProductService productService() {
    return new ProductServiceImpl();
  }

}