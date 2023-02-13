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

  @InjectMocks
  private CategoryController categoryController;

  @Mock
  private CategoryService categoryService;

  @Mock
  private Page<Category> categoryPageMock;

  @Mock
  private Category categoryMock;

  @Test
  @DisplayName("Should return 200 when get all categories")
  void shouldReturn200WhenGetAllCategories() {
    when(this.categoryService.findAll(any(Pageable.class), any(), any())).thenReturn(this.categoryPageMock);

    ResponseEntity<Page<Category>> response = this.categoryController.findAll(PageRequest.of(0, 10), null, null);

    verify(this.categoryService, times(1)).findAll(any(Pageable.class), any(), any());

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(this.categoryPageMock, response.getBody());
  }

  @Test
  @DisplayName("Should return 201 when create category")
  void shouldReturn201WhenCreateCategory() {
    when(this.categoryService.create(any(Category.class))).thenReturn(this.categoryMock);

    ResponseEntity<Category> response = this.categoryController.create(this.categoryMock);

    verify(this.categoryService, times(1)).create(any(Category.class));

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(this.categoryMock, response.getBody());
  }

}
