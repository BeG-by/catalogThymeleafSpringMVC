package by.beg.catalog.entity;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


public class Product implements Serializable {

    private static int count = 0;
    private int id = ++count;

    @NotBlank(message = "Заполните имя")
    @Size(min = 2 , max = 24 , message = "Имя должно содержать от 2 до 24 символов")
    private String name;

    @NotNull(message = "Тип не должен быть пустым")
    private ProductTypeEnum type;

    @NotNull
    private String description;

    @Positive(message = "Цена должна быть больше 0")
    private int price;


    public Product(String name, ProductTypeEnum type, String description, int price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }


    public Product() {
    }

    public static int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
