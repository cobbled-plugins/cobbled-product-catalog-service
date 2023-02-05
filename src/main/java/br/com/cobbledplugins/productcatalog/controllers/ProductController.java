package br.com.cobbledplugins.productcatalog.controllers;

import br.com.cobbledplugins.productcatalog.domain.model.Product;
import br.com.cobbledplugins.productcatalog.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<Page<Product>> findAll(
    @PageableDefault Pageable pageable,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String description
  ) {
    return ResponseEntity
      .ok(this.productService.findAll(pageable, name, description));
  }

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product product) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(this.productService.create(product));
  }

}
