package pro.sky.telegrambot.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Card;


public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByNumber(String number);


}
