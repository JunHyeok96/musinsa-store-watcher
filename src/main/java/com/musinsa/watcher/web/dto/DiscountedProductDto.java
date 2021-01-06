package com.musinsa.watcher.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DiscountedProductDto implements Serializable {

  private int productId;
  private String productName;
  private String brand;
  private String img;
  private LocalDate modifiedDate;
  private int price;
  private long discount;
  private float percent;

  @Builder
  public DiscountedProductDto(int productId, String productName, String brand, int price,
      String img, Timestamp modifiedDate, long discount, float percent) {
    this.productId = productId;
    this.productName = productName;
    this.price = price;
    this.brand = brand;
    this.img = img;
    this.modifiedDate = modifiedDate.toLocalDateTime().toLocalDate();
    this.discount = discount;
    this.percent = percent;
  }

  public static DiscountedProductDto objectToDto(Object[] object) {
    return DiscountedProductDto
        .builder()
        .productId((int) object[0])
        .productName((String) object[1])
        .brand((String) object[2])
        .price(((int) object[3]))
        .img((String) object[4])
        .modifiedDate((Timestamp) object[5])
        .discount(((BigInteger) object[6]).longValue())
        .percent(((BigDecimal) object[7]).floatValue())
        .build();
  }

  public static List<DiscountedProductDto> objectsToDtoList(List<Object[]> objectList) {
    return objectList.stream()
        .map(product -> DiscountedProductDto.objectToDto(product))
        .collect(Collectors.toList());
  }
}
