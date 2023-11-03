package com.poly.JavaWeb.dao;

import com.poly.JavaWeb.entity.Category;
import com.poly.JavaWeb.entity.Product;
import com.poly.JavaWeb.untitil.Dbconection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {

    private Dbconection dbconection = new Dbconection();

    public ArrayList<Product> getAll(){
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from product";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //load Danh mục
    public ArrayList<Category> getCategory(){
        ArrayList<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setCid(rs.getInt(1));
                category.setCname(rs.getString(2));
                list.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //load Last Product
    public Product getLastProduct(){
        String sql = "select top 1 * from product order by id desc\n";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                return product;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //get by id
    public Product getProductByID(int id){
        String sql = "select * from product where id = ?\n";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                return product;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Load theo Category
    public ArrayList<Product> loadByCategory(int cateID){
        ArrayList<Product> lists = new ArrayList<>();
        String sql = "select * from product where cateID = ?";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,cateID);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                lists.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lists;
    }

    //Search Product
    public ArrayList<Product> getSearchByProduct(String key){
        ArrayList<Product> listProduct = new ArrayList<>();
        String sql = "select * from product where [name] like ?";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,"%"+key+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                listProduct.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listProduct;
    }

    //deleteProduct
    public void delete(int id){
        String sql = "delete product where id=?";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,id);
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //thêm sản phẩm
    public void add(Product product){
        String sql = "INSERT [dbo].[product] ([name], [image], [price], [title], [description], [cateID]) VALUES (?,?,?,?,?,?)";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,product.getName());
            st.setObject(2,product.getImg());
            st.setObject(3,product.getPrice());
            st.setObject(4,product.getTitle());
            st.setObject(5,product.getDescription());
            st.setObject(6,product.getCateID());
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //update Product
    public void update(int pid,Product product){
        String sql = "update product set [name]=?,[image]=?,price=?,title=?,[description]=?,cateID=? where id=?\n";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,product.getName());
            st.setObject(2,product.getImg());
            st.setObject(3,product.getPrice());
            st.setObject(4,product.getTitle());
            st.setObject(5,product.getDescription());
            st.setObject(6,product.getCateID());
            st.setObject(7,pid);
            st.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //pageging phân trang
    public ArrayList<Product> pagingSanPham(int index){
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY id OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setObject(1,(index-1)*5);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTitle(rs.getString(5));
                product.setDescription(rs.getString(6));
                product.setCateID(rs.getInt(7));
                product.setSell_ID(rs.getInt(8));
                list.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //đếm tổng sản phẩm
    public int getTatalSP(){
        String sql = "select COUNT(*) from product";
        try(Connection con = dbconection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              return rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    public static void main(String[] args) {
        new ProductDAO().pagingSanPham(2).forEach(e -> System.out.println(e.toString()));
       // new ProductDAO().delete(18);
//        ProductDAO dao = new ProductDAO();
//        System.out.println(dao.getTatalSP());
//        Product product = dao.getProductByID(10);
//        System.out.println(product.toString());
//        Product product = new Product("Men''s Outdoors Waterproof Boots Sports Shoes","https://canary.contestimg.wish.com/api/webimage/5e0db090b6383fa7bb0416ab-large.jpg?cache_buster=92297459781628185d780669109c00d6",500,
//                "Men''s Outdoors Waterproof Boots Sports Shoes Snow Boots Hiking Boots Men''s Winter Warm Boots","Men''s Outdoors Waterproof Boots Sports Shoes Snow Boots Hiking Boots Men''s Winter Warm Boots",3);
//        dao.add(product);
    }
}
