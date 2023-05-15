package yura.valkiv.SpringApi.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "film_rating")
public class FilmRating {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @Column(name = "rating")
    private int rating;

    public FilmRating() {
    }

    public FilmRating(Film film, int rating) {
        this.film = film;
        this.rating = rating;
    }

    public FilmRating(int id, Film film, int rating) {
        this.id = id;
        this.film = film;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmRating that)) return false;
        return getId() == that.getId() && getRating() == that.getRating() && Objects.equals(getFilm(), that.getFilm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFilm(), getRating());
    }

    @Override
    public String toString() {
        return "FilmRating{" +
                "id=" + id +
                ", film=" + film +
                ", rating=" + rating +
                '}';
    }
}