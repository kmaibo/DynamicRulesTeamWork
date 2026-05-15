package pro.sky.telegrambot.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.primary.Transactions;


import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findAllByAccountId(long accountId);

}
