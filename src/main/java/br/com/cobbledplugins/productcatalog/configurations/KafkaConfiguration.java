package br.com.cobbledplugins.productcatalog.configurations;

import br.com.cobbledplugins.productcatalog.messaging.CategoryClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@ConditionalOnProperty(value = "spring.kafka.enabled")
@EnableKafka
@Configuration
public class KafkaConfiguration {

  @Bean
  public NewTopic categoryTopic() {
    return new NewTopic(CategoryClient.TOPIC_NAME, 1, (short) 1);
  }

}
