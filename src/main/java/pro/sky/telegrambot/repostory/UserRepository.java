package pro.sky.telegrambot.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
}
