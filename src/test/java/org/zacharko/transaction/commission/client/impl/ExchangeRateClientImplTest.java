package org.zacharko.transaction.commission.client.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zacharko.transaction.commission.client.ExchangeRateResult;
import org.zacharko.transaction.commission.exception.ExchangeServiceUnreachableException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExchangeRateClientImplTest
{
   @Autowired
   private ExchangeRateClientImpl client;


   @Test
   public void exchangeRateClient_success() throws ExchangeServiceUnreachableException
   {
      ExchangeRateResult exchangeRates = client.getExchangeRates();
      assertNotNull(exchangeRates);
      assertEquals(true, exchangeRates.getSuccess());
      assertEquals("EUR", exchangeRates.getBase());
      assertNotNull(exchangeRates.getRates());
   }

}