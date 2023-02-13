package br.com.cobbledplugins.productcatalog.services;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import br.com.cobbledplugins.productcatalog.domain.repository.CategoryRepository;
import br.com.cobbledplugins.productcatalog.event.category.CategoryCreatedEvent;
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
class CategoryServiceTest {

  @InjectMocks
  private CategoryService categoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private ApplicationEventPublisher eventPublisher;

  @Mock
  private Page<Category> categoryPageMock;

  @Mock
  private Category categoryMock;

  @SuppressWarnings("unchecked")
  @Test
  @DisplayName("Should return a page of categories when find all")
  void shouldReturnCategoriesWhenFindAll() {
    when(this.categoryRepository.findAll(any(Specification.class), any(Pageable.class)))
      .thenReturn(this.categoryPageMock);

    Page<Category> result = this.categoryService.findAll(PageRequest.of(0, 10), null, null);

    verify(this.categoryRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));

    assertEquals(this.categoryPageMock, result);
  }

  @Test
  @DisplayName("Should return a category when create")
  void shouldReturnCategoryWhenCreate() {
    when(this.categoryRepository.save(any(Category.class)))
      .thenReturn(this.categoryMock);

    Category result = this.categoryService.create(this.categoryMock);

    verify(this.categoryRepository, times(1)).save(any(Category.class));
    verify(this.eventPublisher, times(1)).publishEvent(any(CategoryCreatedEvent.class));

    assertEquals(this.categoryMock, result);
  }

}
