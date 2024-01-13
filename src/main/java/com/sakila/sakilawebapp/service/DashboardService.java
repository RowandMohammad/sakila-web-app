package com.sakila.sakilawebapp.service;

import com.sakila.sakilawebapp.dto.DashboardStats;
import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
    DashboardStats getDashboardStats();
}
