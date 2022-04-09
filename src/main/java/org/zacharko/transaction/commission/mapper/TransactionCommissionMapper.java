package org.zacharko.transaction.commission.mapper;

import lombok.experimental.UtilityClass;
import org.zacharko.transaction.commission.controller.request.TransactionCommissionRequest;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class TransactionCommissionMapper
{
   // Better way is using some mapper library like MapStruct or Orika but for small project like this is overcomplication
   public TransactionCommissionDto map(TransactionCommissionRequest request) {
      return TransactionCommissionDto.builder()
            .amount(new BigDecimal(request.getAmount()))
            .date(LocalDate.parse(request.getDate())) // use default ISO date format
            .currency(request.getCurrency())
            .clientId(request.getClientId())
            .build();
   }
}
