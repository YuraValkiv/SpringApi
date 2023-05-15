package yura.valkiv.SpringApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yura.valkiv.SpringApi.models.FilmRating;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<FilmRating, Integer> {

     List<FilmRating> findAllByFilm_Id(int filmId);

}

