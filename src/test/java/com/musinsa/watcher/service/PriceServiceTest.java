package com.musinsa.watcher.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musinsa.watcher.domain.price.slave.PriceSlaveRepository;
import com.musinsa.watcher.web.dto.product.PriceResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PriceServiceTest {

  @Mock
  private PriceSlaveRepository priceSlaveRepository;

  @Test
  @DisplayName("가격 데이터를 조회한다.")
  public void 가격을_조회한다() {
    PriceService priceService = new PriceService(priceSlaveRepository);
    int product_id = 1;
    Page mockPage = mock(Page.class);
    Pageable pageable = mock(Pageable.class);
    List<PriceResponseDto> list = new ArrayList<>();
    when(priceSlaveRepository.findByProductId(eq(product_id), eq(pageable))).thenReturn(mockPage);
    when(mockPage.getTotalElements()).thenReturn(100L);
    when(mockPage.getContent()).thenReturn(list);
    //when
    priceService.findByProductId(product_id, pageable);
    //then
    verify(priceSlaveRepository, times(1)).findByProductId(product_id, pageable);
  }
}