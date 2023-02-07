package br.com.cobbledplugins.productcatalog.messaging;

import br.com.cobbledplugins.productcatalog.domain.model.Product;
import br.com.cobbledplugins.productcatalog.event.product.ProductCreatedEvent;
import br.com.cobbledplugins.productcatalog.event.product.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductClient {

  public static String TOPIC_NAME = "CATALOG-PRODUCT";

  private final KafkaTemplate<UUID, Product> client;

  public void publish(Product product) {
    this.client.send(TOPIC_NAME, product.getId(), product);
  }

  @EventListener({ ProductCreatedEvent.class })
  public void onApplicationEvent(ProductEvent event) {
    this.publish(event.getProduct());
  }

}
