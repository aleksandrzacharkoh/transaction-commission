package org.zacharko.transaction.commission.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zacharko.transaction.commission.db.entity.TransactionDao;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDao, Long>
{

   List<TransactionDao> getTransactionDaoByClientIdAndDateAfter(Integer clientId, LocalDate date);
}
