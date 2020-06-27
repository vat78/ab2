package ru.vat78.alfa.ab2.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.vat78.alfa.ab2.model.Payment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AnalyticsService {

    private Map<String, UserData> dataByUser = new HashMap<>();
    private Map<Integer, AnalyticsData> totalByCategory = new HashMap<>();


    public void addPayment(Payment payment) {
        String userId = payment.getUserId();
        dataByUser.put(userId,
                dataByUser.getOrDefault(userId, new UserData(userId)).addNewData(payment.getCategoryId(), payment.getAmount()));
        Integer category = payment.getCategoryId();
        totalByCategory.put(category,
                totalByCategory.getOrDefault(category, new AnalyticsData()).addNewValue(payment.getAmount()));
    }

    public Collection<UserData> getAllAnalytic() {
        return dataByUser.values();
    }

    public Optional<UserData> getUserData(String userId) {
        return Optional.ofNullable(dataByUser.get(userId));
    }

    @Data
    public static class AnalyticsData {
        private Long min = Long.MAX_VALUE;
        private Long max = Long.MIN_VALUE;
        private Long total = Long.valueOf(0L);

        public AnalyticsData addNewValue(Integer val) {
            if (min > val) {
                min = Long.valueOf(val);
            }
            if (max < val) {
                max = Long.valueOf(val);
            }
            total = Long.valueOf(total + val);
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
                    analyticInfo.getOrDefault(categoryId, new AnalyticsData()).addNewValue(amount));
            return this;
        }
    }
}
