<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abeg
  Date: 2017/8/18
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <style type="text/css">
        html, body, div, span, applet, object, iframe,h1, h2, h3, h4, h5, h6, p, blockquote, pre,a, abbr, acronym, address, big, cite, code,del, dfn, em, font, img, ins, kbd, q, s, samp,small, strike, strong, sub, sup, tt, var,b, u, i, center,dl, dt, dd, ol, ul, li,fieldset, form, label, legend,table, caption, tbody, tfoot, thead, tr, th, td{margin:0; padding:0; border:0; outline: 0; font-size: 100%; font-size:12px; text-decoration:none; margin:0 auto; font-family:Verdana, Geneva, sans-serif;}
        a:link,a:visited,a:hover{color:#000;}
        a:hover{text-decoration: underline; color:#f40;}
        ol, ul { list-style: none;}
        body{ font-size:10px; color:#000; background:#eee;}
        .logo{height:75px; width:1100px; margin:auto; background: url(static/img/LOGO.png) no-repeat left; }
        .nav{ position:absolute; float:left; width:0; height:0;}
        .menu{ position: relative; top:44px; left:280px;width:535px;height:31px;background:url(static/img/navbg.gif) repeat-x;padding-left:7px;}
        .menu li{float:left;width:107px;height:27px;margin-top:4px;background:url(static/img/navline.gif) no-repeat right 3px;}
        .menu li span{display:block;width:98px;height:27px;margin-left:4px;text-align:center;line-height:27px;}
        .menu li span a,.menu .back a{color:#fff;text-decoration:none; display:block;}
        .menu li span a:hover,.menu .back a:hover{ text-decoration:underline; }
        .menu li.current span{background:url(static/img/tagbg.gif) no-repeat;}
        .menu li.current a{font-weight:bold;color:#00712E;}
        .header{ float:right; height:75px; width:180px;}
        .header ul li { float:left; padding-right:15px;}
        .header ul li a{ display:block; float:left; height:36px; line-height:36px;}
        .content {background:#fdfdfd; height:552px; width:990px; border:1px #999 solid; }
        .menu-content{ height:500px;}
        .menu-content .tr1{height:38px; background:#eee;}
        .menu-content .tr2{height:38px; background:#ccc;}
        .menu-content td{ width:245px; text-align:center;}
        .menu-content a{ display:inline-block; padding-left:5px;}
        .menu-content button{ text-align:center; line-height:18px;}
        .fafoot{background:#fff; width:100%; height:20px; border-bottom:2px solid #FF4400;}
        .foot-tab{ padding-left:290px;}
        .foot-tab ul li{ float:left; padding:5px;}
        .foot-tab ul li,.foot-tab ul span{ float:left; padding:5px;}
        .foot-tab-hover,.foot-tab-hover1,.foot-tab-hover2{display:block; float:left; cursor: pointer; height:23px; width:9px; padding:4px 10px; border:1px #fff solid; line-height:23px; text-align:center;}
        .foot-tab-active{display:block;line-height:23px; text-align:center; float:left; cursor: pointer; height:23px; width:9px; padding:4px 10px;background-color: #FFEDE1;border:1px  #F60 solid;  font-weight: bold;}
        .foot-tab ul li a:hover,.foot-tab ul span a:hover{ background:#f7f7f7; border: 1PX #C0C0C0 solid; text-decoration: none; }
        .footer{ height:100px; width:550px; margin:auto; text-align:center; padding-top:5px; line-height:20px;}
        .tips-button{  background:#737373; height:28px; line-height:28px; margin: auto;}
        .tips-button  a{display:inline-block; margin-left:45px; padding:0; cursor:pointer; text-align:center; width:104px;background: url(img/biaotianniou1.png) no-repeat;_background:none;
            _filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="static/img/biaotianniou1.png");}
        .tips-button  a:hover{ color:#000; font-weight: bolder; text-decoration: none; background: url(static/img/biaotianniou2.png) no-repeat;_background:none;
            _filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="static/img/biaotianniou2.png"); }
        .tips-button  a:active{ color:#fff; background: url(static/img/biaotianniou3.png) no-repeat; text-decoration:none; _background:none;
            _filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src="img/biaotianniou3.png");}
        .tr1 a{ display:inline-block; width:230px;}
        .function{position:absolute; display:none; top:76px;width:990px; height:552px; background:#DBEAF9;}
        .function2{position:absolute; display:none;top:76px;width:990px; height:552px; background:#DBEAF9;}
        .function td,.function2 td{ height:60px; text-align:center; background:#0C9; border-width:2px;border-style: solid; border-color: #DFDFDF #ADADAD #ADADAD #DFDFDF; }
        .function-head{height:18px; line-height:18px; width:990px; background:#666;color:#F40; font-weight:bolder; text-align:center;}
        .function-content{ width:746px;}
        .function-content-img{height:100px; margin:0px; padding-left:4px;}
        .function-content-img a{ position:relative;display: inline-block; float:left;border:1px #999 dashed; background:#fff;width:99px;height:98px; margin-right: 5px; }
        .function-content-img a p{position:absolute; display:inline-block; left:85px; width:14px; height:14px; background:url(img/PUSS.jpg) no-repeat;}
        .function-content-list {float:left; height:150px; line-height:150px; text-align:center; font-size:19px;}
        .textarea{height: 126px; width: 434px; margin-left: 55px; margin-top: 10px;}
        .function-foot{ position:relative;height:50px; width:990px;background: #666; text-align:center; }
        .function-btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
        .function-file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0); opacity: 0;width:260px }
        .function-foot button{ text-align:center; line-height:18px;}
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="static/js/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#five").click(function(){
                if($(this).attr("checked")){
                    $(".col").attr("checked","checked");
                }else{
                    $(".col").removeAttr("checked","checked");
                }
            })
            $("#container").click(function(){
                $(".function").fadeTo(1000,1);
            });
            $(".2").click(function(){
                $(".function2").fadeTo(1000,1);
            });
            $("p.3").click(function(){
                $(this).parent().remove();
            });
            $(".12").click(function() {
                $(".function").add(".function2").fadeOut();
            });
        });


        $(document).ready(function(){
            $(".foot-tab ul li a").each(function(){
                $(".foot-tab ul li a").click(function(){
                    $(this).addClass("foot-tab-active").css("color"," #F60")
                    $(this).removeClass("foot-tab-hover")
                    $(this).parent("li").siblings().children().addClass("foot-tab-hover").css("color","#000")
                    $(this).parent("li").siblings().children().removeClass("foot-tab-active")
                });
                $(".foot-tab-hover1").click(function(){
                    $(this).parent().next().children().addClass("foot-tab-active").css("color"," #F60")
                    $(this).parent().next().children().removeClass("foot-tab-hover")
                    $(this).parent().next().siblings().children().addClass("foot-tab-hover").css("color","#000")
                    $(this).parent().next().siblings().children().removeClass("foot-tab-active")
                });
                $(".foot-tab-hover2").click(function(){
                    $(this).parent().prev().children().addClass("foot-tab-active").css("color"," #F60")
                    $(this).parent().prev().children().removeClass("foot-tab-hover")
                    $(this).parent().prev().siblings().children().addClass("foot-tab-hover").css("color","#000")
                    $(this).parent().prev().siblings().children().removeClass("foot-tab-active")
                });
            });
        });
    </script>
    <title>商品管理</title>
</head>
<body>
<%@ include file="../comment/head.jsp" %>
<div style="border-top:1px #999 solid;"></div>
<div class="content"  style="border-top:none;">
    <div class="function">
        <div class="function-head">正在添加菜品</div>
        <form id="addgoods" method="post" enctype="multipart/form-data">
            <table width="750">
                <tr>
                    <td width="60">菜名</td>
                    <td><input name="productName" type="text" /></td>
                    <td width="60">单价</td>
                    <td ><input name="productPrice" type="text" /></td>
                </tr>
                <tr>
                    <td width="60">库存</td>
                    <td><input name="productStock" type="text" /></td>
                    <td width="60">是否上架</td>
                    <td >
                        <input name="productIsSell" type="radio" value="1" checked />是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="productIsSell" type="radio" value="0" />否
                    </td>
                </tr>
                <tr>
                    <td width="60">选择图片</td>
                    <td width="300">
                        <div class="function-box">
                            <form id="img_upload" action="" method="post" enctype="multipart/form-data">
                                <input type="file" name="file" id="fileField" />
                                <input type="button" name="submit" onclick="uploadimg()" class="function-btn" value="上传" />
                            </form>
                        </div>
                    </td>
                    <td width="60">口味</td>
                    <td>
                        <input type="text" id="add_goods_img_id" name="productPicture" value="" style="display: none">
                        <input name="productTaste" type="radio" value="0"checked />不辣&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="productTaste" type="radio" value="1" />辣
                    </td>
                </tr>
            </table>
            <div class="function-content">
                <div id="function-content-img" class="function-content-img">
                </div>
                <div style="height:150px; padding-left:70px;">
                    <div class="function-content-list">简介：</div>
                    <textarea name="productDescription" cols="" rows="" class="textarea"></textarea>
                </div>
            </div>
        </form>
        <div class=" function-foot">
            <input onclick="add_goods()" style="position:absolute;  top:10px; width:104px; left:300px;" value="确认添加" type="button"/>
            <button class="12" style="position:absolute;  top:10px; width:104px; left:650px;">取消操作</button></div>

    </div>
    <div class="function2">
        <form action="">
        <div class="function-head">菜品修改页面</div>
        <table width="750">
            <tr>
                <td width="60">菜名</td>
                <td><input name="" type="text" /></td>
                <td width="60">单价</td>
                <td ><input name="" type="text" /></td>
            </tr>
            <tr>
                <td width="60">库存</td>
                <td><input name="" type="text" /></td>
                <td width="60">是否上架</td>
                <td >
                    <input name="up" type="radio" value="1"checked />是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="up" type="radio" value="0" />否
                </td>
            </tr>
            <tr>
                <td width="60">选择图片</td>
                <td width="300">
                    <div class="function-box">
                        <form action="" method="post" enctype="multipart/form-data">
                            <input type="file" name="fileField" id="fileField" size="28"/>
                            <input type="button" name="submit" class="function-btn" value="上传" />
                        </form>
                    </div>
                </td>
                <td width="60">口味</td>
                <td>
                    <input name="SELL" type="radio" value=""checked />不辣&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="SELL" type="radio" value="" />辣
                </td>
            </tr>
        </table>
        <div class="function-content">
            <div class="function-content-img">
                <a><p class="3"></p><img  alt="图片1"/></a>
            </div>
            <div style="height:150px; padding-left:70px;">
                <div class="function-content-list">简介：</div>
                <textarea name="TEXT" cols="" rows="" class="textarea"></textarea>
            </div>
        </div>
        <div class=" function-foot">
            <button class="1" style="position:absolute;  top:10px; width:104px; left:300px;">确认修改</button>
            <button class="1" style="position:absolute;  top:10px; width:104px; left:650px;">取消操作</button>
        </div>
        </form>
    </div>

    <div class="menu-content">
        <table>
            <tr>
                <td>商品名字</td>
                <td>价格</td>
                <td>交易状态</td>
                <td>交易操作</td>
            </tr>
        </table>
        <div  class="tips-button">
            <input type="checkbox" id="five"  style="margin:0 30px;" />
            <label for="five" style=" height:28px; line-height:28px; ">全选</label>
            <a onclick="getCheck(0)">下架</a>
            <a onclick="getCheck(1)">上架</a>
            <a onclick="getCheck(2)">删除</a>
        </div>
        <table>
            <c:forEach items="${pageResult.objects}" var="list">
                <tr class="tr1" >
                    <td style="width:70px;"><input type="checkbox" name="check_id" value="${list.productId}" id="xuanze" class="col"/>
                    </td>
                    <td><a href="goods?id=${list.productId}">${list.productName}</a></td>
                    <td>￥<em>${list.productPrice}</em></td>
                    <c:if test="${list.productIsSell==0}">
                    <td>已下架</td>
                    <td><button><a href="${pageContext.request.contextPath}/updateSellStatus?id=${list.productId}&status=1">上架</a></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="updateGoods(${list.productId})" >修改商品</button></td>
                    </c:if>
                    <c:if test="${list.productIsSell==1}">
                        <td>已上架</td>
                        <td><button><a href="${pageContext.request.contextPath}/updateSellStatus?id=${list.productId}&status=0">下架</a></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="updateGoods(${list.productId})" >修改商品</button></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <table>
            <tr  class="tr2" style="background:#dbeaf9;">
                <td style="width:990px;"> <button id="container" style="height:60px; width:200px; border:2px #666 dashed;">添加产品</button></td>
            </tr>
        </table>
    </div>
    <div style="width:990px;">
        <div class="foot-tab">
            <ul>
                <span><a  class="foot-tab-hover1" href="insertGoods?page=1&row=10" style="width:57px;">首页</a></span>
                <c:if test="${pageResult.pages==1}">
                    <c:if test="${pageResult.curr==1}">
                        <li><a  class="foot-tab-active" style="color: #F60;" href="#">1</a></li>

                    </c:if>
                </c:if>
                <c:if test="${pageResult.pages>1}">
                    <c:if test="${pageResult.curr==1}">
                        <li><a  class="foot-tab-active" style="color: #F60;" href="#">1</a></li>
                        <li><a  class="foot-tab-hover" href="insertGoods?page=${pageResult.curr+1}&row=10">2</a></li>
                    </c:if>
                    <c:if test="${pageResult.curr+1<=pageResult.pages &&pageResult.curr>1}">
                        <li><a  class="foot-tab-hover" href="insertGoods?page=${pageResult.curr-1}&row=10">${pageResult.curr-1}</a></li>
                        <li><a  class="foot-tab-active" style="color: #F60;" href="#">${pageResult.curr}</a></li>
                        <li><a  class="foot-tab-hover" href="insertGoods?page=${pageResult.curr+1}&row=10">${pageResult.curr+1}</a></li>
                    </c:if>
                    <c:if test="${pageResult.curr==pageResult.pages}">
                        <li><a  class="foot-tab-hover" href="insertGoods?page=${pageResult.curr-1}&row=10">${pageResult.curr-1}</a></li>
                        <li><a  class="foot-tab-active" style="color: #F60;" href="#">${pageResult.curr}</a></li>
                    </c:if>
                </c:if>

                <span><a  class="foot-tab-hover2" href="#" style="width:57px;">尾页</a></span>
            </ul>
            <form action="insertGoods" method="get">
                <input name="" type="text"  style=" margin:13px;width:23px;"  onkeyup="this.value=this.value.replace(/\D/g,'')" value="1"/>
                <input  style="padding:0;" name="提交"  type="submit"  value="跳转" />
            </form>
        </div>
    </div>
</div>
<div class="fa-foot"></div>
<div class="footer">
    <p>项目中监制：吴明华</p>
    <p>项目制作人：赖叶蕾丨周光利丨李思渊丨胡斌</p>
    <p>技术支持：web前端-丁伟&nbsp;&nbsp;JAVA:xxx</p>
    <p>实习项目：百航点餐系统</p>
</div>
</body>
<script type="text/javascript">
    //定义一个全局变量 用来保存图片上传的次数
    var imgcount=0;
    function uploadimg(){
            var formData = new FormData();
            var files = $("#fileField").get(0).files[0]; //获取file控件中的内容
            formData.append("file", files);
            formData.append("count",imgcount);
            $.ajax({
                url: '${pageContext.request.contextPath}/uploadMulit' ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (date) {
                    if(date.status==200)
                    {
                        imgcount++;
                        //成功之后照片显示 并且把路径回显到隐藏的input中
                        $("#add_goods_img_id").attr("value",date.data);
                        $("#fileField").attr("value","");
                        $("#function-content-img").append("<a id=\"upload_"+date.msg+"\"><p class=\"3\"></p><img src=\""+date.data+"\" style=\"width: 100px;height: 100px;z-index: 1;position:absolute;\" alt=\"图片\"/><img style=\"position:absolute;left:86px;z-index:999;float: right\" src=\"static/img/PUSS.jpg\" onclick=\"delimg("+date.msg+")\"/></a>")
                    }else{
                        alert(date.msg);
                    }
                },
                error: function (returndata) {
                    alert(returndata);
                }
            });
    }
    function  delimg(number) {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/delimgurl',
            data: {index_imgurl:number},
            traditional: true,
            success: function (date) {
                if(date.status==200)
                {
                    alert(date.msg);
                    $("#upload_"+number).remove();

                }else{
                    alert(date.msg);
                }
            },
        });

    }
    function getCheck(status) {
        var xuanze_id=document.getElementsByClassName("xuanze_id");
        //获取已选择的商品
        var check_array=new Array();
        var j=0;
        $('input[name="check_id"]:checked').each(function(){
            check_array[j]=$(this).val();//向数组中添加元素
            j++
        });
        if(check_array.length==0)
        {
            alert("请您选择商品");
        }else {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/updatelittleGoods',
                data: {ids:check_array,status:status},
                traditional: true,
                success: function (date) {
                    if(date.status==200)
                    {
                        alert(date.msg);
                        window.location.href=date.data;
                    }else{
                        alert(date.msg);
                    }
                },
            });
        }

    }

    function updateGoods(id) {
       // $(".function2").fadeTo(1000,1);
        window.location.href="updategoods?id="+id;
    }

    function add_goods() {
        //添加商品发出ajax请求
        var reg_number = /^[1-9]\d*$/;
        var reg_price = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        var param=document.getElementById("addgoods");
        if(param.productName.value=="")
        {
            alert("名字不能为空");
        }else if(!reg_price.test(param.productPrice.value) || param.productName.value==""){
            alert("请填写正确的价格")
        }else if(param.productStock.value=="" || !reg_number.test(param.productStock.value))
        {
            alert("请输入正确库存");
        }else {

            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/insertGoods',
                data: {productName:param.productName.value,productPrice:param.productPrice.value,
                    productStock:param.productStock.value,productIsSell:param.productIsSell.value,
                    productTaste:param.productTaste.value,
                    productPicture:param.productPicture.value,
                    productDescription:param.productDescription.value},
                success: function (date) {
                    if(date.status==200)
                    {
                        alert(date.msg);
                        window.location.href="adminimenu";
                    }else{
                        alert(date.msg);
                    }
                },
            });
        }
    }
</script>
</html>

