package pro.sky.telegrambot.repository.telegram;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.telegram.BotState;


public interface BotStateRepository extends JpaRepository<BotState, Long> {
}
