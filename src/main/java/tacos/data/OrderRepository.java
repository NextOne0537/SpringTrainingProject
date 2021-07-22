package tacos.data;

import tacos.Order;

/**
 * @author Timofey
 * @since 20.07.2021
 */
public interface OrderRepository {
    Order save(Order order);
}
