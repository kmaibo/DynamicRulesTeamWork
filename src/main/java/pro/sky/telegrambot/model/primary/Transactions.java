package pro.sky.telegrambot.model.primary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.sky.telegrambot.enums.TypeTransactions;


import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeTransactions type;
    private BigDecimal amount;
    private Long fromAccountId;
    private Long toAccountId;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
}
