package com.poly.JavaWeb.controller;

import com.poly.JavaWeb.dao.AccountDAO;
import com.poly.JavaWeb.dao.ProductDAO;
import com.poly.JavaWeb.entity.Account;
import com.poly.JavaWeb.entity.Category;
import com.poly.JavaWeb.entity.Product;
import com.poly.JavaWeb.entity.ProductCart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ProductServlet", value = {
        "/loadProduct",
        "/detail",
        "/loadCategory",
        "/search",
        "/managerProduct",
        "/delete",
        "/add",
        "/viewUpdate", //get
        "/update",     //post
        "/form",
        "/login",
        "/signup",
        "/logout",
        "/getPagingSP"
})
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("loadProduct")){
            this.loadSanPham(request,response);
        }else if (uri.contains("detail")){
            this.viewDetail(request,response);
        }else if (uri.contains("loadCategory")){
            this.loadTheoDM(request,response);
        }else if (uri.contains("managerProduct")){
            this.quanLyProducts(request,response);
        }else if (uri.contains("delete")){
            this.deleteProduct(request,response);
        }else if (uri.contains("viewUpdate")){
            this.viewUpdateProduct(request,response);
        }else if (uri.contains("form")){
            this.formDangNhap(request,response);
        }else if (uri.contains("logout")){
            this.logOutWeb(request,response);
        }else if (uri.contains("getPagingSP")){
            this.phanTrang(request,response);
        }

    }

    private void phanTrang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //load Category
//        ArrayList<Category> danhMuc = productDAO.getCategory();
//        request.setAttribute("listCC",danhMuc);
//        //
//        String indexPage = request.getParameter("index");
//        if (indexPage==null){
//            indexPage="1";
//        }
//        int index = Integer.parseInt(indexPage);
//
//        int count = productDAO.getTatalSP();
//        int endPage = count/5;
//        if (count%5!=0){
//            endPage++;
//        }
//
//        ArrayList<Product> listSP = productDAO.pagingSanPham(index);
//        request.setAttribute("listP",listSP);
//        request.setAttribute("endP",endPage);
//        request.setAttribute("tag",index);
//
//        request.getRequestDispatcher("/views/manager.jsp").forward(request,response);
    }

    private void logOutWeb(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
        session.setAttribute("cart",null);
        session.removeAttribute("acc");
        response.sendRedirect("loadProduct");
    }

    private void formDangNhap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //b1: get user,pass trên cookie
        Cookie arr[] = request.getCookies();

        if (arr!=null){
            for(Cookie o:arr){
                if (o.getName().equals("userC")){
                    request.setAttribute("username",o.getValue());
                }
                if (o.getName().equals("passC")){
                    request.setAttribute("password",o.getValue());
                }
            }
        }

        //b2: xét user,pass vào login form
        request.getRequestDispatcher("/views/login.jsp").forward(request,response);
    }

    private void viewUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //load Category
        ArrayList<Category> danhMuc = productDAO.getCategory();
        request.setAttribute("listCC",danhMuc);
        //
        int id = Integer.parseInt(request.getParameter("pid"));
        Product product = productDAO.getProductByID(id);
        request.setAttribute("viewProduct",product);
        request.getRequestDispatcher("/views/updateForm.jsp").forward(request,response);

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("pid"));
        productDAO.delete(id);
        response.sendRedirect("managerProduct");
    }

    private void quanLyProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //load Category
