package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;

/**
 * @author Timofey
 * @since 20.07.2021
 */
public interface TacoRepository extends CrudRepository <Taco,Long> {


// JDBC CODE
//    Taco save(Taco desing);
}
