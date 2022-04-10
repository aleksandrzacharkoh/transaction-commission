package org.zacharko.transaction.commission.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class TransactionCommissionRequest
{

   @NotNull
   private String date;
   @NotNull
   private String amount;
   @NotNull
   private String currency;

   @NotNull
   @JsonProperty("client_id")
   private Integer clientId;
}
