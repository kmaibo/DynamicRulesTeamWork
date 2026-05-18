package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RecommendationService {

    private final Logger log = LoggerFactory.getLogger(RecommendationService.class);

    private final List<String> recommendations = List.of(
            "«Только для своих ❤ Лучшие предложения в нашем телеграм-канале...»",
            "Не пропустите нашу новую коллекцию!",
            "Закрытый доступ: особые условия для вас",
            "Свежие тренды, которые точно подойдут для любого повода!!!",
            "Персональная подборка лучших товаров!"
    );

    public String getPersonalizedRecommendation(String username) {

        log.info("get recommendations for username: " + username);
        log.error("get recommendations for username: " + username + " not found");
        String baseMessage = String.format("Привет, %s! ", username);
        String randomRecommendation = recommendations.get(
                new Random().nextInt(recommendations.size())
        );
        return baseMessage + randomRecommendation;
    }
}