//        ArrayList<Category> danhMuc = productDAO.getCategory();
//        request.setAttribute("listCC",danhMuc);
//        //
//        ArrayList<Product> products = productDAO.getAll();
//        request.setAttribute("listP",products);
//        request.getRequestDispatcher("/views/manager.jsp").forward(request,response);

        //load Category
        ArrayList<Category> danhMuc = productDAO.getCategory();
        request.setAttribute("listCC",danhMuc);
        //
        String indexPage = request.getParameter("index");
        if (indexPage==null){
            indexPage="1";
        }
        int index = Integer.parseInt(indexPage);

        int count = productDAO.getTatalSP();
        int endPage = count/5;
        if (count%5!=0){
            endPage++;
        }

        ArrayList<Product> listSP = productDAO.pagingSanPham(index);
        request.setAttribute("listP",listSP);
        request.setAttribute("endP",endPage);
        request.setAttribute("tag",index);

        request.getRequestDispatcher("/views/manager.jsp").forward(request,response);
    }


    private void loadTheoDM(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Last product
        Product lsProduct = productDAO.getLastProduct();
        request.setAttribute("lastProduct",lsProduct);
        //load Category
        ArrayList<Category> danhMuc = productDAO.getCategory();
        request.setAttribute("listCategory",danhMuc);
        //
        int newCateID = Integer.parseInt(request.getParameter("cateID"));
        ArrayList<Product> listDM = productDAO.loadByCategory(newCateID);
        request.setAttribute("listProduct",listDM);
        request.getRequestDispatcher("/views/home.jsp").forward(request,response);
    }

    private void viewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.getProductByID(id);
        request.setAttribute("viewProduct",product);
        request.getRequestDispatcher("/views/detail.jsp").forward(request,response);
    }

    private void loadSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Last product
        Product lsProduct = productDAO.getLastProduct();
        request.setAttribute("lastProduct",lsProduct);
        //load Category
        ArrayList<Category> danhMuc = productDAO.getCategory();
        request.setAttribute("listCategory",danhMuc);
        //
        ArrayList<Product> products = productDAO.getAll();
        request.setAttribute("listProduct",products);
        request.getRequestDispatcher("/views/home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("search")){
            this.searchProduct(request,response);
        }else if (uri.contains("add")){
            this.themProduct(request,response);
        }else if (uri.contains("update")){
            this.updateProduct(request,response);
        }else if (uri.contains("login")){
            this.dangNhapHeThong(request,response);
        }else if (uri.contains("signup")){
            this.dangKyTaiKhoan(request,response);
        }
    }

    private void dangKyTaiKhoan(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("repass");

        if (!pass.equals(re_pass)){
            response.sendRedirect("form");
        }else {
            Account a = accountDAO.checkSignUp(user);
            if (a==null){
                accountDAO.addAcount(user,pass);
                response.sendRedirect("loadProduct");
            }else {
                response.sendRedirect("form");
            }
        }
    }

    private void dangNhapHeThong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String remember = request.getParameter("remember");

        Account a = accountDAO.loginAccount(user,pass);
        if (a==null){
            String mess = "Tài khoản hoặc mật khẩu không chính xác";
            request.setAttribute("message",mess);
            request.getRequestDispatcher("/views/login.jsp").forward(request,response);
        }else {
            //Session dùng chủ yếu là để phân quyền
            HttpSession session = request.getSession();
            session.setAttribute("acc",a);

            //Lưu account trên Cookie
            Cookie u = new Cookie("userC",user);
            Cookie p = new Cookie("passC",pass);
            //Set thời gian cho Cookie
            u.setMaxAge(60*60);

            if (remember!=null){
                p.setMaxAge(60*60);
            }else {
                p.setMaxAge(0);
            }

            //lưu u,p lên trên chrome
            response.addCookie(u);
            response.addCookie(p);

            response.sendRedirect("loadProduct");
        }

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String newName = request.getParameter("name");
        String newImg = request.getParameter("image");
        double newPrice = Double.parseDouble(request.getParameter("price"));
        String newTitle = request.getParameter("title");
        String newDescription = request.getParameter("description");
        int newCateID = Integer.parseInt(request.getParameter("category"));

        Product product = new Product(newName,newImg,newPrice,newTitle,newDescription,newCateID);
        productDAO.update(id,product);
        response.sendRedirect("managerProduct");
    }

    private void themProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newName = request.getParameter("name");
        String newImg = request.getParameter("image");
        double newPrice = Double.parseDouble(request.getParameter("price"));
        String newTitle = request.getParameter("title");
        String newDescription = request.getParameter("description");
        int newCateID = Integer.parseInt(request.getParameter("category"));

        Product product = new Product(newName,newImg,newPrice,newTitle,newDescription,newCateID);
        productDAO.add(product);
        response.sendRedirect("managerProduct");
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Last product
        Product lsProduct = productDAO.getLastProduct();
        request.setAttribute("lastProduct",lsProduct);
        //load Category
        ArrayList<Category> danhMuc = productDAO.getCategory();
        request.setAttribute("listCategory",danhMuc);
        //
        String key = request.getParameter("txt");
        ArrayList<Product> listC = productDAO.getSearchByProduct(key);
        request.setAttribute("listProduct",listC);
        request.getRequestDispatcher("/views/home.jsp").forward(request,response);
    }
}
