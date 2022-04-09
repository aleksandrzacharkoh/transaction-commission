package org.zacharko.transaction.commission.helper;

import org.zacharko.transaction.commission.db.entity.TransactionDao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDaoHelper
{
   public static TransactionDao transaction1() {
      TransactionDao transaction = new TransactionDao();
      transaction.setClientId(42);
      transaction.setAmount(new BigDecimal(100));
      transaction.setDate(LocalDate.now());
      return transaction;
   }

   public static TransactionDao transaction2() {
      TransactionDao transaction = new TransactionDao();
      transaction.setClientId(42);
      transaction.setAmount(new BigDecimal(1000));
      transaction.setDate(LocalDate.now());
      return transaction;
   }

   public static TransactionDao transactionOld() {
      TransactionDao transaction = new TransactionDao();
      transaction.setClientId(42);
      transaction.setAmount(new BigDecimal(1000));
      transaction.setDate(LocalDate.now().minusMonths(2));
      return transaction;
   }

   public static TransactionDao transaction_bigAmount() {
      TransactionDao transaction = new TransactionDao();
      transaction.setClientId(42);
      transaction.setAmount(new BigDecimal(10000));
      transaction.setDate(LocalDate.now());
      return transaction;
   }
}
