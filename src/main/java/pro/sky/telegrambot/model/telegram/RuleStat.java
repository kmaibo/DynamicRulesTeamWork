package pro.sky.telegrambot.model.telegram;

import pro.sky.telegrambot.model.secondary.RuleEntity;

import javax.persistence.*;

/**
 * Статистика использования динамического правила рекомендаций.
 * Хранит количество срабатываний правила.
 */

@Entity
@Table(name = "rule_stats")
public class RuleStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rule_id", nullable = false, unique = true)
    private RuleEntity rule;

    @Column(nullable = false)
    private long count = 0;

    public RuleStat() {
    }

    public RuleStat(RuleEntity rule) {
        this.rule = rule;
        this.count = 0;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public RuleEntity getRule() {
        return rule;
    }

    public void setRule(RuleEntity rule) {
        this.rule = rule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
