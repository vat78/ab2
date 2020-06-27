package ru.vat78.alfa.ab2.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.vat78.alfa.ab2.dto.UserStats;
import ru.vat78.alfa.ab2.model.Payment;

import java.util.*;

@Service
public class AnalyticsService {

    private Map<String, UserData> dataByUser = new HashMap<>();

    public void addPayment(Payment payment) {
        String userId = payment.getUserId();
        dataByUser.put(userId,
                dataByUser.getOrDefault(userId, new UserData(userId)).addNewData(payment.getCategoryId(), payment.getAmount()));
    }

    public Collection<UserData> getAllAnalytic() {
        return dataByUser.values();
    }

    public Optional<UserData> getUserData(String userId) {
        return Optional.ofNullable(dataByUser.get(userId));
    }

    public Optional<UserStats> getUserStats(String userId) {
        return Optional.ofNullable(dataByUser.get(userId))
                .map(this::calcStatistics);
    }

    private UserStats calcStatistics(UserData userData) {
        return UserStats.builder()
                .rareCategoryId(userData.analyticInfo.values().stream()
                        .min(Comparator.comparing(a -> a.counter))
                        .map(AnalyticsData::getCategoryId).get())
                .oftenCategoryId(userData.analyticInfo.values().stream()
                        .max(Comparator.comparing(a -> a.counter))
                        .map(AnalyticsData::getCategoryId).get())
                .maxAmountCategoryId(userData.analyticInfo.values().stream()
                        .max(Comparator.comparing(a -> a.total))
                        .map(AnalyticsData::getCategoryId).get())
                .minAmountCategoryId(userData.analyticInfo.values().stream()
                        .min(Comparator.comparing(a -> a.total))
                        .map(AnalyticsData::getCategoryId).get())
                .build();
    }

    @Data
    public static class AnalyticsData {
        private Integer categoryId;
        private Long min = Long.MAX_VALUE;
        private Long max = Long.MIN_VALUE;
        private Long total = Long.valueOf(0L);
        private Long counter = Long.valueOf(0L);

        public AnalyticsData(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public AnalyticsData addNewValue(Integer val) {
            if (min > val) {
                min = Long.valueOf(val);
            }
            if (max < val) {
                max = Long.valueOf(val);
            }
            total = Long.valueOf(total + val);
            counter = Long.valueOf(counter + 1);
            return this;
        }
    }

    @Data
    public static class UserData {
        String userId;
        Long total = Long.valueOf(0L);;
        Map<Integer, AnalyticsData> analyticInfo = new HashMap<>();

        UserData(String userId) {
            this.userId = userId;
        }

        public UserData addNewData(Integer categoryId, Integer amount) {
            total = Long.valueOf(total + amount);
            analyticInfo.put(categoryId,
                    analyticInfo.getOrDefault(categoryId, new AnalyticsData(categoryId)).addNewValue(amount));
            return this;
        }
    }
}
