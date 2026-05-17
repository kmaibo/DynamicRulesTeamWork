package pro.sky.telegrambot.model.secondary;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Сущность динамического правила рекомендации продукта.
 * Содержит информацию о продукте и набор условий,
 * определяющих возможность показа рекомендации пользователю.</p>
 */

@Entity
@Table(name = "dynamic_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_id", nullable = false, unique = true)
    private UUID productId;

    @Column(name = "product_text", length = 2000)
    private String productText;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id")
    private List<QueryConditionEntity> rule;
}