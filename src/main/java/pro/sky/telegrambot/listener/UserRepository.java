package pro.sky.telegrambot.listener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.sky.telegrambot.model.telegram.UserEntity;
import pro.sky.telegrambot.model.telegram.UsersTgBot;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UsersTgBot, Long> {
    UsersTgBot findByChatId(long chatId);

    List<UserEntity> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String query, String query1);
}
