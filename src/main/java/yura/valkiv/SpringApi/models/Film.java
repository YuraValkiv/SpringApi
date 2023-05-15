package yura.valkiv.SpringApi.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "release_date")
    private int releaseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "video_file")
    private String videoFile;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "films_tags",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private List<Tag> tags;

    @Column(name="rating")
    private int rating;

    @OneToMany(mappedBy = "film", cascade = {CascadeType.ALL})
    private List<FilmRating> filmRatingList;

    public Film() {
    }

    public Film(String name, int releaseDate, String description, String videoFile, List<Tag> tags) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.description = description;
        this.videoFile = videoFile;
        this.tags = tags;
    }

    public Film(int id, String name, int releaseDate, String description, String videoFile, List<Tag> tags, int rating, List<FilmRating> filmRatingList) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.description = description;
        this.videoFile = videoFile;
        this.tags = tags;
        this.rating = rating;
        this.filmRatingList = filmRatingList;
    }

    public Film(String name) {
        this.name = name;
    }

    public List<FilmRating> getFilmRatingList() {
        return filmRatingList;
    }

    public void setFilmRatingList(List<FilmRating> filmRatingList) {
        this.filmRatingList = filmRatingList;
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
    public int getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getVideoFile() {
        return videoFile;
    }
    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
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
        if (!(o instanceof Film film)) return false;
        return getId() == film.getId() && getReleaseDate() == film.getReleaseDate() && getRating() == film.getRating() && getName().equals(film.getName()) && getDescription().equals(film.getDescription()) && getVideoFile().equals(film.getVideoFile()) && getTags().equals(film.getTags()) && getFilmRatingList().equals(film.getFilmRatingList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getReleaseDate(), getDescription(), getVideoFile(), getTags(), getRating(), getFilmRatingList());
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", description='" + description + '\'' +
                ", videoFile='" + videoFile + '\'' +
                ", tags=" + tags +
                ", rating=" + rating +
                ", filmRatingList=" + filmRatingList +
                '}';
    }

}