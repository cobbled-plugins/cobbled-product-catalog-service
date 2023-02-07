package br.com.cobbledplugins.productcatalog.event.category;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CategoryCreatedEvent extends ApplicationEvent implements CategoryEvent {

  private final Category category;

  @Builder
  private CategoryCreatedEvent(Object source, Category category) {
    super(source);
    this.category = category;
  }

}
