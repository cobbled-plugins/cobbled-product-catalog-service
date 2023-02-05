package br.com.cobbledplugins.productcatalog.controllers;

import br.com.cobbledplugins.productcatalog.domain.model.Product;
import br.com.cobbledplugins.productcatalog.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

  @Mock
  private ProductService productService;

  @Mock
  private Page<Product> productPageMock;

  @Mock
  private Product productMock;

  @InjectMocks
  private ProductController productController;

  @Test
  @DisplayName("Should return 200 when get all products")
  void findAll() {
    when(this.productService.findAll(any(Pageable.class), any(), any())).thenReturn(this.productPageMock);

    ResponseEntity<Page<Product>> response = this.productController.findAll(PageRequest.of(0, 10), null, null);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(this.productPageMock, response.getBody());

    verify(this.productService, times(1)).findAll(any(Pageable.class), any(), any());
  }

  @Test
  @DisplayName("Should return 201 when product is created")
  void create() {
    when(this.productService.create(any(Product.class))).thenReturn(this.productMock);

    ResponseEntity<Product> response = this.productController.create(this.productMock);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(this.productMock, response.getBody());

    verify(this.productService, times(1)).create(any(Product.class));
  }

}
