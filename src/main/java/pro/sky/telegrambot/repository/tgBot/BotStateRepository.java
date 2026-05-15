package pro.sky.telegrambot.repository.tgBot;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.tgBot.BotState;


public interface BotStateRepository extends JpaRepository<BotState, Long> {
}
