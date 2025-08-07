package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Analytics;
import za.ac.cput.factory.AnalyticsFactory;

@Service
public class AnalyticsService implements IAnalyticsService {

    private final AnalyticsFactory analyticsFactory;

    public AnalyticsService(AnalyticsFactory analyticsFactory) {
        this.analyticsFactory = analyticsFactory;
    }

    @Override
    public Analytics getAnalytics() {
        return analyticsFactory.createAnalytics();
    }
}

