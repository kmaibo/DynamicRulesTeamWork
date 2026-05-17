package pro.sky.telegrambot.model.secondary;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Условие динамического правила рекомендации.
 * Определяет тип запроса, аргументы условия и признак отрицания результата.
 */

@Entity
@Table(name = "query_conditions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryConditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "query_type", nullable = false)
    private String query;

    @ElementCollection
    @CollectionTable(
            name = "query_condition_arguments",
            joinColumns = @JoinColumn(name = "condition_id")
    )
    @Column(name = "argument")
    private List<String> arguments;

    @Column(name = "negate", nullable = false)
    private boolean negate;

}