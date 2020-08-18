/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.daoImpl.UserDaoImpl;
import com.company.daoInter.UserDaoInter;

/**
 *
 * @author MyRzayev
 */
public class Context {

    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }
    
}
