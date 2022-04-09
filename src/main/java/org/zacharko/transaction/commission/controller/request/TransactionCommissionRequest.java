package org.zacharko.transaction.commission.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TransactionCommissionRequest
{

   @NotEmpty
   private String date;
   @NotEmpty
   private String amount;
   @NotEmpty
   private String currency;

   @NotEmpty
   @JsonProperty("client_id")
   private Integer clientId;
}
