package org.zacharko.transaction.commission.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zacharko.transaction.commission.controller.request.TransactionCommissionRequest;

@RestController
@Slf4j
public class TransactionCommissionCalculateController
{
   @GetMapping("/transaction/commission/")
   public void commissionCalculate(TransactionCommissionRequest transactionCommissionRequest) {
      log.info("Recieve request for commision calculation with payload {}", transactionCommissionRequest);
   }
}
