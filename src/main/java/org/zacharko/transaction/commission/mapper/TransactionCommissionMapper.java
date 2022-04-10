package org.zacharko.transaction.commission.mapper;

import org.zacharko.transaction.commission.controller.request.TransactionCommissionRequest;
import org.zacharko.transaction.commission.controller.response.TransactionCommissionResponse;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.dto.TransactionCommissionResultDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class TransactionCommissionMapper
{
   // Better way is using some mapper library like MapStruct or Orika but for small project like this is overcomplicated
   public static TransactionCommissionDto mapToDto(TransactionCommissionRequest request) {
      return TransactionCommissionDto.builder()
            .amount(new BigDecimal(request.getAmount()))
            .date(LocalDate.parse(request.getDate())) // use default ISO date format
            .currency(request.getCurrency())
            .clientId(request.getClientId())
            .build();
   }

   public static TransactionCommissionResponse mapToResponse(TransactionCommissionResultDto dto) {
      return new TransactionCommissionResponse(dto.getAmount().setScale(2, RoundingMode.HALF_UP), dto.getCurrency());
   }
}
