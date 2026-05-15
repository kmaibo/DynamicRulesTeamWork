package pro.sky.telegrambot.repository.tgBot;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.tgBot.UserEntity;


import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);
}