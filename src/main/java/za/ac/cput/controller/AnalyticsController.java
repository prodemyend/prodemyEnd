package za.ac.cput.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.domain.Analytics;
import za.ac.cput.service.IAnalyticsService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final IAnalyticsService analyticsService;

    public AnalyticsController(IAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping
    public ResponseEntity<Analytics> getAnalytics() {
        return ResponseEntity.ok(analyticsService.getAnalytics());
    }
}
