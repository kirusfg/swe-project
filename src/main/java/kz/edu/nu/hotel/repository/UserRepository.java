package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByEmail(@Param("email") String email);
    public User findById(@Param("id") long id);
}
