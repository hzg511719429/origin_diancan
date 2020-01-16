<%--
  Created by IntelliJ IDEA.
  User: abeg
  Date: 2017/8/22
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="logo">
    <div class=" nav">
        <div class=" menu">
            <ul >
                <li class="current" ><span><a href="index.html" >主页</a></span></li>
                <li ><span><a>精品店铺</a></span></li>
                <li ><span><a>咖啡饮品</a></span></li>
                <li ><span><a>甜品蛋糕</a></span></li>
                <li><span><a>宴会直通车</a></span></li>
            </ul>
        </div>
    </div>
    <div class="header">
        <div style="border-bottom:2px #4e4e4e solid; height:36px;">
            <ul>
                <c:if test="${sessionScope.user==null}">
                    <li><a href="login.html">登陆</a></li>
                    <li><a href="regist.html">注册</a></li>
                </c:if>
                <c:if test="${sessionScope.user!=null}">
                    <c:if test="${sessionScope.user.userRol==0}">
                        <li><a href="menu">我的订单</a></li>
                        <li><a href="car">餐车</a></li>
                    </c:if>
                    <c:if test="${sessionScope.user.userRol==1}">
                        <li><a href="sellOrderList">我的订单</a></li>
                        <li><a href="adminimenu.html">我的商品</a></li>
                    </c:if>
                </c:if>
            </ul>
        </div>
        <div style="margin:auto; width:100px; height:36px; line-height:36px;">
            <c:if test="${sessionScope.user!=null}">
                <a href="updateUser">${sessionScope.user.userNickname}</a>
                <a style="margin-left:10px;" href="loginOut">退出</a>
            </c:if>
        </div>
    </div>
</div>
