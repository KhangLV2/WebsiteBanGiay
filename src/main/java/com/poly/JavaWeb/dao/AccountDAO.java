package com.poly.JavaWeb.dao;

import com.poly.JavaWeb.entity.Account;
import com.poly.JavaWeb.untitil.Dbconection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {
    private Dbconection dbconection = new Dbconection();

    public Account loginAccount(String us,String ps){
        String sql = "select * from Account where [user]=? and pass=?";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,us);
            st.setObject(2,ps);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Account account = new Account();
                account.setuID(rs.getInt(1));
                account.setUser(rs.getString(2));
                account.setPass(rs.getString(3));
                account.setIsSell(rs.getInt(4));
                account.setIsAdmin(rs.getInt(5));
                return account;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Check user xem đã tồn tại chưa
    public Account checkSignUp(String username){
        String sql = "select * from Account where [user]=?";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,username);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Account account = new Account();
                account.setuID(rs.getInt(1));
                account.setUser(rs.getString(2));
                account.setPass(rs.getString(3));
                account.setIsSell(rs.getInt(4));
                account.setIsAdmin(rs.getInt(5));
                return account;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Đâng ký tài khoản
    public void addAcount(String user,String pass){
        String sql = "insert into Account values(?,?,0,0)";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,user);
            st.setObject(2,pass);
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        //Account account = new Account("Adam","123456");
        System.out.println(dao.loginAccount("Adam","123456"));
    }
}
