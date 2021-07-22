package tacos;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@Data
public class Order {

    private Long id;
    private Date createdAt;
    private List<Taco> tacos = new ArrayList<>();

    @NotBlank (message = "Name is required")
    private String name;
    @NotBlank (message = "Street is required")
    private String street;
    @NotBlank (message = "City is required")
    private String city;
    @NotBlank (message = "State is required")
    private String state;
    @NotBlank (message = "Zip is required")
    private String zip;
    @CreditCardNumber(message = "Credit card number is required")
    private String ccNumber;
    @Pattern (regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",message = "Expiration date is required in format mm/yy")
    private String ccExpiration;
    @Digits (integer=3, fraction=0,message = "CVV cannot be blank")
    private String ccCVV;

    public void addDesign(Taco taco){
        tacos.add(taco);
    }
}