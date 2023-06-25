package yura.valkiv.SpringApi.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//@Entity
//@Table(name = "")
//public class User {
//
//    @Id
//    @GeneratedValue( strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "name")
//    private String name;
//    @Column(name = "email")
//    private String email;
//    @Column(name = "date")
//    private Date date;
//
//    public User() {
//    }
//
//    public User(int id, String name, String email, Date date) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.date = date;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User user)) return false;
//        return getId() == user.getId() && getName().equals(user.getName()) && getEmail().equals(user.getEmail()) && getDate().equals(user.getDate());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName(), getEmail(), getDate());
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", date=" + date +
//                '}';
//    }
//}
//
//
//
//
//
//
