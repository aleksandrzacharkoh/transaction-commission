package org.zacharko.transaction.commission.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionCommissionRequest
{
   private String date;
   private String amount;
   private String currency;

   @JsonProperty("client_id")
   private Integer clientId;
}
