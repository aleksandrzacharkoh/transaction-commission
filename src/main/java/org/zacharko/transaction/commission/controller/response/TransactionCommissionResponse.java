package org.zacharko.transaction.commission.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionCommissionResponse
{
   private BigDecimal amount;
   private String currency;
}
