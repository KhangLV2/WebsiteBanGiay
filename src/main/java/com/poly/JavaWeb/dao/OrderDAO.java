package com.poly.JavaWeb.dao;

import com.poly.JavaWeb.entity.Order;
import com.poly.JavaWeb.untitil.Dbconection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderDAO {
    private Dbconection dbconection = new Dbconection();

    public int add(Order order){
        String sql = "insert into orders([customer_name],[address],[phone],[total]) values(?,?,?,?)";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setObject(1,order.getCustomer_name());
            st.setObject(2,order.getAddress());
            st.setObject(3,order.getPhone());
            st.setObject(4,order.getTotal());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()){
                return rs.getInt(1);
            }
           rs.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
