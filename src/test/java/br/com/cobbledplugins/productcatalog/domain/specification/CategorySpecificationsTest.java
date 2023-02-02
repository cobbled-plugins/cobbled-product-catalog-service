package br.com.cobbledplugins.productcatalog.domain.specification;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import br.com.cobbledplugins.productcatalog.domain.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategorySpecificationsTest {

  @Autowired
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    this.categoryRepository.save(Category.builder()
      .name("Testing")
      .description("Testing description")
      .build());

    this.categoryRepository.save(Category.builder()
      .name("Another Thing")
      .description("Another Thing description")
      .build());
  }

  @Test
  @DisplayName("Should return only categories with name like 'testing'")
  void shouldReturnOnlyCategoriesWithNameLikeTesting() {
    List<Category> categories = this.categoryRepository.findAll(CategorySpecifications.nameLike("testing"));

    assertThat(categories).hasSize(1);
    assertThat(categories).extracting(Category::getName).containsExactlyInAnyOrder("Testing");
  }

  @Test
  @DisplayName("Should not return categories with name like 'None'")
  void shouldNotReturnCategoriesWithNameLikeNone() {
    List<Category> categories = this.categoryRepository.findAll(CategorySpecifications.nameLike("None"));

    assertThat(categories).isEmpty();
  }

  @Test
  @DisplayName("Should return only categories with description like 'testing'")
  void shouldReturnOnlyCategoriesWithDescriptionLikeTesting() {
    List<Category> categories = this.categoryRepository.findAll(CategorySpecifications.descriptionLike("testing"));

    assertThat(categories).hasSize(1);
    assertThat(categories).extracting(Category::getName).containsExactlyInAnyOrder("Testing");
  }

  @Test
  @DisplayName("Should not return categories with description like 'None'")
  void shouldNotReturnCategoriesWithDescriptionLikeNone() {
    List<Category> categories = this.categoryRepository.findAll(CategorySpecifications.descriptionLike("None"));

    assertThat(categories).isEmpty();
  }

}
