package br.com.cobbledplugins.productcatalog.domain.specification;

import br.com.cobbledplugins.productcatalog.domain.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

  public static Specification<Product> nameLike(String name) {
    if (name == null) return Specification.where(null);

    return (root, query, builder) -> builder.like(
      builder.upper(root.get("name")),
      "%" + name.toUpperCase() + "%"
    );
  }

  public static Specification<Product> descriptionLike(String description) {
    if (description == null) return Specification.where(null);

    return (root, query, builder) -> builder.like(
      builder.upper(root.get("description")),
      "%" + description.toUpperCase() + "%"
    );
  }

}
