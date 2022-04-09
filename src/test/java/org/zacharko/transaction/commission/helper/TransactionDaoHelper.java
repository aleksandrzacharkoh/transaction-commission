package org.zacharko.transaction.commission.helper;

import lombok.experimental.UtilityClass;
import org.zacharko.transaction.commission.db.entity.TransactionDao;

import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class TransactionDaoHelper
{
   public TransactionDao transaction1() {
      TransactionDao transaction = new TransactionDao();
      transaction.setClientId(42);
      transaction.setAmount(new BigDecimal(100));
      transaction.setDate(LocalDate.now());
      return transaction;
   }

   public TransactionDao transaction2() {
      TransactionDao transaction = new TransactionDao();
      transaction.setClientId(42);
      transaction.setAmount(new BigDecimal(1000));
      transaction.setDate(LocalDate.now());
      return transaction;
   }

   public TransactionDao transactionOld() {
      TransactionDao transaction = new TransactionDao();
      transaction.setClientId(42);
      transaction.setAmount(new BigDecimal(1000));
      transaction.setDate(LocalDate.now().minusMonths(2));
      return transaction;
   }

   public TransactionDao transaction3() {
      TransactionDao transaction = new TransactionDao();
      transaction.setClientId(43);
      transaction.setAmount(new BigDecimal(100));
      transaction.setDate(LocalDate.now());
      return transaction;
   }
}
