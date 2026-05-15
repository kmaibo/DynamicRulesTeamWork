package pro.sky.telegrambot.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.primary.Card;


public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByNumber(String number);


}
