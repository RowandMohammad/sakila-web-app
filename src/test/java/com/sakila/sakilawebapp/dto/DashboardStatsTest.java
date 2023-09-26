package com.sakila.sakilawebapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardStatsTest {

    @Test
    public void testDashboardStats() {
        DashboardStats stats = new DashboardStats();
        stats.setTotalActors(10);
        stats.setTotalFilms(20);
        stats.setTotalCategories(5);
        stats.setTotalLanguages(3);

        assertEquals(10, stats.getTotalActors());
        assertEquals(20, stats.getTotalFilms());
        assertEquals(5, stats.getTotalCategories());
        assertEquals(3, stats.getTotalLanguages());
    }
}
