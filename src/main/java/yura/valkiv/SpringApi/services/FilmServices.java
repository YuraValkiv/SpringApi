package yura.valkiv.SpringApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import yura.valkiv.SpringApi.models.Film;
import yura.valkiv.SpringApi.models.FilmRating;
import yura.valkiv.SpringApi.repositories.FilmRepository;
import yura.valkiv.SpringApi.repositories.RatingRepository;
import java.util.Arrays;
import java.util.List;

@Service
public class FilmServices {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private RatingRepository ratingRepository;

    //CREATE

    @Cacheable("film")
    public Film addFilm(Film film) {
        film.setRating(0); // сетаєм 0 рейтинг бо фільм тільки добавили і сейвим його в базу
        return filmRepository.save(film);
    }

    @Cacheable("rate")
    public String addRatingToFilm(int ID, int rat) {
        //добавили до списку рейтинга фільма нову оцінку;
        Film film = filmRepository.findById(ID);
        ratingRepository.save(new FilmRating(film, rat));
        //Обновляєм середнє арифметичне фільма
        int temp = 0, i = 0;
        for (FilmRating filmRating : ratingRepository.findAllByFilm_Id(film.getId())) {
            temp += filmRating.getRating();
            i++;
        }
        film.setRating(temp/i);
        filmRepository.save(film);
        return "Оцінка поставлена";
    }

    //READ

    @Cacheable("films")
    public List<Film> getAllFilmsList() {
        return filmRepository.findAll();//Передаєм список всіх фільмів із бази даних
    }

    @Cacheable("film")
    public Film getFilmById( int id) {
        return filmRepository.findById(id);//Пошук фільма по айді
    }

    @Cacheable("films")
    public List<Film> findFilmsByTags(String tags) {
        List<String> tagList = Arrays.asList(tags.split(","));
        return filmRepository.findDistinctByTagsNameIn(tagList); //пошук по тегам
    }

    //UPDATE

    @Cacheable("film")
    public Film updateFilm(int id,Film filmNewData) {
        Film film = filmRepository.findById(id);
        film.setName(filmNewData.getName());
        film.setReleaseDate(filmNewData.getReleaseDate());
        film.setDescription(filmNewData.getDescription());
        film.setVideoFile(filmNewData.getVideoFile());
        film.setTags(filmNewData.getTags()); // сет всіх полів
        return filmRepository.save(film);
    }

    //DELETE

    public String deleteFilmById(int id) {
        filmRepository.deleteById(id);
        return "Видалення успішне"; // видалення
    }


}