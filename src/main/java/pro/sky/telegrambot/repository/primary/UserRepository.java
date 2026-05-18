package pro.sky.telegrambot.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.primary.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
}
