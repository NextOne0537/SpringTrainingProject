package tacos;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private Long id;

    private Date createdAt;

    @ManyToMany (targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design){
        this.tacos.add(design);
    }

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }

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
    @NotBlank(message = "Credit card number is required")
    private String ccNumber;
    @Pattern (regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",message = "Expiration date is required in format mm/yy")
    private String ccExpiration;
    @Digits (integer=3, fraction=0,message = "CVV cannot be blank")
    private String ccCVV;


}