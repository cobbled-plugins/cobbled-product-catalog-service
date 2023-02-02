package br.com.cobbledplugins.productcatalog.domain.model;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CATEGORY_ID", nullable = false)
  private Category category;

  @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

}
