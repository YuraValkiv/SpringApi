package yura.valkiv.SpringApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yura.valkiv.SpringApi.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

}
