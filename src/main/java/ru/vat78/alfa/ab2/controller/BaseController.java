package ru.vat78.alfa.ab2.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/")
public class BaseController {

    @GetMapping
    public String getApiDescription() throws IOException {
        File resource = new ClassPathResource("api-swagger.json").getFile();
        return Files.readString(resource.toPath());
    }
}
