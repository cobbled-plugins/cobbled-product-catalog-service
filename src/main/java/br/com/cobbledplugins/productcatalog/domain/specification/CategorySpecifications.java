package br.com.cobbledplugins.productcatalog.domain.specification;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecifications {

  public static Specification<Category> nameLike(String name) {
    return (root, query, builder) -> builder.like(
      builder.upper(root.get("name")),
      "%" + name.toUpperCase() + "%"
    );
  }

  public static Specification<Category> descriptionLike(String description) {
    return (root, query, builder) -> builder.like(
      builder.upper(root.get("description")),
      "%" + description.toUpperCase() + "%"
    );
  }

}
