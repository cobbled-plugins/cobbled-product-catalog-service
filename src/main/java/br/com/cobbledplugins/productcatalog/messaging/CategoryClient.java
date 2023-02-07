package br.com.cobbledplugins.productcatalog.messaging;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import br.com.cobbledplugins.productcatalog.event.category.CategoryCreatedEvent;
import br.com.cobbledplugins.productcatalog.event.category.CategoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryClient {

  public static String TOPIC_NAME = "CATALOG-CATEGORY";

  private final KafkaTemplate<UUID, Category> client;

  public void publish(Category category) {
    this.client.send(TOPIC_NAME, category.getId(), category);
  }

  @EventListener({ CategoryCreatedEvent.class })
  public void onApplicationEvent(CategoryEvent event) {
    this.publish(event.getCategory());
  }

}
