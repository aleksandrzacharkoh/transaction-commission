package org.zacharko.transaction.commission.mapper;

import lombok.experimental.UtilityClass;
import org.zacharko.transaction.commission.db.entity.TransactionDao;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;

@UtilityClass
public class TransactionMapper
{
   public static TransactionDao map(TransactionCommissionDto commissionDto) {
      TransactionDao transactionDao = new TransactionDao();
      transactionDao.setDate(commissionDto.getDate());
      transactionDao.setAmount(commissionDto.getAmount());
      transactionDao.setClientId(commissionDto.getClientId());
      return transactionDao;
   }
}
