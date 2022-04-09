package org.zacharko.transaction.commission.exception;

public class ExchangeServiceUnreachableException extends Exception
{
   public ExchangeServiceUnreachableException()
   {
      super("Exchange rate service is unreachable");
   }
}
