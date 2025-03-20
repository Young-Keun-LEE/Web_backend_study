package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;
    // GET
    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }
    // POST
    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeDto dto){
        Coffee Coffee = dto.toEntity();
        return coffeeRepository.save(Coffee);
    }
    // PATCH

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto){
        // 1. Convert DTO to Entity
        Coffee coffee = dto.toEntity();
        log.info("id: {}, Coffee: {}", id, coffee.toString());
        // 2. Retrieve Target
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 3. Handle Bad Request
        if(target == null || id != coffee.getId()){
            log.info("Bad Request! id: {}, Coffee: {}", id, coffee.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. Update and Respond with 200 OK
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(coffee);
        return ResponseEntity.status(HttpStatus.OK).body(updated);

    }
    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        // 1. Find target
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 2. Handle invalid request
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. Delete target
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
}
