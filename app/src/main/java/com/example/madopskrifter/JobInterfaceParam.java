package com.example.madopskrifter;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JobInterfaceParam{
    void doJob(ResultSet resultSet) throws SQLException;
}
