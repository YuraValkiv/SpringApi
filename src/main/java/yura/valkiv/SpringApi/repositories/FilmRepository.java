package yura.valkiv.SpringApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yura.valkiv.SpringApi.models.Film;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    Film findDistinctById(int id);
    List<Film> findDistinctByTagsNameIn(List<String> tagNames);
    Film findById(int id);

}