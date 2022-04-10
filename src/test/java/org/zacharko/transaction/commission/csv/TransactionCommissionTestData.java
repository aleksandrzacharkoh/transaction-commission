package org.zacharko.transaction.commission.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class TransactionCommissionTestData
{
   @CsvBindByName(column = "client_id")
   private Integer clientId;

   @CsvBindByName
   private String date;

   @CsvBindByName
   private String amount;

   @CsvBindByName
   private String currency;

   @CsvBindByName(column = "commission_amount")
   private String commissionAmount;

   @CsvBindByName(column = "commission_currency")
   private String commissionCurrency;
}
