package pro.sky.telegrambot.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByPhone(String phone);
}
