package com.example.madopskrifter;

import java.sql.ResultSet;

public interface JobInterface {
    void doJob();
    void doJob(ResultSet resultSet);
}
