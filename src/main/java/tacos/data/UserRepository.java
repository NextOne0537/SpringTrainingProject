package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.User;

/**
 * @author Timofey
 * @since 02.08.2021
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername (String username);

}
