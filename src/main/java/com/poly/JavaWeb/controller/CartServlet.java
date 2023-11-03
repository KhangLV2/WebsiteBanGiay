package com.poly.JavaWeb.controller;

import com.poly.JavaWeb.dao.OrderDAO;
import com.poly.JavaWeb.dao.OrderDetailDAO;
import com.poly.JavaWeb.dao.ProductDAO;
import com.poly.JavaWeb.entity.Order;
import com.poly.JavaWeb.entity.OrderDetails;
import com.poly.JavaWeb.entity.Product;
import com.poly.JavaWeb.entity.ProductCart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet", value = {
        "/addGioHang",
        "/cart-item",
        "/delete-product",
        "/checkout-success",
        "/checkOut",
        "/updateQuantity",
        "/byNowGioHang"
})
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri  = request.getRequestURI();
        if(uri.contains("cart-item")){
            this.showCart(request,response);
        }else if (uri.contains("checkout-success")){
            this.checkOutThanhCong(request,response);
        }else if (uri.contains("updateQuantity")){
            this.updateSL(request,response);
        }
    }

    private void updateSL(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        String tnum = request.getParameter("num").trim();
        Integer num = Integer.parseInt(tnum);

        Product product = productDAO.getProductByID(id);
        HttpSession session = request.getSession();
        ProductCart productCart;
        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
        if (cart==null){
            cart = new HashMap<>();
            productCart = new ProductCart(quantity,product);
            cart.put(id,productCart);
        }else{
            if (cart.containsKey(id)&&num==-1){
                productCart = cart.get(id);
                productCart.reduceQuantity();
            }else {
                productCart = cart.get(id);
                productCart.incrementQuantity();
            }
        }

        session.setAttribute("cart",cart);
        response.sendRedirect("cart-item");
    }

    private void checkOutThanhCong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/checkOut.jsp").forward(request,response);
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        int total=0;
//        HashMap<Integer,ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
//        if (cart!=null){
//            for(Map.Entry<Integer,ProductCart> entry : cart.entrySet()){
//                total+= entry.getValue().quantity*entry.getValue().product.getPrice();
//            }
//        }

        HttpSession session = request.getSession();
        int total=0;
        HashMap<Integer,ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
        if (cart!=null){
            for (Map.Entry<Integer,ProductCart> entry: cart.entrySet()){
                total+=entry.getValue().product.getPrice()*entry.getValue().quantity;
            }
        }

        request.setAttribute("total",total);
        request.setAttribute("cart",cart);
        request.getRequestDispatcher("/views/shoppingCart.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri  = request.getRequestURI();
        if(uri.contains("addGioHang")){
            this.addSanPhamVaoGioHang(request,response);
        }else if (uri.contains("delete-product")){
            this.deleteCart(request,response);
        }else if (uri.contains("checkOut")){
            this.checkOut(request,response);
        }else if (uri.contains("byNowGioHang")){
            this.byNow(request,response);
        }
    }

    private void byNow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.getProductByID(id);
        HttpSession session = request.getSession();
        ProductCart productCart;
        HashMap<Integer,ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");

        if (cart==null){
            cart = new HashMap<>();
            productCart = new ProductCart(1,product);
            cart.put(id,productCart);
        }else {
            if (cart.containsKey(id)){
                productCart = cart.get(id);
                productCart.incrementQuantity();
            }else {
                productCart = new ProductCart(1,product);
                cart.put(id,productCart);
            }
        }

        session.setAttribute("cart",cart);
        response.sendRedirect("cart-item");
    }


    private void checkOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int tong = 0;
        HttpSession session = request.getSession();
        HashMap<Integer,ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");

            for(Map.Entry<Integer,ProductCart> entry : cart.entrySet()){
                tong+= entry.getValue().quantity*entry.getValue().product.getPrice();
            }

        Order order = new Order(name,address,phone,tong);
        int orderID = orderDAO.add(order);

        for(Map.Entry<Integer,ProductCart> productCart : cart.entrySet()){
            OrderDetails orderDetails = new OrderDetails(productCart.getValue().product.getId(),orderID,productCart.getValue().quantity,(int)(productCart.getValue().product.getPrice()*productCart.getValue().quantity));
            orderDetailDAO.addOrderDetail(orderDetails);
        }

        session.setAttribute("cart",null);
        response.sendRedirect("checkout-success");
    }

    private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Integer id = Integer.parseInt(request.getParameter("id"));
//        HttpSession session = request.getSession();
//        HashMap<Integer,ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
//        if (cart.containsKey(id)){
//            cart.remove(id);
//        }

        Integer id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
        if (cart.containsKey(id)){
            cart.remove(id);
        }

        response.sendRedirect("cart-item");
    }

    private void addSanPhamVaoGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Integer id = Integer.parseInt(request.getParameter("id"));
//        Product product = productDAO.getProductByID(id);
//        HttpSession session = request.getSession();
//        ProductCart productCart;
//        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
//
//        if (cart==null){
//            cart = new HashMap<>();
//            productCart = new ProductCart(1,product);
//            cart.put(id,productCart);
//        }else {
//            if (cart.containsKey(id)){
//                productCart = cart.get(id);
//                productCart.incrementQuantity();
//            }else {
//                productCart = new ProductCart(1,product);
//                cart.put(id,productCart);
//            }
//        }

        Integer id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.getProductByID(id);
        HttpSession session = request.getSession();
        ProductCart productCart;
        HashMap<Integer,ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");

        if (cart==null){
            cart = new HashMap<>();
            productCart = new ProductCart(1,product);
            cart.put(id,productCart);
        }else {
            if (cart.containsKey(id)){
                productCart = cart.get(id);
                productCart.incrementQuantity();
            }else {
                productCart = new ProductCart(1,product);
                cart.put(id,productCart);
            }
        }

        session.setAttribute("cart",cart);
        response.sendRedirect("loadProduct");

    }
}
