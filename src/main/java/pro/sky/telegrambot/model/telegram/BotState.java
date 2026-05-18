package pro.sky.telegrambot.model.telegram;

import javax.persistence.*;

@Entity
@Table(name = "bot_users_state")
public class BotState {
    @Id
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "first_time")
    private boolean firstTime = true;

    public BotState() {}
    public BotState(Long chatId) { this.chatId = chatId; }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public boolean isFirstTime() { return firstTime; }
    public void setFirstTime(boolean firstTime) { this.firstTime = firstTime; }
}