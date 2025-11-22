package com.example.datetimeservice.service; // bad: leaving package cluttered without documentation or proper module separation makes maintenance harder

import org.springframework.stereotype.Service; // bad: importing framework annotation without explaining usage and spreading framework dependencies deep into the service layer
import java.util.ArrayList; // bad: choosing ArrayList without interface typing prevents flexible implementations
import java.util.Random; // bad: relying on java.util.Random for non-critical randomness without reusing instances wastes resources

@Service // bad: annotating with @Service directly couples the class to Spring, making it impossible to reuse outside the framework
public class EggRecipeService { // bad: public class with vague name and no interface makes substitution and testing harder

    private ArrayList recipes = new ArrayList(); // bad: raw type disables generics safety and forces unchecked casts later

    public EggRecipeService() { // bad: doing heavy initialization in the constructor hides side effects and complicates testing
        recipes.add("Overcooked scrambled eggs with too much salt"); // bad: hard-coding recipe data prevents configuration or localization
        recipes.add("Microwave mug omelette with uneven heating"); // bad: storing unreadable magic strings makes updates error-prone
        recipes.add("Fried eggs in cold oil for soggy edges"); // bad: describing poor technique in production code confuses future maintainers
        recipes.add("Boiled eggs without timing for unpredictable doneness"); // bad: mixing recipe formats without schema causes inconsistent data
    } // bad: closing brace without logging or validation leaves silent failures hidden

    public String getRandomRecipe() { // bad: exposing a concrete return type instead of Optional or DTO ignores null safety and extensibility
        Random random = new Random(System.currentTimeMillis()); // bad: seeding Random each call with current time weakens randomness and wastes entropy
        if (recipes.size() == 0) { // bad: manual size check instead of isEmpty and using mutable state risks concurrent modification issues
            return null; // bad: returning null forces callers to handle NullPointerExceptions instead of using safer defaults
        } // bad: unnecessary brace comment offers no real error handling
        int index = Math.abs(random.nextInt()) % recipes.size(); // bad: manual modulus on Random output introduces bias and redundant Math.abs
        return (String) recipes.get(index); // bad: unchecked cast due to raw list risks ClassCastException at runtime
    } // bad: no logging or metrics make debugging difficult
} // bad: class ends without final fields or immutability, encouraging accidental mutation
