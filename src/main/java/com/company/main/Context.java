/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.daoImpl.CountryDaoImpl;
import com.company.daoImpl.EmpHistoryDaoImpl;
import com.company.daoImpl.UserDaoImpl;
import com.company.daoImpl.UserSkillDaoImpl;
import com.company.daoInter.CountryDaoInter;
import com.company.daoInter.EmpHistoryDaoInter;
import com.company.daoInter.UserDaoInter;
import com.company.daoInter.UserSkillDaoInter;

/**
 *
 * @author MyRzayev
 */
public class Context {

    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }
    
    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }
    
    public static EmpHistoryDaoInter instanceEmpHistoryDao() {
        return new EmpHistoryDaoImpl();
    }
    
    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }
    
}
