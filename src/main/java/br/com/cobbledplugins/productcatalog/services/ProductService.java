package br.com.cobbledplugins.productcatalog.services;

import br.com.cobbledplugins.productcatalog.domain.model.Product;
import br.com.cobbledplugins.productcatalog.domain.repository.ProductRepository;
import br.com.cobbledplugins.productcatalog.domain.specification.ProductSpecifications;
import br.com.cobbledplugins.productcatalog.event.product.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public Page<Product> findAll(Pageable pageable, String name, String description) {
    return this.productRepository.findAll(
      ProductSpecifications.nameLike(name)
        .and(ProductSpecifications.descriptionLike(description)),
      pageable
    );
  }

  @Transactional
  public Product create(Product product) {
    Product createdProduct = this.productRepository.save(product);
    this.publishProductCreatedEvent(createdProduct);
    return createdProduct;
  }

  private void publishProductCreatedEvent(Product product) {
    this.eventPublisher.publishEvent(ProductCreatedEvent.builder()
      .source(this)
      .product(product)
      .build());
  }

}
