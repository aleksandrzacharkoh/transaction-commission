package org.zacharko.transaction.commission.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionCommissionResultDto
{
   private BigDecimal amount;
   private String currency;
}
