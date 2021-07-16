package tacos;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import lombok.Data;

import java.sql.Date;


@Data
public class Order {

    private Long id;
    private Date createAt;

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
    @NotBlank (message = "Credit card number is required")
    private String ccNumber;
    @Pattern (regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",message = "Expiration date is required in format mm/yy")
    private String ccExpiration;
    @NotBlank (message = "CVV cannot be blank")
    private String ccCVV;
}