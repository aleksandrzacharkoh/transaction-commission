package org.zacharko.transaction.commission.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zacharko.transaction.commission.db.entity.TransactionDao;
import org.zacharko.transaction.commission.helper.TransactionDaoHelper;
import org.zacharko.transaction.commission.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionServiceImplTest
{
   @Autowired
   private TransactionService transactionService;

   @Test
   public void transactionService_save() {
      transactionService.saveTransaction(TransactionDaoHelper.transaction1());
      List<TransactionDao> allTransactions = transactionService.getAllTransactions();
      assertEquals(1, allTransactions.size());
   }

   @Test
   public void transactionService_getAll() {
      transactionService.saveTransaction(TransactionDaoHelper.transaction1());
      transactionService.saveTransaction(TransactionDaoHelper.transaction1());
      transactionService.saveTransaction(TransactionDaoHelper.transaction2());
      List<TransactionDao> allTransactions = transactionService.getAllTransactions();
      assertEquals(3, allTransactions.size());
   }


   @Test
   public void transactionService_getTransactionForLastMonth() {
      Integer clientId = 42;

      transactionService.saveTransaction(TransactionDaoHelper.transaction1());
      transactionService.saveTransaction(TransactionDaoHelper.transaction1());
      transactionService.saveTransaction(TransactionDaoHelper.transaction2());
      transactionService.saveTransaction(TransactionDaoHelper.transactionOld());
      List<TransactionDao> allTransactions = transactionService.getClientTransactionForCurrentMonth(LocalDate.now(), clientId);
      assertEquals(3, allTransactions.size());
   }

}