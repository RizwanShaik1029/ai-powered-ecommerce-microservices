package com.srb.ai_service.controller;

import com.srb.ai_service.service.Ai_ServiceImpl;
import com.srb.product.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class Ai_controller {

    private final Ai_ServiceImpl aiService;

    public Ai_controller(Ai_ServiceImpl aiService)
    {
        this.aiService = aiService;
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String qry)
    {
        return aiService.getAllProductDetails(qry);
    }

}
