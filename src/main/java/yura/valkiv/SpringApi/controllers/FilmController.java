package yura.valkiv.SpringApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yura.valkiv.SpringApi.models.Film;
import yura.valkiv.SpringApi.services.FilmServices;
import java.util.*;

@RestController
@RequestMapping("/filmix")
public class FilmController {

    @Autowired
    private FilmServices filmServices;

    @GetMapping("/all")
    public List<Film> getFilms() {
        return filmServices.getAllFilmsList();
    }

    @GetMapping("/tag")
    public List<Film> findByTags(@RequestParam("tags") String tags) {
        return filmServices.findFilmsByTags(tags);
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable int id) {
        return filmServices.getFilmById(id);
    }

    @PostMapping("/add")
    public Film addFilm(@RequestBody Film film) {
        return filmServices.addFilm(film);
    }

    @PostMapping("addRate/{ID}")
    public String addRatingToFilm(@PathVariable("ID") int ID,
                                  @RequestParam("rat") int rat) {
        return filmServices.addRatingToFilm(ID, rat);
    }

    @PutMapping("/{id}")
    public Film updateFilm(@PathVariable int id,
                           @RequestBody Film filmNewData) {
        return filmServices.updateFilm(id, filmNewData);
    }

    @DeleteMapping("/{id}")
    public String deleteFilm(@PathVariable int id) {
        return filmServices.deleteFilmById(id);
    }

}
