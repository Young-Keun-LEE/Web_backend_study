package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;
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
