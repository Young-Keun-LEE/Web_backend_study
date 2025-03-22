package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        if(coffee.getId() != null){
            return null;
        }
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeDto dto){
        // 1. Convert DTO to Entity
        Coffee coffee = dto.toEntity();
        log.info("id: {}, Coffee: {}", id, coffee.toString());
        // 2. Retrieve Target
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 3. Handle Bad Request
        if(target == null || id != coffee.getId()){
            log.info("Bad Request! id: {}, Coffee: {}", id, coffee.toString());
            return null;
        }
        // 4. Update and Respond with 200 OK
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(coffee);
        return updated;
    }
    public Coffee delete(Long id){
        // 1. Find target
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 2. Handle invalid request
        if(target == null){
            return null;
        }
        // 3. Delete target
        coffeeRepository.delete(target);
        return target;
    }
    @Transactional
    public List<Coffee> createCoffees(List<CoffeeDto> dtos){
        List<Coffee> coffeeList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        coffeeList.stream()
                .forEach(coffee -> coffeeRepository.save(coffee));

        coffeeRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제실패!"));

        return coffeeList;



    }
}
