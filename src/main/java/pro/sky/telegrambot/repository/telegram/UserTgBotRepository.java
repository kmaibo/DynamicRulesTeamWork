package pro.sky.telegrambot.repository.telegram;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.telegram.UserEntity;


import java.util.List;

public interface UserTgBotRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);
}