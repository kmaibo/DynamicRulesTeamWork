package pro.sky.telegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class QueryConditionDto {
    private String query;
    private List<String> arguments;
    private Boolean negate;
}


