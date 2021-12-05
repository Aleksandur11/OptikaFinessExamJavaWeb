package com.example.optikafiness.service;

import com.example.optikafiness.model.entity.view.StatsView;

public interface StatsService {
    void onRequest();

    StatsView getStats();

}
