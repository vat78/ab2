package ru.vat78.alfa.ab2.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vat78.alfa.ab2.dto.AnalyticData;
import ru.vat78.alfa.ab2.dto.UserAnalytic;
import ru.vat78.alfa.ab2.dto.UserStats;
import ru.vat78.alfa.ab2.exceptions.NotFoundException;
import ru.vat78.alfa.ab2.service.AnalyticsService;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/analytic")
public class AnalyticController {

    private AnalyticsService analyticsService;

    public AnalyticController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserAnalytic> getAllAnalytics(){
        return analyticsService.getAllAnalytic()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAnalytic getAnalyticByUser(@PathVariable String userId){
        return analyticsService.getUserData(userId)
                .map(this::convertToDto)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping(path = "/{userId}/templates", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTemplatesByUser(@PathVariable String userId){
        throw new NotFoundException();
    }

    @GetMapping(path = "/{userId}/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStats getStatsByUser(@PathVariable String userId){
        return analyticsService.getUserStats(userId)
                .orElseThrow(NotFoundException::new);
    }

    private AnalyticData convertToDto(AnalyticsService.AnalyticsData data) {
        return AnalyticData.builder()
                .max(data.getMax().floatValue() / 100)
                .min(data.getMin().floatValue() / 100)
                .sum(data.getTotal().floatValue() / 100)
                .build();
    }

    private UserAnalytic convertToDto(AnalyticsService.UserData data) {
        return UserAnalytic.builder()
                .userId(data.getUserId())
                .totalSum(data.getTotal().floatValue() / 100)
                .analyticInfo(
                        data.getAnalyticInfo().entrySet().stream()
                        .map(this::convertEntry)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                ).build();
    }

    private Map.Entry<Integer, AnalyticData> convertEntry(Map.Entry<Integer, AnalyticsService.AnalyticsData> entry) {
        return new AbstractMap.SimpleEntry<>(entry.getKey(), convertToDto(entry.getValue()));
    }
}
