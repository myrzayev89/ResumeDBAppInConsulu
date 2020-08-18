/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.daoImpl;

import com.company.bean.User;
import com.company.daoInter.AbstractDao;
import com.company.daoInter.UserDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyRzayev
 */
public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            Statement stat = c.createStatement();
            stat.execute("select * from user");
            ResultSet res = stat.getResultSet();
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String surname = res.getString("surname");
                String phone = res.getString("phone");
                String email = res.getString("email");

                list.add(new User(id, name, surname, phone, email));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?, surname=?, phone=?, email=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setInt(5, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stat = c.createStatement();
            return stat.execute("delete from user where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            Statement stat = c.createStatement();
            stat.execute("select * from user where id = " + userId);
            ResultSet res = stat.getResultSet();
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String surname = res.getString("surname");
                String phone = res.getString("phone");
                String email = res.getString("email");

                result = new User(id, name, surname, phone, email);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email) values(?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
