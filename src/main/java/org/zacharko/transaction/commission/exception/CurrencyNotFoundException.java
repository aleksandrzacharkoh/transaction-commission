package org.zacharko.transaction.commission.exception;

public class CurrencyNotFoundException extends Exception
{
   public CurrencyNotFoundException(String currency)
   {
      super("Currency not found " + currency);
   }
}
