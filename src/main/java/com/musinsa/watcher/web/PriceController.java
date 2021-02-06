package com.musinsa.watcher.web;

import com.musinsa.watcher.service.PriceService;
import com.musinsa.watcher.web.dto.product.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
@RestController
public class PriceController {

  private final PriceService priceService;

  @GetMapping("/api/v1/product/price")
  public Page<PriceResponseDto> findByPostId(@RequestParam int id,
      @RequestParam(defaultValue = "30") int count) {
    return priceService
        .findByProductId(id, PageRequest.of(0, count, Sort.by("createdDate").descending()));
  }
}
