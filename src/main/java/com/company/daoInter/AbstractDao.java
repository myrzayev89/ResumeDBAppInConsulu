/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.daoInter;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author MyRzayev
 */
public abstract class AbstractDao {
    public Connection connect() throws Exception {
        final String DB_URL = "jdbc:mysql://localhost:3306/resume?serverTimezone=UTC";
        final String DB_USER = "root";
        final String DB_PASS = "123456";
        Connection c = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        return c;
    }
}
