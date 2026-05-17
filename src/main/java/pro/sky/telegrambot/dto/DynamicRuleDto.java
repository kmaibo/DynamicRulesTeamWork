package pro.sky.telegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicRuleDto {
    private UUID id;
    private String productName;
    private UUID productId;
    private String productText;
    private List<QueryConditionDto> rule;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QueryConditionDto {

        private String query;
        private List<String> arguments;
        private boolean negate;

    }
}