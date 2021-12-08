package kz.edu.nu.hotel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health")
@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        System.out.println("Kirill mal");
        return "API is up!!!!";
    }

}
