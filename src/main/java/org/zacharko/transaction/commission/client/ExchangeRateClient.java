package org.zacharko.transaction.commission.client;

import org.zacharko.transaction.commission.exception.ExchangeServiceUnreachableException;

public interface ExchangeRateClient
{
   ExchangeRateResult getExchangeRates() throws ExchangeServiceUnreachableException;
}
