// tag::all[]
// tag::allButValidation[]
package tacos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull (message = "Name cannot be empty")
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  private Date createdAt;

  @ManyToMany(targetEntity = Ingredient.class)
  @NotNull (message = "Choose at least 3 ingredients")
  @Size(min = 3, message = "You must choose at least 3 ingredients")
  private List<Ingredient> ingredients;

  @PrePersist
  private void createdAt(){
    this.createdAt = new Date();
  }
}
