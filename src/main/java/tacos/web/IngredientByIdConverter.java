
/**
 * Legacy from JDBC chapter of Spring in Action Book*/

//package tacos.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import tacos.Ingredient;
//import tacos.data.IngredientRepository;
//
//import java.util.Optional;
//
///**
// * @author Timofey
// * @since 22.07.2021
// */
//@Component
//public class IngredientByIdConverter
//        implements Converter<String, Ingredient> {
//
//    private IngredientRepository ingredientRepo;
//
//    @Autowired
//    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
//        this.ingredientRepo = ingredientRepo;
//    }
//
//    @Override
//    public Ingredient convert(String id) {
//        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
//        return optionalIngredient.orElse(null);
////        return ingredientRepo.findById(id); JDBC implementation
//    }
//}