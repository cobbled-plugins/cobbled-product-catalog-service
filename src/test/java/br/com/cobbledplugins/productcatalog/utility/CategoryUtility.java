package br.com.cobbledplugins.productcatalog.utility;

import br.com.cobbledplugins.productcatalog.domain.model.Category;

public final class CategoryUtility {

  public static Category createCategory() {
    return Category.builder()
      .name("Testing")
      .description("Testing description")
      .build();
  }

}
