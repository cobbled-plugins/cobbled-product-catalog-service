package br.com.cobbledplugins.productcatalog.services;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import br.com.cobbledplugins.productcatalog.domain.repository.CategoryRepository;
import br.com.cobbledplugins.productcatalog.domain.specification.CategorySpecifications;
import br.com.cobbledplugins.productcatalog.event.category.CategoryCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final ApplicationEventPublisher eventPublisher;

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
    Category createdCategory = this.categoryRepository.save(category);
    this.publishCategoryCreatedEvent(createdCategory);
    return createdCategory;
  }

  private void publishCategoryCreatedEvent(Category category) {
    this.eventPublisher.publishEvent(CategoryCreatedEvent.builder()
        .source(this)
        .category(category)
        .build());
  }

}
