package by.beg.catalog.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Заполните имя")
    @Size(min = 2 , max = 12, message = "Имя должно быть от 2 до 12 символов")
    private String name;

    @NotBlank(message = "Заполните номер")
    private String phoneNumber;

    @NotBlank(message = "Заполните адрес")
    private String address;

    @Email(message = "Некорректная почта")
    private String email;

    @NotBlank(message = "Заполните пароль")
    @Size(min = 4 , max = 16 , message = "Пароль должен содержать от 4 до 16 символов")
    private String password;

    @Column(name = "admin")
    private boolean isAdmin;

    @Column(name = "dispatcher")
    private boolean isDispatcher;


    public User(String name, String phoneNumber, String address, String email, String password, boolean isAdmin, boolean isDispatcher) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isDispatcher = isDispatcher;
    }

    public User(String name, String phoneNumber, String address, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isDispatcher() {
        return isDispatcher;
    }

    public void setDispatcher(boolean dispatcher) {
        isDispatcher = dispatcher;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isDispatcher=" + isDispatcher +
                '}';
    }
}
