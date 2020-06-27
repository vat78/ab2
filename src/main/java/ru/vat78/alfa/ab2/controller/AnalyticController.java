package ru.vat78.alfa.ab2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytic")
public class AnalyticController {

    @GetMapping
    public String getAllAnalytics(){

        return "";
    }

    @GetMapping("/{userId}")
    public String getAnalyticByUser(@PathVariable String userId){

        return "";
    }

    @GetMapping("/{userId}/templates")
    public String getTemplatesByUser(@PathVariable String userId){

        return "";
    }

    @GetMapping("/{userId}/stats")
    public String getStatsByUser(@PathVariable String userId){

        return "";
    }
}
