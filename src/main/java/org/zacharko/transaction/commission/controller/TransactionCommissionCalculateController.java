package org.zacharko.transaction.commission.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zacharko.transaction.commission.controller.request.TransactionCommissionRequest;
import org.zacharko.transaction.commission.controller.response.TransactionCommissionResponse;
import org.zacharko.transaction.commission.dto.TransactionCommissionResultDto;
import org.zacharko.transaction.commission.mapper.TransactionCommissionMapper;
import org.zacharko.transaction.commission.service.TransactionCommissionService;

import javax.validation.Valid;

import static org.zacharko.transaction.commission.mapper.TransactionCommissionMapper.mapToResponse;

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
   public ResponseEntity<TransactionCommissionResponse> commissionCalculate(@Valid @RequestBody TransactionCommissionRequest transactionCommissionRequest) {
      log.info("Receive request for commission calculation with payload {}", transactionCommissionRequest);
      TransactionCommissionResultDto result = commissionService.calculate(TransactionCommissionMapper.mapToDto(transactionCommissionRequest));
      return ResponseEntity.ok(mapToResponse(result));
   }
}
