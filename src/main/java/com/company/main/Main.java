/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.daoInter.EmpHistoryDaoInter;

/**
 *
 * @author MyRzayev
 */
public class Main {
    public static void main(String[] args) throws Exception {
        EmpHistoryDaoInter emp = Context.instanceEmpHistoryDao();

//        User u = new User(0, "Ahmed", "Rzali", "+9945537230", "rza_89@inbox.ru");
//        userDao.addUser(u);
//        userDao.removeUser(4);
//        userDao.removeUser(6);
//        List<User> list = userDao.getAll();
        System.out.println(emp.getAllEmpHistoryByUserId(6));
    }
}
