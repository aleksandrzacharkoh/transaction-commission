package org.zacharko.transaction.commission.helper;

import lombok.experimental.UtilityClass;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class TransactionCommissionDtoHelper
{
   public static TransactionCommissionDto transactionCommission_USD() {
      return TransactionCommissionDto.builder()
            .date(LocalDate.now())
            .amount(new BigDecimal(100))
            .currency("USD")
            .clientId(42)
            .build();
   }

   public static TransactionCommissionDto transactionCommission_RUR() {
      return TransactionCommissionDto.builder()
            .date(LocalDate.now())
            .amount(new BigDecimal(100))
            .currency("RUR")
            .clientId(42)
            .build();
   }

   public static TransactionCommissionDto transactionCommission_EUR() {
      return TransactionCommissionDto.builder()
            .date(LocalDate.now())
            .amount(new BigDecimal(100))
            .currency("EUR")
            .clientId(42)
            .build();
   }

   public static TransactionCommissionDto transactionCommission_EUR_small_amount() {
      return TransactionCommissionDto.builder()
            .date(LocalDate.now())
            .amount(new BigDecimal(1))
            .currency("EUR")
            .clientId(42)
            .build();
   }
}
