package by.beg.catalog.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comments")
    private long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    private String name;

    @NotBlank(message = "Введите текст комментария")
    private String text;

    public Comment(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public Comment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Дата: " + date + " Имя: " + name + " Отзыв: " + text;
    }
}
