package br.com.cobbledplugins.productcatalog.services;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import br.com.cobbledplugins.productcatalog.domain.repository.CategoryRepository;
import br.com.cobbledplugins.productcatalog.domain.specification.CategorySpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  @Transactional
  public Page<Category> findAll(Pageable pageable, String name, String description) {
    return this.categoryRepository.findAll(
      CategorySpecifications.nameLike(name)
        .and(CategorySpecifications.descriptionLike(description)),
      pageable
    );
  }

  @Transactional
  public Category create(Category category) {
    return this.categoryRepository.save(category);
  }

}
