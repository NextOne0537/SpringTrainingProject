package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Order;

import java.util.Date;
import java.util.List;

/**
 * @author Timofey
 * @since 20.07.2021
 */
public interface OrderRepository extends CrudRepository<Order,String> {


    //    public List<Order> findByZip(String deliveryZip);
//
//    public List<Order> readOrdersByZipAndCreatedAtBetween(String deliveryZip, Date start, Date end);
//
//    //    JDBC code
//    //    Order save(Order order);
}
