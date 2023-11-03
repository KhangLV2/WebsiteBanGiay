
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
    <link
            href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            rel="stylesheet"
            id="bootstrap-css"
    />
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <link
            href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
            rel="stylesheet"
            integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
            crossorigin="anonymous"
    />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <style>
        /*
  ** Style Simple Ecommerce Theme for Bootstrap 4
  ** Created by T-PHP https://t-php.fr/43-theme-ecommerce-bootstrap-4.html
  */
        .bloc_left_price {
            color: #c01508;
            text-align: center;
            font-weight: bold;
            font-size: 150%;
        }

        .category_block li:hover {
            background-color: #007bff;
        }

        .category_block li:hover a {
            color: #ffffff;
        }

        .category_block li a {
            color: #343a40;
        }

        .add_to_cart_block .price {
            color: #c01508;
            text-align: center;
            font-weight: bold;
            font-size: 200%;
            margin-bottom: 0;
        }

        .add_to_cart_block .price_discounted {
            color: #343a40;
            text-align: center;
            text-decoration: line-through;
            font-size: 140%;
        }

        .product_rassurance {
            padding: 10px;
            margin-top: 15px;
            background: #ffffff;
            border: 1px solid #6c757d;
            color: #6c757d;
        }

        .product_rassurance .list-inline {
            margin-bottom: 0;
            text-transform: uppercase;
            text-align: center;
        }

        .product_rassurance .list-inline li:hover {
            color: #343a40;
        }

        .reviews_product .fa-star {
            color: gold;
        }

        .pagination {
            margin-top: 20px;
        }

        footer {
            background: #343a40;
            padding: 40px;
            margin-top: 20px;
        }

        footer a {
            color: #f8f9fa !important;
        }

        /*.bgc{*/
        /*    background-image: url(image/Clothes+and+shoes-74_banner.jpg);*/
        /*    !*background-image: url("https://envato-shoebox-0.imgix.net/a553/ba21-ce80-45ee-82d4-120907cdb414/Clothes+and+shoes-74_banner.jpg?auto=compress%2Cformat&fit=max&mark=https%3A%2F%2Felements-assets.envato.com%2Fstatic%2Fwatermark2.png&markalign=center%2Cmiddle&markalpha=18&w=1600&s=a9cc1545e602fe3d3e6c9faed39f0a84");*!*/
        /*}*/
        .show_txt {
            display: inline-block;
            width: 100%;
            white-space: nowrap;
            overflow: hidden !important;
            text-overflow: ellipsis;
        }

        a .active {
            color: white;
        }
    </style>
</head>

<body>
       <jsp:include page="menu.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/loadProduct">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Category</a></li>
                    <li class="breadcrumb-item active" aria-current="#">
                        Sub-category
                    </li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <div class="card bg-light mb-3">
                <div class="card-header bg-primary text-white text-uppercase">
                    <i class="fa fa-list"></i> Categories
                </div>
                <c:forEach items="${listCategory}" var="c">
                    <ul class="list-group category_block">
                        <li class="list-group-item text-white"><a href="/loadCategory?cateID=${c.cid}">${c.cname}</a></li>
                    </ul>
                </c:forEach>
            </div>
            <div class="card bg-light mb-3">
                <div class="card-header bg-warning text-white text-uppercase">
                    Last product
                </div>
                <div class="card-body">
                    <img class="img-fluid" src="${lastProduct.img}" />
                    <h5 class="card-title">${lastProduct.name}</h5>
                    <p class="card-text">${lastProduct.description}</p>
                    <p class="bloc_left_price">${lastProduct.price}$</p>
                </div>
            </div>
        </div>
<%--     Load product--%>

        <div class="col-sm-9">
            <div class="row">
                <c:forEach items="${listProduct}" var="o">
                <div class="col-12 col-md-6 col-lg-4" style="margin-bottom: 15px">
                    <div class="card">
                        <img
                                class="card-img-top"
                                src="${o.img}"
                                alt="Card image cap"
                        />
                        <div class="card-body">
                            <h4 class="card-title show_txt">
                                <a href="/detail?id=${o.id}" title="${o.title}">${o.name}</a>
                            </h4>
                            <p class="card-text show_txt">
                                ${o.description}
                            </p>
                            <div class="row">
                                <div class="col">
<%--                                    <p class="btn btn-danger btn-block">${o.price}$</p>--%>
                                      <form action="/byNowGioHang" method="post">
                                         <input name="id" type="hidden" value="${o.id}">
                                         <button class="btn btn-danger btn-block" style="margin-bottom: 10px">By now</button>
                                      </form>
                                </div>
                                <div class="col">
                                    <form action="/addGioHang" method="post">
                                        <input name="id" type="hidden" value="${o.id}">
                                        <button class="btn btn-success btn-block">Add to cart</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </c:forEach>

            </div>
        </div>
    </div>
</div>

<%--        <a href="" class="btn btn-info" style="margin-left: 53%; margin-top: 15px">Load more</a>--%>

<!-- Footer -->
     <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

