package pro.sky.telegrambot.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.primary.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByPhone(String phone);
}
