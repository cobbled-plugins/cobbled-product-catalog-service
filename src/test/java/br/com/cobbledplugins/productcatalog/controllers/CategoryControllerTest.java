package br.com.cobbledplugins.productcatalog.controllers;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import br.com.cobbledplugins.productcatalog.services.CategoryService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

  @Mock
  private CategoryService categoryService;

  @Mock
  private Page<Category> categoryPageMock;

  @Mock
  private Category categoryMock;

  @InjectMocks
  private CategoryController categoryController;

  @Test
  @DisplayName("Should return 200 when get all categories")
  void findAll() {
    when(this.categoryService.findAll(any(Pageable.class), any(), any())).thenReturn(this.categoryPageMock);

    ResponseEntity<Page<Category>> response = this.categoryController.findAll(PageRequest.of(0, 10), null, null);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(this.categoryPageMock, response.getBody());

    verify(this.categoryService, times(1)).findAll(any(Pageable.class), any(), any());
  }

  @Test
  @DisplayName("Should return 201 when category is created")
  void create() {
    when(this.categoryService.create(any(Category.class))).thenReturn(this.categoryMock);

    ResponseEntity<Category> response = this.categoryController.create(this.categoryMock);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(this.categoryMock, response.getBody());

    verify(this.categoryService, times(1)).create(any(Category.class));
  }

}
