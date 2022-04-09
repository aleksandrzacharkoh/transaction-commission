package org.zacharko.transaction.commission.service.rule.exchange;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.zacharko.transaction.commission.client.ExchangeRateClient;
import org.zacharko.transaction.commission.client.impl.ExchangeRateClientImpl;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.exception.CurrencyNotFoundException;
import org.zacharko.transaction.commission.exception.ExchangeServiceUnreachableException;
import org.zacharko.transaction.commission.service.rule.exchange.impl.ExchangeServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.zacharko.transaction.commission.helper.ExchangeRateHelper.exchangeRates;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_RUR;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_USD;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeServiceTest
{

   @Rule
   public MockitoRule initRule = MockitoJUnit.rule();

   private ExchangeRateClient exchangeRateClient;

   private ExchangeService exchangeService;

   @BeforeEach
   public void setup() {
      exchangeRateClient = mock(ExchangeRateClientImpl.class);
      exchangeService = new ExchangeServiceImpl(exchangeRateClient);
   }

   // TODO rewrite test
   @Test
   public void exchangeService_currencyNotFound() throws ExchangeServiceUnreachableException
   {
      // given
      BDDMockito.given(exchangeRateClient.getExchangeRates())
            .willReturn(exchangeRates());
      TransactionCommissionDto commissionDto = transactionCommission_RUR();

      // when
      Exception exception = assertThrows(
            CurrencyNotFoundException.class,
            () -> exchangeService.getExchangedResult(commissionDto));

      // then
      assertTrue(exception.getMessage().contains("Currency not found"));
   }

   @Test
   public void exchangeService_currencyFound() throws CurrencyNotFoundException, ExchangeServiceUnreachableException
   {
      // given
      BDDMockito.given(exchangeRateClient.getExchangeRates())
            .willReturn(exchangeRates());
      TransactionCommissionDto commissionDto = transactionCommission_USD();

      // when
      BigDecimal exchangedResult = exchangeService.getExchangedResult(commissionDto);

      // then
      assertEquals(0, new BigDecimal("121.7582").compareTo(exchangedResult));
   }
}