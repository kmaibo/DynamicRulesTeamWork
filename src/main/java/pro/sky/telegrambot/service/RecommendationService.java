package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Сервис генерации персональных рекомендаций для пользователей.
 * Возвращает случайное сообщение для пользователя
 */

@Service
public class RecommendationService {

    private final Random random = new Random();

    private final List<String> recommendations = List.of(
            "«Только для своих ❤ Лучшие предложения в нашем телеграм-канале...»",
            "Не пропустите нашу новую коллекцию!",
            "Закрытый доступ: особые условия для вас",
            "Свежие тренды, которые точно подойдут для любого повода!!!",
            "Персональная подборка лучших товаров!"
    );

    public String getPersonalizedRecommendation(String username) {
        String baseMessage = String.format("Привет, %s! ", username);
        String randomRecommendation = recommendations.get(
                random.nextInt(recommendations.size())
        );
        return baseMessage + randomRecommendation;
    }
}