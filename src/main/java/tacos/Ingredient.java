package tacos;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Entity// - added in JPA implementation
public class Ingredient {
    @Id // - added in JPA implementation
    private final String id;
    private final String name;
    private final String type;



//    public static enum Type{
//        WRAP,
//        PROTEIN,
//        VEGGIES,
//        CHEESE,
//        SAUCE
//    }
}
