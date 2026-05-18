package pro.sky.telegrambot.model.telegram;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    private Long id;
    private String name;

    public String getName() {
        return name;
    }
}