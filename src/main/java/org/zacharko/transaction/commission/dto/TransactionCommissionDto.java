package org.zacharko.transaction.commission.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class TransactionCommissionDto
{
   private LocalDate date; // TODO what about timezones?
   private BigDecimal amount;
   private String currency;
   private Integer clientId;
}
