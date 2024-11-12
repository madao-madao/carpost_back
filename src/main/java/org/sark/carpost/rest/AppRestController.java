package org.sark.carpost.rest;

import jakarta.validation.Valid;
import org.sark.carpost.dto.RegisterRequestDTO;
import org.sark.carpost.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppRestController {
    @Autowired
    private HelloService helloService;


//    @GetMapping("/api/profile")
//    public ResponseEntity myFirstMethod(){
//        return ResponseEntity.ok();
//    }
    @PostMapping("/api/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok("");
    }
//    @PostMapping("/api/profile/update")
//    public ResponseEntity updateProfile(@Valid @RequestBody){
//        return ResponseEntity.ok("");
//    }

}
