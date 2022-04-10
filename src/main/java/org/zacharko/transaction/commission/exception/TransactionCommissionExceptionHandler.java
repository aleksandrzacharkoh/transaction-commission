package org.zacharko.transaction.commission.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionCommissionExceptionHandler extends ResponseEntityExceptionHandler
{
   @ExceptionHandler(value = {ExchangeServiceUnreachableException.class})
   public ResponseEntity<Object> handleCantExchange(ExchangeServiceUnreachableException e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
   }

   @ExceptionHandler(value = {CurrencyNotFoundException.class})
   public ResponseEntity<Object> handleCurrencyNotFound(Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
   }
}
