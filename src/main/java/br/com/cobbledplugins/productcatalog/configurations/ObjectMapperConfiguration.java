package br.com.cobbledplugins.productcatalog.configurations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ObjectMapperConfiguration {

  @Autowired
  public void customize(ObjectMapper objectMapper) {
    objectMapper
      .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
      .configOverride(BigDecimal.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
  }

}
