/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.daoImpl;

import com.company.daoInter.AbstractDao;
import com.company.daoInter.EmpHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyRzayev
 */
public class EmpHistoryDaoImpl extends AbstractDao implements EmpHistoryDaoInter {

    private EmploymentHistory getEmpHistory(ResultSet res) throws Exception {
        String header = res.getString("header");
        Date startDate = res.getDate("start_date");
        Date endDate = res.getDate("end_date");
        String jobDesc = res.getString("job_desc");
        int userId = res.getInt("user_id");

        EmploymentHistory emp = new EmploymentHistory(null, header, startDate, endDate, jobDesc, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAllEmpHistoryByUserId(int userId) {
        List<EmploymentHistory> list = new ArrayList<>();
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet res = stmt.getResultSet();
            while (res.next()) {
                EmploymentHistory emp = getEmpHistory(res);
                list.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
