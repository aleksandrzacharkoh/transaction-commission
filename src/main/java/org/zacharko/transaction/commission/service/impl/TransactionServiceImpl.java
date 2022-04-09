package org.zacharko.transaction.commission.service.impl;

import org.springframework.stereotype.Service;
import org.zacharko.transaction.commission.db.entity.TransactionDao;
import org.zacharko.transaction.commission.db.repository.TransactionRepository;
import org.zacharko.transaction.commission.service.TransactionService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService
{
   private final TransactionRepository repository;

   public TransactionServiceImpl(TransactionRepository repository)
   {
      this.repository = repository;
   }

   @Override
   public TransactionDao saveTransaction(TransactionDao transactionDao)
   {
      return repository.save(transactionDao);
   }

   @Override
   public List<TransactionDao> getAllTransactions()
   {
      return repository.findAll();
   }

   @Override
   public List<TransactionDao> getClientTransactionForLastMonth(Integer clientId)
   {
      LocalDate lastMonthDate = LocalDate.now().minus(1, ChronoUnit.MONTHS);
      return repository.getTransactionDaoByClientIdAndDateAfter(clientId, lastMonthDate);
   }

}
