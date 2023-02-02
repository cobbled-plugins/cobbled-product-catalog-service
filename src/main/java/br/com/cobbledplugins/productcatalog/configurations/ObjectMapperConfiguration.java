package br.com.cobbledplugins.productcatalog.configurations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ObjectMapperConfiguration {

  @Autowired
  public void customize(ObjectMapper objectMapper) {
    objectMapper
      .configOverride(BigDecimal.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
  }

}
