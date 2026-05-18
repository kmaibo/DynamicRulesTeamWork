package pro.sky.telegrambot.model.telegram;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_tgbot")
public class UsersTgBot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;
    private String username;
    private LocalDateTime registrationDate;

    public UsersTgBot(String username) {
        this.username = username;
    }

    public UsersTgBot() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
