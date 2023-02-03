package br.com.cobbledplugins.productcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@SuperBuilder
@Data
@NoArgsConstructor
@Table(name = "PRODUCTS")
@Entity
public class Product {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "ID", unique = true, nullable = false, updatable = false)
  private UUID id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(name = "CATEGORY_ID", nullable = false)
  private UUID categoryId;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
  private Category category;

  @Column(name = "PRICE", nullable = false, precision = 4, scale = 2)
  private BigDecimal price;

}
