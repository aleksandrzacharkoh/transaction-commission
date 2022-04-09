package org.zacharko.transaction.commission.service.rule.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.helper.TransactionDaoHelper;
import org.zacharko.transaction.commission.service.TransactionService;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_VipClient;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_notVipClient;
import static org.zacharko.transaction.commission.helper.TransactionDaoHelper.transaction1;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class HighTurnoverCommissionRuleTest
{

   @InjectMocks
   private HighTurnoverCommissionRule rule;

   @Mock
   private TransactionService transactionService;

   private BigDecimal highTurnoverLimit = new BigDecimal("1000");

   private BigDecimal highTurnoverCommission = new BigDecimal("0.03");

   @BeforeEach
   public void setup() {
      rule = new HighTurnoverCommissionRule(highTurnoverLimit, highTurnoverCommission, transactionService);
   }

   @Test
   public void highTurnoverCommissionRule_getCommission_reachLimit() {
      TransactionCommissionDto commissionDto = transactionCommission_EUR_VipClient();

      BDDMockito.when(transactionService.getClientTransactionForLastMonth(commissionDto.getClientId()))
            .thenReturn(Arrays.asList(transaction1(), TransactionDaoHelper.transaction2()));
      BigDecimal commission = rule.getCommission(commissionDto);

      assertEquals(0, highTurnoverCommission.compareTo(commission));
   }

   @Test
   public void highTurnoverCommissionRule_getCommission_dontReachLimit() {
      BigDecimal commission = rule.getCommission(transactionCommission_EUR_notVipClient());

      assertNotEquals(0, highTurnoverCommission.compareTo(commission));
   }

}