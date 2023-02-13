package br.com.cobbledplugins.productcatalog.services;

import br.com.cobbledplugins.productcatalog.domain.model.Product;
import br.com.cobbledplugins.productcatalog.domain.repository.ProductRepository;
import br.com.cobbledplugins.productcatalog.event.product.ProductCreatedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @InjectMocks
  private ProductService productService;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ApplicationEventPublisher eventPublisher;

  @Mock
  private Page<Product> productPageMock;

  @Mock
  private Product productMock;

  @SuppressWarnings("unchecked")
  @Test
  @DisplayName("Should return a page of products when find all")
  void shouldReturnCategoriesWhenFindAll() {
    when(this.productRepository.findAll(any(Specification.class), any(Pageable.class)))
      .thenReturn(this.productPageMock);

    Page<Product> result = this.productService.findAll(PageRequest.of(0, 10), null, null);

    verify(this.productRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));

    assertEquals(this.productPageMock, result);
  }

  @Test
  @DisplayName("Should return a product when create")
  void shouldReturnProductWhenCreate() {
    when(this.productRepository.save(any(Product.class)))
      .thenReturn(this.productMock);

    Product result = this.productService.create(this.productMock);

    verify(this.productRepository, times(1)).save(any(Product.class));
    verify(this.eventPublisher, times(1)).publishEvent(any(ProductCreatedEvent.class));

    assertEquals(this.productMock, result);
  }

}
