package br.com.cobbledplugins.productcatalog.controllers;

import br.com.cobbledplugins.productcatalog.domain.model.Category;
import br.com.cobbledplugins.productcatalog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Page<Category>> findAll(@PageableDefault Pageable pageable) {
    return ResponseEntity
      .ok(this.categoryService.findAll(pageable));
  }

  @PostMapping
  public ResponseEntity<Category> create(@RequestBody Category category) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(this.categoryService.create(category));
  }

}
