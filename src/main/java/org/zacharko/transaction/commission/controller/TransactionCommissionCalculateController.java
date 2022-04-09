package org.zacharko.transaction.commission.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zacharko.transaction.commission.controller.request.TransactionCommissionRequest;
import org.zacharko.transaction.commission.service.TransactionCommissionService;

import javax.validation.Valid;

import static org.zacharko.transaction.commission.mapper.TransactionCommissionMapper.map;

@RestController
@Slf4j
public class TransactionCommissionCalculateController
{
   private final TransactionCommissionService commissionService;

   public TransactionCommissionCalculateController(TransactionCommissionService commissionService)
   {
      this.commissionService = commissionService;
   }

   @PostMapping(value = "/transaction/commission/")
   public void commissionCalculate(@Valid @RequestBody TransactionCommissionRequest transactionCommissionRequest) {
      log.info("Recieve request for commission calculation with payload {}", transactionCommissionRequest);
      commissionService.calculate(map(transactionCommissionRequest));
   }
}
