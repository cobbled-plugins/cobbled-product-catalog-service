package br.com.cobbledplugins.productcatalog.domain.specification;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import br.com.cobbledplugins.productcatalog.domain.model.Product;
import br.com.cobbledplugins.productcatalog.domain.repository.CategoryRepository;
import br.com.cobbledplugins.productcatalog.domain.repository.ProductRepository;
import br.com.cobbledplugins.productcatalog.utility.CategoryUtility;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductSpecificationsTest {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    Category category = this.categoryRepository.save(CategoryUtility.createCategory());

    this.productRepository.save(Product.builder()
      .name("Testing")
      .description("Testing description")
      .categoryId(category.getId())
      .price(BigDecimal.valueOf(9.90))
      .build());

    this.productRepository.save(Product.builder()
      .name("Another Thing")
      .description("Another Thing description")
      .categoryId(category.getId())
      .price(BigDecimal.valueOf(19.90))
      .build());
  }

  @Test
  @DisplayName("Should return only products with name like 'testing'")
  void shouldReturnOnlyProductsWithNameLikeTesting() {
    List<Product> categories = this.productRepository.findAll(ProductSpecifications.nameLike("testing"));

    assertThat(categories).hasSize(1);
    assertThat(categories).extracting(Product::getName).containsExactlyInAnyOrder("Testing");
  }

  @Test
  @DisplayName("Should not return products with name like 'None'")
  void shouldNotReturnProductsWithNameLikeNone() {
    List<Product> categories = this.productRepository.findAll(ProductSpecifications.nameLike("None"));

    assertThat(categories).isEmpty();
  }

  @Test
  @DisplayName("Should return only products with description like 'testing'")
  void shouldReturnOnlyProductsWithDescriptionLikeTesting() {
    List<Product> categories = this.productRepository.findAll(ProductSpecifications.descriptionLike("testing"));

    assertThat(categories).hasSize(1);
    assertThat(categories).extracting(Product::getName).containsExactlyInAnyOrder("Testing");
  }

  @Test
  @DisplayName("Should not return products with description like 'None'")
  void shouldNotReturnProductsWithDescriptionLikeNone() {
    List<Product> categories = this.productRepository.findAll(ProductSpecifications.descriptionLike("None"));

    assertThat(categories).isEmpty();
  }

}
