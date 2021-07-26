package tacos.web;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.data.OrderRepository;

/**
 * @author Timofey
 * @since 25.07.2021
 */

@Slf4j
@Data
@RestController
public class RestOrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping ("/rest")
    public Object rest (){
           return orderRepository.findAll();
    }

}
