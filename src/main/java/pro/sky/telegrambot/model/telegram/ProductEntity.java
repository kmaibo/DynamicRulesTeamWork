package pro.sky.telegrambot.model.telegram;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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