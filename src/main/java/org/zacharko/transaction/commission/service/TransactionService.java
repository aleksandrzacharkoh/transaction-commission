package org.zacharko.transaction.commission.service;

import org.zacharko.transaction.commission.db.entity.TransactionDao;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService
{

   TransactionDao saveTransaction(TransactionDao transactionDao);

   List<TransactionDao> getAllTransactions();

   List<TransactionDao> getClientTransactionForCurrentMonth(LocalDate date, Integer clientId);

}
