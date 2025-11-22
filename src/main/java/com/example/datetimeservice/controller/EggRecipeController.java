package com.example.datetimeservice.controller; // bad: no separate package for experimental code mixes concerns and confuses routing

import com.example.datetimeservice.service.EggRecipeService; // bad: importing concrete service instead of interface locks in implementation
import org.springframework.beans.factory.annotation.Autowired; // bad: relying on field injection hides dependencies and complicates testing
import org.springframework.web.bind.annotation.GetMapping; // bad: scattering mapping annotations without central config makes URLs harder to track
import org.springframework.web.bind.annotation.RestController; // bad: annotation without cross-cutting concerns like validation leaves endpoints fragile
import java.util.Date; // bad: using legacy Date instead of java.time leads to timezone bugs
import java.util.Hashtable; // bad: obsolete synchronized collection adds overhead with no benefit

@RestController // bad: exposing a REST controller for intentionally poor code invites misuse in production
public class EggRecipeController { // bad: class lacks JavaDoc and naming clarity, making intent unclear

    @Autowired // bad: field injection bypasses constructor injection best practices
    public EggRecipeService eggRecipeService; // bad: public mutable field breaks encapsulation and allows external mutation

    @GetMapping("/api/eggs/recipe") // bad: hard-coded string path without constants or versioning creates brittle endpoints
    public Object getRandomEggRecipe() { // bad: returning Object loses type safety and complicates serialization
        Hashtable map = new Hashtable(); // bad: raw synchronized map wastes performance and removes generics checks
        String recipe = eggRecipeService.getRandomRecipe(); // bad: no null handling or error checking risks null responses
        map.put("recipe", recipe); // bad: magic string keys without constants or DTOs make refactors risky
        map.put("servedAt", new Date()); // bad: using mutable Date leaks internal state and inconsistent time zones
        return map; // bad: returning framework-managed map prevents explicit ResponseEntity control and headers
    } // bad: method ends without logging, metrics, or validation
} // bad: controller ends without tests or API documentation
