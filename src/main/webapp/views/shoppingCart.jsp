<%@ page import="java.util.HashMap" %>
<%@ page import="com.poly.JavaWeb.entity.ProductCart" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>EShopper - Bootstrap Shop Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="Free HTML Templates" name="keywords" />
    <meta content="Free HTML Templates" name="description" />

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon" />

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
            rel="stylesheet"
    />

    <!-- Font Awesome -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
            rel="stylesheet"
    />

    <!-- Libraries Stylesheet -->
    <!-- <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet"> -->

    <!-- Customized Bootstrap Stylesheet -->
    <link href="/views/style.css" rel="stylesheet" />
</head>

<body>

        <jsp:include page="menu.jsp"></jsp:include>

<!-- Cart Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <div class="col-lg-8 table-responsive mb-5">
            <table class="table table-bordered text-center mb-0">

                <c:if test="${cart!=null}">
                <thead class="bg-secondary text-dark">
                <tr>
                    <th>Products</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Remove</th>
                </tr>
                </thead>

                    <%
                        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) request.getAttribute("cart");
                        for(Map.Entry<Integer,ProductCart> entry: cart.entrySet()){
                            Integer key = entry.getKey();
                            ProductCart productCart = entry.getValue();

                    %>

                    <tbody class="align-middle">
                    <tr>
                        <td class="align-middle">
                            <img src="<%= productCart.product.getImg()%>" alt="" style="width: 50px" />
                            <%= productCart.product.getName()%>
                        </td>
                        <td class="align-middle">$<%= productCart.product.getPrice()%></td>
                        <td class="align-middle">
                            <div class="input-group quantity mx-auto"
                                    style="width: 100px">



                                <div class="input-group-btn">
                                    <c:if test="<%= productCart.quantity>1%>">
                                        <button class="btn btn-sm btn-primary btn-minus">
                                            <a href="/updateQuantity?num=-1&id=<%= productCart.product.getId()%>&quantity=<%= productCart.quantity%>" style="color: black"><i class="fa fa-minus"></i></a>
                                        </button>
                                    </c:if>

                                </div>

                                <input type="text" name="quantity" class="form-control form-control-sm bg-secondary text-center" value="<%= productCart.quantity%>"/>

                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-primary btn-plus">
                                         <a href="/updateQuantity?num=1&id=<%= productCart.product.getId()%>&quantity=<%= productCart.quantity%>" style="color: black"><i class="fa fa-plus"></i></a>
                                    </button>
                                </div>



                            </div>
                        </td>
                        <td>
                            <form action="/delete-product" method="POST">
                                <input type="hidden" name="id" value="<%= productCart.product.getId()%>">
                                <button type="submit" class="btn btn-primary">Delete</button>
                            </form>
                        </td>
                    </tr>
                    </tbody>

                   <% } %>
                </c:if>
            </table>

            <c:if test="${cart==null}">
                <div class="alert alert-warning" role="alert">
                    Chưa có sản phẩm nào trong giỏ hàng
                </div>
            </c:if>
        </div>

        <!-- Right -->
        <div class="col-lg-4">
            <form action="/checkOut" method="post">
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Họ tên</label>
                        <input type="type" class="form-control" name="name" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Phone</label>
                        <input type="type" class="form-control" name="phone" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Địa chỉ</label>
                        <textarea
                                rows=""
                                cols=""
                                name="address"
                                class="form-control"
                        ></textarea>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Total</h5>
                            <h5 class="font-weight-bold">$${total}</h5>
                        </div>
                        <button
                                type="submit"
                                class="btn btn-block btn-primary my-3 py-3"
                        >
                            Đặt đơn hàng
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>


</div>

<!-- Cart End -->

        <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

