/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.daoImpl;

import com.company.entity.Country;
import com.company.entity.User;
import com.company.daoInter.AbstractDao;
import com.company.daoInter.UserDaoInter;
import com.company.entity.UserSkill;
import java.sql.Connection;
import java.sql.Date;
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

    private User getUser(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String name = res.getString("name");
        String surname = res.getString("surname");
        String phone = res.getString("phone");
        String email = res.getString("email");
        String profilDesc = res.getString("profil_desc");
        String adress = res.getString("adress");
        int nationalityId = res.getInt("nationality_id");
        int birthplaceId = res.getInt("birthplace_id");
        String nationalityStr = res.getString("nationality");
        String birthplaceStr = res.getString("birthplace");
        Date birthdate = res.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, phone, email, profilDesc, adress, birthdate, nationality, birthplace);
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            Statement stat = c.createStatement();
            stat.execute("select "
                    + "u.*, "
                    + "n.nationality, "
                    + "c.name as birthplace "
                    + "from user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id");
            ResultSet res = stat.getResultSet();
            while (res.next()) {
                User u = getUser(res);
                list.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?, surname=?, phone=?, email=?, profil_desc=?, adress=?, birthdate=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfilDesc());
            stmt.setString(6, u.getAdress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getId());
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
    public User getById(int id) {
        User result = null;
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            Statement stat = c.createStatement();
            stat.execute("select "
                    + "u.*, "
                    + "n.nationality, "
                    + "c.name as birthplace "
                    + "from user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id where u.id = " + id);
            ResultSet res = stat.getResultSet();
            while (res.next()) {
                result = getUser(res);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email,profil_desc,adress) values(?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfilDesc());
            stmt.setString(6, u.getAdress());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
