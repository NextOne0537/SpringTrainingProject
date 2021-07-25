package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;


import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import org.springframework.validation.Errors;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping ("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientRepository ingredientRepo;
    private TacoRepository designRepo;

    @ModelAttribute (name = "design")
    public Taco design(){
        return new Taco();
    }

    @ModelAttribute (name = "order")
    public Order order(){
        return new Order();
    }

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, String type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }

    @GetMapping
    public String showDesignForm(Model model){
        //old hardcoded data for ingredients
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        List<String>types = new ArrayList<>();

        ingredients.forEach(ingredient -> types.add(ingredient.getType()));

        for (String type : types){
            model.addAttribute(type.toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }



    //tag::processDesignValidated[]
    @PostMapping
    public String processDesign

    (@Valid Taco design, Errors errors, @ModelAttribute Order order) {

        System.out.println("Errors: " + errors.hasErrors());
        errors.getAllErrors().forEach(System.out::println);
        System.out.println(errors.getErrorCount()+" - error count");

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = designRepo.save(design);
        order.addDesign(saved);

//        model.addAttribute("order", order);

        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }
}
