package yura.valkiv.SpringApi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yura.valkiv.SpringApi.models.Film;
import yura.valkiv.SpringApi.models.FilmRating;
import yura.valkiv.SpringApi.models.Tag;
import yura.valkiv.SpringApi.repositories.FilmRepository;
import yura.valkiv.SpringApi.repositories.RatingRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FilmServicesTest {

    List<Film> films = getEmuDataBase();
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private RatingRepository ratingRepository;
    @InjectMocks
    private FilmServices filmServices;

    //test 1/7
    @Test
    public void getAllFilmsListTest() {
        Mockito.when(filmRepository.findAll()).thenReturn(films);
        List<Film> result = filmServices.getAllFilmsList();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(films, result);
        Assertions.assertEquals(2, result.size());
    }
    @Test
    public void getFilmById() {
        int id = 2;
        Mockito.when(filmRepository.findById(id)).thenReturn(films.get(id-1));
        Film filmRes = filmServices.getFilmById(id);
        Assertions.assertNotNull(filmRes);
        Assertions.assertEquals(filmRes.getId(), id);
    }
    @Test
    public void testFindFilmsByTags() {
        List<Tag> tagsTest = new ArrayList<>();
        List<String> tagsNameList = new ArrayList<>();
        tagsNameList.add("horror");
        tagsTest.add(new Tag(2, "horror"));
        Mockito.when(filmRepository.findDistinctByTagsNameIn(tagsNameList)).thenReturn(films.stream().filter(film -> tagsNameList.equals(film.getTags())).toList());
        List<Film> tagsFoundFilms = filmServices.findFilmsByTags("horror");

    }
    @Test
    public void math() {

    }



    @Test
    public void deleteFilmByIdTest() {
        Mockito.doNothing().when(filmRepository).deleteById(1);
        String result = filmServices.deleteFilmById(1);
        Assertions.assertEquals("Видалення успішне", result);
    }











    //МЕТОД ДЛЯ ЕМУЛЯЦІЇ БАЗИ ДАНИХ
    private List<Film> getEmuDataBase() {
        // Створюємо замокований список
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
    //Емулятор айді
    private Film getById(int id) {
        for (Film film : films) {
            if (film.getId() == id)
                return film;
        }
        return null;
    }

}
