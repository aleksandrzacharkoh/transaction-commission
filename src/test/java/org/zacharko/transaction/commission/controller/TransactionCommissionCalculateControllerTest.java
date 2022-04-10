package org.zacharko.transaction.commission.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.zacharko.transaction.commission.controller.request.TransactionCommissionRequest;
import org.zacharko.transaction.commission.controller.response.TransactionCommissionResponse;
import org.zacharko.transaction.commission.csv.CSVReader;
import org.zacharko.transaction.commission.csv.TransactionCommissionTestData;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.zacharko.transaction.commission.helper.TransactionCommissionHelper.mapToRequest;

@SpringBootTest
class TransactionCommissionCalculateControllerTest
{
   @Autowired
   private TransactionCommissionCalculateController controller;

   @Test
   public void controller_success() {
      ResponseEntity<TransactionCommissionResponse> response = controller.commissionCalculate(new TransactionCommissionRequest("2021-01-01", "100", "EUR", 42));
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(0, new BigDecimal("0.05").compareTo(response.getBody().getAmount()));
      assertEquals("EUR", response.getBody().getCurrency());
   }

   @Test
   public void checkTransactionData() {
      CSVReader csvReader = new CSVReader();
      List<TransactionCommissionTestData> transactionCommissionTestData = csvReader.loadData("transactionCommissionTestData.csv");
      transactionCommissionTestData.forEach(
            data -> {
               ResponseEntity<TransactionCommissionResponse> response = controller.commissionCalculate(mapToRequest(data));
               assertEquals(0, new BigDecimal(data.getCommissionAmount()).compareTo(response.getBody().getAmount()));
               assertEquals(data.getCurrency(), response.getBody().getCurrency());
            }
      );
   }

}