package com.poly.JavaWeb.dao;

import com.poly.JavaWeb.entity.OrderDetails;
import com.poly.JavaWeb.untitil.Dbconection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDetailDAO {
    private Dbconection dbconection = new Dbconection();

    public void addOrderDetail(OrderDetails orderDetails){
        String sql = "insert into order_tails([product_id],[order_id],[quantity],[price]) values(?,?,?,?)";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,orderDetails.getProduct_id());
            st.setObject(2,orderDetails.getOrder_id());
            st.setObject(3,orderDetails.getQuantity());
            st.setObject(4,orderDetails.getPrice());
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
