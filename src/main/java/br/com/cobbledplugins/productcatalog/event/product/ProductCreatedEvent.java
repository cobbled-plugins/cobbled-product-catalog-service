package br.com.cobbledplugins.productcatalog.event.product;

import br.com.cobbledplugins.productcatalog.domain.model.Product;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ProductCreatedEvent extends ApplicationEvent implements ProductEvent {

  private final Product product;

  @Builder
  private ProductCreatedEvent(Object source, Product product) {
    super(source);
    this.product = product;
  }

}
