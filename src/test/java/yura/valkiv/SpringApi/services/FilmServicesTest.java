package yura.valkiv.SpringApi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.annotation.Cacheable;
import yura.valkiv.SpringApi.models.Film;
import yura.valkiv.SpringApi.models.FilmRating;
import yura.valkiv.SpringApi.models.Tag;
import yura.valkiv.SpringApi.repositories.FilmRepository;
import yura.valkiv.SpringApi.repositories.RatingRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FilmServicesTest {

    List<Film> films = getEmuDataBase(); // ініціалізує список з 2 фільмами
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private RatingRepository ratingRepository;
    @InjectMocks
    private FilmServices filmServices;

    @Test
    public void getAllFilmsListTest() {

        Mockito.when(filmRepository.findAll()).thenReturn(films);
        List<Film> result = filmServices.getAllFilmsList();
        Assertions.assertNotNull(result);
        assertEquals(films, result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindFilmsByTags() {

        List<Film> expectedFilms = Arrays.asList(new Film(), new Film(), new Film());
        String tags = "tag1,tag2,tag3";
        Mockito.when(filmRepository.findDistinctByTagsNameIn(Mockito.anyList())).thenReturn(expectedFilms);
        List<Film> actualFilms = filmServices.findFilmsByTags(tags);
        assertEquals(expectedFilms, actualFilms);
        Mockito.verify(filmRepository).findDistinctByTagsNameIn(Arrays.asList("tag1", "tag2", "tag3"));
    }

    @Test
    public void getFilmById() {

        int id = 2;
        Mockito.when(filmRepository.findById(id)).thenReturn(films.get(id-1));
        Film filmRes = filmServices.getFilmById(id);
        Assertions.assertNotNull(filmRes);
        assertEquals(filmRes.getId(), id);
    }

    @Test
    public void deleteFilmByIdTest() {

        Mockito.doNothing().when(filmRepository).deleteById(1);
        String result = filmServices.deleteFilmById(1);
        assertEquals("Видалення успішне", result);
    }

    @Test
    public void updateFilmTest() {

        List<Film> emuDataBase = getEmuDataBase();
        Mockito.when(filmRepository.findById(1)).thenReturn(emuDataBase.get(0));
        Mockito.when(filmRepository.save(Mockito.any(Film.class))).thenReturn(emuDataBase.get(0));
        Film filmNewData = new Film();
        filmNewData.setName("updatedName");
        filmNewData.setReleaseDate(2023);
        filmNewData.setDescription("updatedDescription");
        filmNewData.setVideoFile("updatedVideo.mp3");
        filmNewData.setTags(List.of(new Tag(1, "updatedTag")));
        Film updatedFilm = filmServices.updateFilm(1, filmNewData);
        assertEquals("updatedName", updatedFilm.getName());
    }

    @Test
    public void addRatingToFilmTest() {

        FilmRating filmRating = new FilmRating();
        filmRating.setId(1);
        filmRating.setFilm(films.get(0));
        filmRating.setRating(5);

        Mockito.when(filmRepository.findById(1)).thenReturn(films.get(0));
        Mockito.when(ratingRepository.findAllByFilm_Id(1)).thenReturn(List.of(filmRating));

        String result = filmServices.addRatingToFilm(1, 4);

        Mockito.verify(filmRepository).findById(1);
        Mockito.verify(ratingRepository).save(new FilmRating(films.get(0), 4));
        Mockito.verify(filmRepository).save(films.get(0));

        assertEquals("Оцінка поставлена", result);

    }

    @Test
    public void addFilmTest() {
        Film film = new Film();
        film.setId(1);
        film.setName("test1");
        film.setFilmRatingList(List.of(new FilmRating(1, film, 5)));
        film.setTags(List.of(new Tag(1, "horror")));
        film.setDescription("description test");
        film.setRating(5);
        film.setVideoFile("video.mp3");
        film.setReleaseDate(2014);
        Film savedFilm = filmServices.addFilm(film);

        Mockito.when(filmRepository.save(film)).thenReturn(film);
        Assertions.assertNotNull(filmRepository.save(film));
        Assertions.assertEquals(film, filmRepository.save(film));
    }

    //Приватні методи тільки для тестування
    // Метод що емулює базу даних
    private List<Film> getEmuDataBase() {
        List<Film> films = new ArrayList<>();
        Film film1 = new Film();
        film1.setId(1);
        film1.setName("test1");
        film1.setFilmRatingList(List.of(new FilmRating(1, film1, 5)));
        film1.setTags(List.of(new Tag(1, "horror")));
        film1.setDescription("description test");
        film1.setRating(5);
        film1.setVideoFile("video.mp3");
        film1.setReleaseDate(2014);
        films.add(film1);

        Film film2 = new Film();
        film2.setId(2);
        film2.setName("test2");
        film2.setFilmRatingList(List.of(new FilmRating(2, film2, 4)));
        film2.setTags(List.of(new Tag(2, "criminal")));
        film2.setDescription("description test");
        film2.setRating(4);
        film2.setVideoFile("video.mp3");
        film2.setReleaseDate(2014);
        films.add(film2);
        return films;
    }
}
