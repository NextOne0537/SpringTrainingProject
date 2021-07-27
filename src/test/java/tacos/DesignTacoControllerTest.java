package tacos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tacos.web.DesignTacoController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Timofey
 * @since 27.07.2021
 */


/**Test DOES NOT work this way, because @WebMvcTest focuses exclusively on controllers,
 * but if they require some other parameters or beans, you will have hard time mocking
 * all those required components, so you should use @SpringBootTest and @AutoconfigureMockMvc */

@RunWith(SpringRunner.class)
@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testOrderController() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(content().string(containsString("Design")));
    }

}
