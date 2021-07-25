// tag::all[]
// tag::allButValidation[]
package tacos;
import java.util.Date;
import java.util.List;
// end::allButValidation[]
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
// tag::allButValidation[]
import lombok.Data;
import lombok.Generated;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  private Date createdAt;

  @ManyToMany(targetEntity = Ingredient.class)
  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<Ingredient> ingredients;

  @PrePersist
  private void createdAt(){
    this.createdAt = new Date();
  }
}
