<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
	 <!--jquery easyUI 的主文件-->
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<!--jquery easyUI 的主样式文件..-->
	<link type="text/css" rel="stylesheet" href="js/themes/default/easyui.css" >
	<!--jquery easyUI 的图标文件..-->
	<link type="text/css" rel="stylesheet" href="js/themes/icon.css" >
	<!--国际化文件-->
	<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
	<!-- 样式 -->
	<style>
        #fpsq td{border:2px dashed #dfdfdf;}
        #fpsq{border : 0px}
        #fpsq{ font-weight: bold}
	</style>
	
	
	
	<script type="text/javascript">
	//这个方法是为了按backspace不会跳回到上个页面
		//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
      function banBackSpace(e){   
	          var ev = e || window.event;//获取event对象   
	          var obj = ev.target || ev.srcElement;//获取事件源   
	          var t = obj.type || obj.getAttribute('type');//获取事件源类型  
	          //获取作为判断条件的事件类型
	          var vReadOnly = obj.getAttribute('readonly');
	          //处理null值情况
	          vReadOnly = (vReadOnly == "") ? false : vReadOnly;
	         var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
	                     && vReadOnly=="readonly")?true:false;
	         //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	         var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
	                     ?true:false;        
	         
	         if(flag2){
	             return false;
	         }
	         if(flag1){   
	             return false;   
	         }   
	     }
 
		 window.onload=function(){
		     document.onkeypress=banBackSpace;
		     document.onkeydown=banBackSpace;
		 };
	
	
		
		var biao = "";
		var biaoname="";
		
		 window.jQuery(document).ready(function(){
			//$('#tijiaoresult').window('close');  //先把提示框关掉
			 $("#caozuo").tree({
			 		url:"getCaozuo.action",
			 		onClick:function(node){
						var tabs=$("#center_tab").tabs("getTab",node.text);
						if(tabs==null){
						if(node.text=="往期申请"){
						
							biao='wqbiao.jsp';
							biaoname='wqbiao';
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:biao,  
							    fit:true,
							    closable:true,
							    onLoad:function(){
							 	$("#"+biaoname).datagrid({
						 	//从服务端获取数据...
						 	   	url:'${pageContext.request.contextPath}/userwqfpsq.action?userNO=${sessionScope.user.userNo}',
								fit:true,
								method:"POST",
								pagination:true,
								/*columns:[[    
							        {field:'id',title:'编号',width:100},    
							        {field:'userId',title:'用户id',width:100},    
							        {field:'name',title:'申请名称',width:100},
									{field:'position',title:'申请人身份',width:100},
									{field:'money',title:'金额',width:100},
									{field:'date',title:'申请时间',width:100},
									{field:'use',title:'用途',width:100},
									{field:'auditNo',title:'审核到第几级',width:100},
									{field:'whypass',title:'是否通过',width:100},
									{field:'opt',title:'操作',width:50,align:'center',  
							          formatter:formatOper
							        }    
							    ]],*/
								
						
				 				});
							 }
						});
						}else if(node.text=="正在审批")
						{
							biao='spbiao.jsp';
							biaoname='zzsq';
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:biao,  
							 	fit:true,
							    fitColumns:true,
							    closable:true,
							    onLoad:function(){
							 	$("#"+biaoname).datagrid({
						 	//从服务端获取数据...
						 	   	url:'${pageContext.request.contextPath}/userzzfpsq.action?userNO=${sessionScope.user.userNo}',
								fit:true,
								pagination:true,
								method:"POST",
								columns:[[    
							        {field:'id',title:'编号',width:100},    
							        {field:'userId',title:'用户id',width:100},    
							        {field:'name',title:'申请名称',width:100},
									{field:'xmname',title:'项目名称',width:100},
									{field:'money',title:'金额',width:100},
									{field:'sqdata',title:'申请时间',width:100},
									{field:'use',title:'用途',width:100},
									{field:'auditNo',title:'审核到第几级',width:100},
									{field:'auditcount',title:'需要几级审核',width:100},
									{field:'opt',title:'操作',width:50,align:'center',  
							          formatter:formatOper
							        }    
							    ]],
				 				});
							 }
						});
						}else if(node.text=="报账申请"){
							$.get('${pageContext.request.contextPath }/getfpcountid.action');
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:'e_fpsq.jsp',  
							    fit:true,
							    closable:true
							    });
							    $.getJSON('${pageContext.request.contextPath }/getxm.action', function(data) {
							    if(data=="")
							    {
							        	alert("您没有项目可以申请");
							        	$('#center_tab').tabs('close',"报账申请");
							     }else{
									        $("#e_fpsq_xmid").html("");//清空info内容
									        $.each(data, function(i, item) {
										        
											         $("#e_fpsq_xmid").append(
											                  	"<option value="+item.id+">"+item.xmname+"</option>"
																);
												
										     });
						        }
					        })
						}else if(node.text=="用户管理"){
							biao='usergl.jsp';
							biaoname='usergl';
							$('#center_tab').tabs('add',{    
								    title:node.text,    
								    href:"usergl.jsp",  
								    fit:true,
								    closable:true,
								    onLoad:function(){
								 	$("#usergl").datagrid({
							 	//从服务端获取数据...
							 	   	url:'${pageContext.request.contextPath }/getUserList.action',
									fit:true,
									pagination:true,
									method:"POST",
									columns:[[    
								        {field:'id',title:'编号',width:100},    
								        {field:'userNo',title:'用户编号',width:100},    
								        {field:'userName',title:'姓名',width:100},
								        {field:'identity',title:'申请人身份',width:100},
										{field:'userPhone',title:'手机号',width:100},
										{field:'useremail',title:'邮箱地址',width:100},
										{field:'positionName',title:'权限等级',width:100},
										{field:'departmentsName',title:'院系',width:100},
										{field:'one',title:'修改',width:50,align:'center',  
								          formatter:userupdate
								        },
								        {field:'two',title:'删除',width:50,align:'center',  
								          formatter:userdel
								        }    
								    ]],
									
							
					 				});
								 }
							});
						
						}
						else if(node.text=="角色管理"){
							biao='jsgl.jsp';
							biaoname='jsgl';
							$('#center_tab').tabs('add',{    
								    title:node.text,    
								    href:biao,  
								    fit:true,
								    closable:true,
								    onLoad:function(){
								 	$("#jsgl").datagrid({
							 	//从服务端获取数据...
							 	   	url:'${pageContext.request.contextPath }/getallcaozuo.action',
									fit:true,
									pagination:true,
									method:"POST",
									columns:[[    
								        {field:'id',title:'编号',width:100},    
								        {field:'name',title:'权限名',width:100},    
								        {field:'user',title:'一般用户',width:100},
								        {field:'sp',title:'审批人员',width:100},
										{field:'admin',title:'超级管理员',width:100},
										{field:'one',title:'修改',width:50,align:'center',  
								          formatter:jsxg
								        },
								    ]],
									
							
					 				});
								 }
							});
						
						}else if(node.text=="退出系统"){
							window.location.href='${pageContext.request.contextPath }/logout.action'; 
						}else if(node.text=="待审批"){
							biao='dsp.jsp';
							biaoname='dsp';
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:biao,  
							    fit:true,
							    closable:true,
							    onLoad:function(){
							 	$("#"+biaoname).datagrid({
						 	//从服务端获取数据...
						 	   	url:'${pageContext.request.contextPath }/adminfpsq.action?userNo=${sessionScope.user.userNo}',
								fit:true,
								pagination:true,
								method:"POST",
								columns:[[    
							        {field:'id',title:'编号',width:100},    
							        {field:'userId',title:'用户id',width:100},    
							        {field:'name',title:'申请名称',width:100},
							        {field:'xmname',title:'项目名称',width:100},
									{field:'identity',title:'申请人身份',width:100},
									{field:'count',title:'发票总数',width:100},
									{field:'money',title:'金额',width:100},
									{field:'sqdata',title:'申请时间',width:100},
									{field:'use',title:'用途',width:100},
									{field:'opt',title:'操作',width:50,align:'center',  
							          formatter:dspOpen
							        }    
							    ]],
								
						
				 				});
							 }
						});
						}else if(node.text=="申请列表"){
							biao='fpsqlist.jsp';
							biaoname='fpsqlist';
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:biao,  
							    fit:true,
							    fitColumns:true,
							    closable:true,
							    onLoad:function(){
							 	$("#"+biaoname).datagrid({
							 	pagination:true,
						 	//从服务端获取数据... 因为根据条件出数据，所以先加载表然后加载数据
								columns:[[    
							        {field:'id',title:'编号',width:100},    
							        {field:'userId',title:'用户id',width:100},    
							        {field:'name',title:'支出事项',width:100},
									{field:'position',title:'申请人身份',width:100},
									{field:'count',title:'发票总数',width:100},
									{field:'money',title:'金额',width:100},
									{field:'date',title:'申请时间',width:100},
									{field:'use',title:'预算科目',width:100},
									{field:'delName',title:'审核状态',width:100},
									{field:'opt',title:'操作',width:50,align:'center',  
							          formatter:formatOper
							        }    
							    ]]
							   
				 				});
							 }
						});
						}
						else if(node.text=="历史发票申请管理"){
							biao='fpsqlist.jsp';
							biaoname='fpsqlist';
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:biao,  
							    fit:true,
							    fitColumns:true,
							    closable:true,
							    onLoad:function(){
							 	$("#"+biaoname).datagrid({
							 	pagination:true,
						 	//从服务端获取数据... 因为根据条件出数据，所以先加载表然后加载数据
								columns:[[    
							        {field:'id',title:'编号',width:100},    
							        {field:'userId',title:'用户id',width:100},    
							        {field:'name',title:'支出事项',width:100},
									{field:'position',title:'申请人身份',width:100},
									{field:'count',title:'发票总数',width:100},
									{field:'money',title:'金额',width:100},
									{field:'date',title:'申请时间',width:100},
									{field:'use',title:'预算科目',width:100},
									{field:'delName',title:'审核状态',width:100},
									{field:'one',title:'操作',width:50,align:'center',  
							          formatter:formatOper
							        },
							        {field:'two',title:'删除',width:50,align:'center',  
							          formatter:delfpsq
							        }    
							    ]]
							   
				 				});
							 }
						});
						}
						else if(node.text=="项目录入"){
							biao='xm.jsp';
							biaoname='xm';
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:biao,  
							    fit:true,
							    fitColumns:true,
							    closable:true,
							    onLoad:function(){
								 	$("#"+biaoname).datagrid({
									 	url:'${pageContext.request.contextPath }/getxmlist.action',
									 	pagination:true,
								 	//从服务端获取数据... 因为根据条件出数据，所以先加载表然后加载数据
										columns:[[    
									        {field:'id',title:'编号',width:100},    
									        {field:'userid',title:'用户id',width:100},    
									        {field:'xmname',title:'项目名称',width:100},
									        {field:'money',title:'金额',width:100},    
									        {field:'shengyu',title:'剩余',width:100},
											{field:'one',title:'修改',width:50,align:'center',  
									          formatter:eduitxm
									        },
									        {field:'two',title:'删除',width:50,align:'center',  
									          formatter:delxm
									        } 
									    ]]
						 				});
								 }
							});
						}else if(node.text=="流程管理"){
							$.get('${pageContext.request.contextPath }/getposition.action');
							if('${sessionScope.positionlist !=null}')
							{
								$('#center_tab').tabs('add',{    
								    title:node.text,    
								    href:'position.jsp',  
								    fit:true,
								    closable:true
								    });
							};
						}else if(node.text=="查看项目"){
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:'ckxm.jsp',  
							    fit:true,
							    fitColumns:true,
							    closable:true,
							    onLoad:function(){
								 $("#ckxm").datagrid({
									 	url:'${pageContext.request.contextPath }/getckxm.action',
									 	pagination:true,
								 		//从服务端获取数据... 因为根据条件出数据，所以先加载表然后加载数据
										columns:[[    
									        {field:'id',title:'编号',width:100},    
									        {field:'userid',title:'用户id',width:100},    
									        {field:'xmname',title:'项目名称',width:100},
									        {field:'money',title:'金额',width:100},    
									        {field:'shengyu',title:'剩余',width:100}
									    ]]
						 				});
								 }
							});
						}else if(node.text=="院系管理"){
							$('#center_tab').tabs('add',{    
							    title:node.text,    
							    href:'departments.jsp',  
							    fit:true,
							    fitColumns:true,
							    closable:true,
							    onLoad:function(){
								 $("#department").datagrid({
									 	url:'${pageContext.request.contextPath }/getdepartmentlist.action',
									 	pagination:true,
								 		//从服务端获取数据... 因为根据条件出数据，所以先加载表然后加载数据
										columns:[[    
									        {field:'id',title:'编号',width:100},    
									        {field:'departmentsName',title:'院系名称',width:100},    
									        {field:'departmentsNo',title:'院系编号',width:100},
									        {field:'addpartment',title:'修改',width:50,align:'center',  
									          formatter:eduitdepartment
									        },
									        {field:'deldte',title:'删除',width:50,align:'center',  
									          formatter:deldepartment
									        } 
									    ]]
						 				});
								 }
							});
						}
						}else{
							//alert(node.text);
							$('#center_tab').tabs("select",node.text);
							if(node.text=="往期申请"){
								biao='wqbiao.jsp';
								biaoname='wqbiao';
							}
							else if(node.text=="正在审批"){
								biao='spbiao.jsp';
								biaoname='zzsq';
							}
							else if(node.text=="待审批"){
								biao='dsp.jsp';
								biaoname='dsp';
							}
							else if(node.text=="申请列表"){
								biao='fpsqlist.jsp';
								biaoname='fpsqlist';
							}
							else if(node.text=="历史发票申请管理"){
								biao='fpsqlist.jsp';
								biaoname='fpsqlist';
							}else if(node.text=="用户管理"){
								biao='usergl.jsp';
								biaoname='usergl';
							}
							else if(node.text=="项目录入"){
								biao='xm.jsp';
								biaoname='xm';
							}
							else if(node.text=="项目录入"){
								biao='jsgl.jsp';
								biaoname='jsgl';
							}
						}
					}
				});
		 });
		 //院系修改
		 function eduitdepartment(val,row,index,biaoname)
		 {
		 	return '<a href="javascript:void(0)" onclick="eduitdepartments('+index+')">修改</a>';
		 }
		 function eduitdepartments(index){
		 	$('#department').datagrid('selectRow',index);// 关键在这里  
			var row = $('#department').datagrid('getSelected'); 
			if(row)
			{
				$("#departments").css("display","block");
				$('#departments').dialog({    
						    title: '修改院系信息',    
						    width: 400,    
						    height: 500,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
				//访问url但是不跳转 只是为了把查询出来的值保存到session域中 但是el表达式只能一次取值，所以还是用ajax取值
				
				        $("#departments_table").html("");//清空info内容
				            $("#departments_table").append(
				            		"<tr style=\"display:none\">"+
										"<td>id</td>"+
									"<td><input name=\"id\" value="+row.id+"></td>"+
									"</tr>"+
				                 	 "<tr>"+
										"<td>院系名称</td>"+
									"<td><input name=\"userid\" value="+row.departmentsName+"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>院系编码</td>"+
										"<td><input name=\"xmname\" value="+row.departmentsNo +"></td>"+
									"</tr>"+
									"<tr>"+
	            						"<td colspan=\"2\" style=\"text-align: center;\"><input type=\"submit\" value=\"修改\"/></td>"+
	       							"</tr>"
									);
								}
		 }
		 //院系删除
		  function deldepartment(val,row,index,biaoname)
		 {
		 	return '<a href="javascript:void(0)" onclick="deletepartments('+index+')">删除</a>';
		 }
		 function deletepartments(index){
		 	$('#department').datagrid('selectRow',index);// 关键在这里  
			var row = $('#department').datagrid('getSelected'); 
			if(row)
			{
				$.ajax({
					        type: "POST",
					        url: '${pageContext.request.contextPath}/delDepartment.action',
					        data: {id:row.id},
					        success: function (data) {
					        	if(data.status==200)
					        	{
					        		alert(data.text);
					            	$('#fpsqlist').datagrid('reload');
					            }
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
			                        alert("删除失败");
			                }
					    });
			}
		 }
		 //历史发票申请管理删除按钮
		 function delfpsq(val,row,index,biaoname)
		 {
		 	return '<a href="javascript:void(0)" onclick="dfpsq('+index+')">删除</a>';
		 }
		 function dfpsq(index){
		 	$('#'+biaoname).datagrid('selectRow',index);// 关键在这里  
			var row = $('#'+biaoname).datagrid('getSelected'); 
			if(row)
			{
				$.ajax({
					        type: "POST",
					        url: '${pageContext.request.contextPath}/delfcount.action',
					        data: {id:row.id},
					        success: function (data) {
					        	alert(data.text);
					            $('#fpsqlist').datagrid('reload');
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
			                        alert("删除失败");
			                }
					    });
			}
		 }
		 //角色权限管理
		 function jsxg(val,row,index,biaoname)
		 {
		 	return '<a href="javascript:void(0)" onclick="jsgl('+index+')">编辑</a>';
		 }
		 function jsgl(index)
		 {
		 	$('#'+biaoname).datagrid('selectRow',index);// 关键在这里  
			var row = $('#'+biaoname).datagrid('getSelected'); 
			if(row)
			{
				$("#jsxg").css("display","block");
				$('#jsxg').dialog({    
						    title: '角色管理',    
						    width: 400,    
						    height: 500,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
				        $("#jsxg_table").html("");//清空info内容
				            $("#jsxg_table").append(
				            		"<tr style=\"display:none\">"+
										"<td>id</td>"+
									"<td><input name=\"id\" value="+row.id+"></td>"+
									"</tr>"+
				                 	 "<tr>"+
										"<td>权限名称</td>"+
									"<td><input name=\"name\" disabled=\"true\" value="+row.name+"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>一般人员</td>"+
										"<td><input name=\"user\" value="+row.user +"></td>"+
									"</tr>"+
									 "<tr>"+
										"<td>审批人员</td>"+
										"<td><input name=\"sp\" value="+row.sp +"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>管理员</td>"+
										"<td><input name=\"admin\" value="+row.admin +"></td>"+
									"</tr>"+
									"<tr>"+
	            						"<td colspan=\"2\" style=\"text-align: center;\"><input type=\"submit\" value=\"修改\"/></td>"+
	       							"</tr>"
									);
								}
		 }
		//项目录入
		function eduitxm(val,row,index,biaoname)
		{
			return '<a href="javascript:void(0)" onclick="exm('+index+')">编辑</a>';
		}
		function exm(index)
		{
			$('#'+biaoname).datagrid('selectRow',index);// 关键在这里  
			var row = $('#'+biaoname).datagrid('getSelected'); 
			if(row)
			{
				$("#uxm").css("display","block");
				$('#uxm').dialog({    
						    title: '发票信息',    
						    width: 400,    
						    height: 500,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
				//访问url但是不跳转 只是为了把查询出来的值保存到session域中 但是el表达式只能一次取值，所以还是用ajax取值
				
				        $("#uxm_table").html("");//清空info内容
				            $("#uxm_table").append(
				            		"<tr style=\"display:none\">"+
										"<td>id</td>"+
									"<td><input name=\"id\" value="+row.id+"></td>"+
									"</tr>"+
				                 	 "<tr>"+
										"<td>用户学号</td>"+
									"<td><input name=\"userid\" value="+row.userid+"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>项目名称</td>"+
										"<td><input name=\"xmname\" value="+row.xmname +"></td>"+
									"</tr>"+
									 "<tr>"+
										"<td>审批金额</td>"+
										"<td><input name=\"money\" value="+row.money +"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>剩余</td>"+
										"<td><input name=\"shengyu\" value="+row.shengyu +"></td>"+
									"</tr>"+
									"<tr>"+
	            						"<td colspan=\"2\" style=\"text-align: center;\"><input type=\"submit\" value=\"修改\"/></td>"+
	       							"</tr>"
									);
								}
		}
		//项目删除
		function delxm(val,row,index,biaoname)
		{
			return '<a href="javascript:void(0)" onclick="dxm('+index+')">删除</a>';
		}
		function dxm(index)
		{
			$('#'+biaoname).datagrid('selectRow',index);// 关键在这里  
			var row = $('#'+biaoname).datagrid('getSelected'); 
			if(row)
			{
				$.ajax({
					        type: "POST",
					        url: '${pageContext.request.contextPath}/delxm.action',
					        data: {id:row.id},
					        success: function (data) {
					        	alert("删除成功");
					            $('#usergl').datagrid('reload');
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
			                        alert("删除失败");
			                }
					    });
			}
		}
		//用户修改
		function userupdate(val,row,index,biaoname)
		{
			return '<a href="javascript:void(0)" onclick="uuser('+index+')">编辑</a>';
		}
		function uuser(index)
		{
			$('#'+biaoname).datagrid('selectRow',index);// 关键在这里  
			var row = $('#'+biaoname).datagrid('getSelected'); 
			if(row)
			{
				$("#userxg").css("display","block");
				$('#userxg').dialog({    
						    title: '发票信息',    
						    width: 400,    
						    height: 500,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
				//访问url但是不跳转 只是为了把查询出来的值保存到session域中 但是el表达式只能一次取值，所以还是用ajax取值
				//$.get('${pageContext.request.contextPath }/adminfpsqinfo.action?id='+row.id);
					$.getJSON('${pageContext.request.contextPath }/updateuser.action?userNo='+row.userNo, function(item) {
				        $("#userxg_table").html("");//清空info内容
				            $("#userxg_table").append(
				            		"<tr style=\"display:none\">"+
										"<td>id</td>"+
									"<td><input name=\"id\" value="+item.id+"></td>"+
									"</tr>"+
				                 	 "<tr>"+
										"<td>用户学号</td>"+
									"<td><input name=\"userNo\" value="+item.userNo+"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>用户姓名</td>"+
										"<td><input name=\"userName\" value="+item.userName +"></td>"+
									"</tr>"+
									 "<tr>"+
										"<td>身份</td>"+
										"<td><input name=\"identity\" value="+item.identity +"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>手机号</td>"+
										"<td><input name=\"userPhone\" value="+item.userPhone +"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>邮箱</td>"+
										"<td><input name=\"useremail\" value="+item.useremail +"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>权限等级</td>"+
										"<td><select id=\"uuser_positionNo\" name=\"positionNo\"></select></td>"+
									"</tr>"+
									"<tr>"+
										"<td>院系</td>"+
										"<td><select id=\"uuser_departments\" name=\"departmentsNo\"></select></td>"+
									"</tr>"
									);
							$("#userxg_table").append(
								"<tr>"+
            						"<td colspan=\"2\" style=\"text-align: center;\"><input type=\"submit\" value=\"修改\"/></td>"+
       							"</tr>"
							);
						//权限等级动态添加option
						$.getJSON('${pageContext.request.contextPath }/getdepartments.action', function(data) {
				        $("#uuser_departments").html("");//清空info内容
				        $.each(data, function(i, one) {
				        	
				        			if(one.departmentsNo==item.departmentsNo)
				        			{
				        				$("#uuser_departments").append(
				        					"<option selected=\"selected\" value="+one.departmentsNo+" >"+one.departmentsName+"</option>"
				        				);
				        			}else{
				        				$("#uuser_departments").append(
				        					"<option value="+one.departmentsNo+" >"+one.departmentsName+"</option>"
				        				);
				        			}
				        		
				       		 });
				        });
				        //院系动态添加option
						$.getJSON('${pageContext.request.contextPath }/getpositionlist.action', function(data) {
				        $("#uuser_positionNo").html("");//清空info内容
				        $.each(data, function(i, one) {
				        	
				        			if(one.positionNo==item.positionNo)
				        			{
				        				$("#uuser_positionNo").append(
				        					"<option selected=\"selected\" value="+one.positionNo+" >"+one.positionName+"</option>"
				        				);
				        			}else{
				        				$("#uuser_positionNo").append(
				        					"<option value="+one.positionNo+" >"+one.positionName+"</option>"
				        				);
				        			}
				        		
				       		 });
				        });
				});
			}
		}
		//用户删除
		 function userdel(val,row,index,biaoname)
		{
			return '<a href="javascript:void(0)" onclick="duser('+index+')">删除</a>';
		}
		function duser(index){
			$('#'+biaoname).datagrid('selectRow',index);// 关键在这里  
			var row = $('#'+biaoname).datagrid('getSelected'); 
			if(row)
			{
				$.ajax({
					        type: "POST",
					        url: '${pageContext.request.contextPath}/deluser.action',
					        data: {id:row.id},
					        success: function (data) {
					        	alert("删除成功");
					            $('#usergl').datagrid('reload');
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
			                        alert("删除失败");
			                }
					    });
			}
		}
		//待审批查询，这里要新加一个tabs，只用来展示表
		function dspOpen(val,row,index,biaoname){  
		 	
		    return '<a href="javascript:void(0)" onclick="dspaddtabs('+index+')">审批</a>';  
			}  
		function dspaddtabs(index){ 
			$('#'+biaoname).datagrid('selectRow',index);// 关键在这里  
			var row = $('#'+biaoname).datagrid('getSelected'); 
			if(row)
			{
				$("#info_sp").css("display","block");
				$('#info_sp').dialog({    
						    title: '发票信息',    
						    width: 800,    
						    height: 800,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
				//访问url但是不跳转 只是为了把查询出来的值保存到session域中 但是el表达式只能一次取值，所以还是用ajax取值
				//$.get('${pageContext.request.contextPath }/adminfpsqinfo.action?id='+row.id);
					$.getJSON('${pageContext.request.contextPath }/getfpzblist.action?fpid='+row.fpid, function(data) {
				        $("#sp_table").html("");//清空info内容
				        $("#sp_table").append(
				            		"<tr style=\"display:none\">"+
										"<td><input name=\"fpid\" value="+row.fpid+"></td>"+
									"</tr>"
									);
				        $.each(data, function(i, item) {
				            $("#sp_table").append(
				                  	"<tr>"+
										"<td>支出项目</td>"+
										"<td><input value="+item.fapiaoname+" readOnly=\"true\"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>发票编号</td>"+
										"<td><input value="+item.fpid +" readOnly=\"true\"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>金额</td>"+
										"<td><input value="+item.money +" readOnly=\"true\"></td>"+
									"</tr>"+
									 "<tr>"+
										"<td>发票照片</td>"+
										"<td><img style=\"width: 100px;height: 100px\"src="+'${pageContext.request.contextPath}/imageshow.action?image='+item.fpimgurl + "></td>"+
									"</tr>"+
									"<tr>"+
										"<td>购物清单（pos条）</td>"+
										"<td><img style=\"width: 100px;height: 100px\" src="+'${pageContext.request.contextPath}/imageshow.action?image='+item.posimgurl + "></td>"+
									"</tr>"
									);
				        });
				});
			}
		}  
			
			
		 function formatOper(val,row,index,biaoname){  
		    return '<a href="javascript:void(0)" onclick="editUser('+index+')">查看</a>';  
		}  
		
		function editUser(index){ 
			
			$('#'+biaoname).datagrid('selectRow',index);// 关键在这里  
			var row = $('#'+biaoname).datagrid('getSelected');  
			//首先进行一次请求，把发票子表中的值做成list取出
			if (row){
				$("#info").css("display","block");
				$('#info').dialog({    
						    title: '详细信息',    
						    width: 800,    
						    height: 800,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
				$.getJSON('${pageContext.request.contextPath }/getfpzblist.action?fpid='+row.fpid, function(data) {
				        $("#info_table").html("");//清空info内容
				        $.each(data, function(i, item) {
				            $("#info_table").append(
				                  	"<tr>"+
										"<td>支出项目</td>"+
									"<td><input value="+item.fapiaoname+" readOnly=\"true\"></td>"+
									"</tr>"+
									"<tr>"+
										"<td>发票编号</td>"+
										"<td><input value="+item.fpid +" readOnly=\"true\"></td>"+
									"</tr>"+
									 "<tr>"+
										"<td>发票照片</td>"+
										"<td><img style=\"width: 100px;height: 100px\"src="+'${pageContext.request.contextPath}/imageshow.action?image='+item.fpimgurl + "/></td>"+
									"</tr>"+
									"<tr>"+
										"<td>购物清单（pos条）</td>"+
										"<td><img style=\"width: 100px;height: 100px\" src="+'${pageContext.request.contextPath}/imageshow.action?image='+item.posimgurl + "/></td>"+
									"</tr>"
									);
				        });
				         $("#info_table").append("<tr>"+
										"<td><input style=\"display: none\" name=\"fpcountid\" value="+item.fpcountid+"></td>"+
									"</tr>");
				        });
				//$('#fm').form('load','${pageContext.request.contextPath }/fpsqinfo.action?id='+row.id); 
				   
			}
		}  
		function d_close(){
			$('#info').dialog('close');
			}
			
		//修改密码
		function update_pwd(){
			$('#center_tab').tabs('add',{    
							    title:'修改密码',  
							    href:'updatapwd.jsp', 
							    fit:true,
							    closable:true
							    });	
		}
		//校验密码
		function updatepwd(param){
			$("#fpsq_cw").css("display","block");
				var  ss=true;
				var mytable = document.getElementById("fpsq");
				var nonull=mytable.getElementsByTagName("input");
				for(var i=0;i<nonull.length-1;i++)
				{
					if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
					{
						document.getElementById("cuowu").innerHTML="信息不完全";
						ss=false;
					}
				}
				if(ss)
				{
					if(param.newpwd.value!=param.newpwd2.value)
					{
						document.getElementById("cuowu").innerHTML="两次输入密码不一致";
					}else{
						var params = {oldpwd:param.oldpwd.value,newpwd:param.newpwd.value,newpwd2:param.newpwd2.value};
						url1 = '${pageContext.request.contextPath }/updatapwd.action';
						 $.ajax({
					        type: "POST",
					        url: url1,
					        data:params,
					        success: function (data) {
					             if(data.text=="修改成功")
					             {
					             	alert("修改成功,请退出重新登录");
					             	$('#center_tab').tabs('close',"修改密码");
					             	$.get('${pageContext.request.contextPath }/logout.action');
					             }else
					             {
									alert(data.text);				             
					             }
					        },
					        error: function () {
					           
					            alert(url1);
					        }
					    });
					};
				}
				return false;
			 } 
			 //发票总表申请
 				function checkdate(param){
				//非空校验
				$("#fpsq_cw").css("display","block");
				var  ss=true;
				var mytable = document.getElementById("fpsq");
				var nonull=mytable.getElementsByTagName("input");
				for(var i=0;i<nonull.length-1;i++)
				{
					if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
					{
						document.getElementById("cuowu").innerHTML="信息不完全~！"+i+nonull[i].name;
						ss=false;
					}
				}
				if(ss)
				{
					if(isNaN(nonull[2].value.trim()))
					{
						document.getElementById("cuowu").innerHTML="金额不是数字~！"
						ss=false;
					}
					else if(nonull[2].value.trim()<0){
						document.getElementById("cuowu").innerHTML="金额不能小于0~！"
						ss=false;
					}else{
						
					   	$.ajax({
					        type: "POST",
					        url: '${pageContext.request.contextPath}/addfp.action',
					        data: {name:param.name.value,xmid:param.xmid.value,use:param.use.value,count:param.count.value,money:param.money.value},
					        success: function (data) {
					        	 alert(data.text);
					        	 if(data.status==200){
					        	 	 $('#center_tab').tabs('close',"报账申请");
					        	 }
					           
					            
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
			                        alert("申请失败");
			                    }
					    });
					}
				}
				return false; 
			}
  		
			
	</script>
	

  </head>
  
  <body class="easyui-layout">   
    <div data-options="region:'north',title:'基本信息',split:true" style="height:100px;">
    <div >
    	<div style="text-align: center;line-height: 80px;height: 80px;width: 80%">
			       	学号&nbsp;:${sessionScope.user.userNo }&nbsp;&nbsp;
			       	用户名：${sessionScope.user.userName}&nbsp;&nbsp;
			       	手机号：${sessionScope.user.userPhone}&nbsp;&nbsp;
			       	邮箱：${sessionScope.user.useremail}&nbsp;&nbsp;
			       	身份：${sessionScope.user.identity}&nbsp;&nbsp;
			       	所属院系：${sessionScope.user.departmentsName}&nbsp;&nbsp;
			       	
		</div>
		<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="margin-top: -53px;margin-left: 80%;float: right" onclick="update_pwd()">修改密码</a>
		
		
		</div> 
	</div>   
    <div data-options="region:'south',title:'版权所有©2008-2015周口师范学院 网络工程学院 黄光旭',split:true" style="height:30px;"></div>   
	<div data-options="region:'west',title:'功能列表',split:true" style="width:230px;">
		<div id="according_west" data-options="fit:true" class="easyui-accordion" style="width:300px;height:200px;">     
			  <ul id="caozuo"></ul>  
		</div>  
	</div>   
	
    <div data-options="region:'east',iconCls:'icon-reload',title:'消息',split:true" style="width:200px;"></div>   
    
    <div data-options="region:'center',title:'主体框架'" style="padding:5px;background:#eee;">
    <div id="center_tab" class="easyui-tabs" style="width:500px;height:250px;background:white" data-options="fit:true,">
        <div title="首页" style="padding:20px;display:block;">
			欢迎光临本系统
        </div>
		</div>  
	</div>
	 <div id="fpzb" style="display: none" >
		  <form id="fpsq_add_fpzb"  method="POST" style="text-align: center;" enctype="multipart/form-data" onsubmit="return checkzbdate(this)">
			<table border="1">
				<tr id="fpzb_cw" style="display: none">
			    	<td colspan="2" style="text-align: center;"><span id="cuowu" style="color:red;"></span></td>
			    </tr>
				<tr>
					<td>支出项目</td>
					<td>
						<select id="fpzb_fptype" name="fptype">
						</select>
					</td>
				</tr>
				<tr>
					<td>发票编号</td>
					<td><input type="text" name="fpid"></td>
				</tr>
				<tr>
					<td>发票金额</td>
					<td><input type="text" name="money"></td>
				</tr>
				<tr>
					<td>发票照片</td>
					<td><input type="file" name="file1" id="file1"/></td>
				</tr>
				<tr>
					<td>购物清单（pos）</td>
					<td><input type="file" name="file2" id="file2"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input type="submit" value="添加发票"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="info" style="display: none;margin-top: 20px"  > 
		<table id="info_table" >
		</table>
		<input type="button" onclick="d_close()" value="关闭" style="margin-left: 100px">
	</div>
	<div id="info_sp" style="display: none;margin-top: 20px"  > 
	<form action="#" onsubmit="return spfpsqreult(this)" >
		<table id="sp_table" >
		</table>
			<table>
				<tr>
					<td>是否通过</td>
					<td>
						<select name="isno">
							<option value="0">同意</option>
							<option value="1">拒绝</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>原因(不同意填写)</td>
					<td><textarea rows="10" cols="60" name="nopass"></textarea> </td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input type="submit"  value="保存结果" ></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="userxg" style="display: none">
		<form onsubmit="return updateuser(this)">
			<table id="userxg_table"></table>
		</form>
	</div>
	<div id="jsxg" style="display: none">
		<form onsubmit="return updatejs(this)">
			<table id="jsxg_table"></table>
		</form>
	</div>
	<div id="adduser" style="display: none">
		<form id="saveform_clear" onsubmit="return saveuser(this)">
			<table id="adduser_table">
				<tr>
					<td>用户学号</td>
					<td><input name="userNo"></td>
				</tr>
				<tr>
					<td>姓名</td>
					<td><input name="userName"></td>
				</tr>
				<tr>
					<td>身份</td>
					<td><input name="identity"></td>
				</tr>
				<tr>
					<td>手机号</td>
					<td><input name="userPhone"></td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td><input name="useremail"></td>
				</tr>
				<tr>
					<td>权限等级</td>
					<td>
						<select id="adduser_position" name="positionNo">
						</select>
					</td>
				</tr>
				<tr>
					<td>学院</td>
					<td>
						<select name="departmentsNo">
							<option value="0001">网络工程学院</option>
							<option value="0000">学院总部</option>
							<option value="0002">政法学院</option>
							<option value="0003">生物与化工学院</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="添加"></td>
				</tr>
			</table>
		</form>		
	</div>
	<div id="uxm" style="display: none">
		<form onsubmit="return updatexm(this)">
			<table id="uxm_table"></table>
		</form>
	</div>
	<div id="addxm" style="display: none">
		<form onsubmit="return savexm(this)">
			<table id="addxm_table">
				<tr>
					<td>用户学号</td>
					<td><input name="userid"></td>
				</tr>
				<tr>
					<td>项目名称</td>
					<td><input name="xmname"></td>
				</tr>
				<tr>
					<td>预算金额</td>
					<td><input name="money"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center"><input type="submit" value="添加"></td>
				</tr>
			</table>
		</form>		
	</div>
	<div id="departments" style="display: none">
		<form onsubmit="return updatedepartments(this)">
			<table id="departments_table">
			</table>
		</form>		
	</div>
	<div id="adddepartments" style="display: none">
		<form onsubmit="return adddepartments(this)">
		<table>
			<tr>
				<td>院系名称</td>
				<td><input name="userid"></td>
			</tr>
			<tr>
				<td>院系编码</td>
				<td><input name="xmname" ></td>
			</tr>
			<tr>
	            <td colspan="2" style="text-align: center;"><input type="submit" value="添加"/></td>
	       		</tr>
		</table>
		</form>		
	</div>
</body>
<script type="text/javascript">
	//点击添加按钮显示
	function adddepartment(){
	$("#adddepartments").css("display","block");
				$('#adddepartments').dialog({    
						    title: '添加院系信息',    
						    width: 400,    
						    height: 500,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
	}
	//添加院系
	function adddepartments(param){
		var ss1=true;
		var mytable = document.getElementById("adddepartments");
		var nonull=mytable.getElementsByTagName("input");
		for(var i=0;i<nonull.length-1;i++)
		{
			//目前先做一个不能为空的校验
			if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
			{
				alert("所有项不能为空");
				ss1=false;
				break;
			}
		}
		if(ss1)
		{
				$.ajax({
				type: "POST",
				url: '${pageContext.request.contextPath}/adddepartments.action',
				data: {departmentsNo:param.departmentsNo.value,departmentsName:param.departmentsName.value},
				success: function (data) {
							if(data.status==200)
							{
						       alert(data.text);
						       $('#departments').dialog('close');
						       $("#departments").css("display","none");
						       $('#adddepartment').datagrid('reload');
					       }
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
			              alert("添加失败");
			    }
				});
		};
		return false; 
	}
	//修改院系
	function updatedepartments(param){
			var ss1=true;
		var mytable = document.getElementById("departments_table");
		var nonull=mytable.getElementsByTagName("input");
		for(var i=0;i<nonull.length-1;i++)
		{
			//目前先做一个不能为空的校验
			if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
			{
				alert("所有项不能为空");
				ss1=false;
				break;
			}
		}
		if(ss1)
		{
				$.ajax({
				type: "POST",
				url: '${pageContext.request.contextPath}/updateDepartment.action',
				data: {id:param.id.value,departmentsNo:param.departmentsNo.value,departmentsName:param.departmentsName.value},
				success: function (data) {
							if(data.status==200)
							{
						       alert(data.text);
						       $('#departments').dialog('close');
						       $("#departments").css("display","none");
						       $('#adddepartment').datagrid('reload');
					       }
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
			              alert("添加失败");
			    }
				});
		};
		return false; 
	}
	//角色管理
	function updatejs(param){
		var ss1=true;
		var mytable = document.getElementById("jsxg_table");
		var nonull=mytable.getElementsByTagName("input");
		for(var i=0;i<nonull.length-1;i++)
		{
			//目前先做一个不能为空的校验
			if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
			{
				alert("所有项不能为空");
				ss1=false;
				break;
			}
		}
		if(ss1)
		{
			if(isNaN(nonull[2].value.trim()) && isNaN(nonull[3].value.trim()) && isNaN(nonull[4].value.trim()))
			{
				alert("所有项必须为0或1");
			}
			else{
				$.ajax({
				type: "POST",
				url: '${pageContext.request.contextPath}/updateCaozuo.action',
				data: {id:param.id.value,user:param.user.value,sp:param.sp.value,admin:param.admin.value},
				success: function (data) {
					       alert(data.text);
					       $('#jsxg').dialog('close');
					        $("#jsxg").css("display","none");
					       $('#jsgl').datagrid('reload');
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
			              alert("添加失败");
			    }
				});
			};
		}
		return false; 
	}
	//添加项目
	function addxm(){
		$("#addxm").css("display","block");
				$('#addxm').dialog({    
						    title: '新增项目',
						    width: 300,    
						    height: 400,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
	}
	//添加项目表单提交
	function savexm(param)
	{
		var ss1=true;
		var mytable = document.getElementById("addxm_table");
		var nonull=mytable.getElementsByTagName("input");
		for(var i=0;i<nonull.length-1;i++)
		{
			//目前先做一个不能为空的校验
			if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
			{
				alert("所有项不能为空");
				ss1=false;
				break;
			}
		}
		if(ss1)
		{
			if(isNaN(nonull[0].value.trim()))
			{
				alert("学号必须为数字");
			}
			else if(isNaN(nonull[2].value.trim())){
				alert("预算金额必须为数字");
			}else if(nonull[2].value.trim()<0)
			{
				alert("预算金额必须大于0");
			}
			else{
				$.ajax({
				type: "POST",
				url: '${pageContext.request.contextPath}/addxm.action',
				data: {userid:param.userid.value,xmname:param.xmname.value,money:param.money.value},
				success: function (data) {
					       alert("添加成功");
					       $('#addxm').dialog('close');
					        $("#addxm").css("display","none");
					       $('#xm').datagrid('reload');
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
			              alert("添加失败");
			    }
				});
			};
		}
		return false; 
	}
	//修改用户
	function updateuser(param){
				var ss=true;
				var mytable = document.getElementById("userxg_table");
				var nonull=mytable.getElementsByTagName("input");
				for(var i=0;i<nonull.length-1;i++)
				{
					//目前先做一个不能为空的校验
					if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
					{
						alert("所有项不能为空");
						ss=false;
					}
				}
				if(ss)
				{
					if(isNaN(nonull[0].value.trim()))
					{
						alert("编号必须为数字");
						ss=false;
					}
					else{
					   	$.ajax({
					        type: "POST",
					        url: '${pageContext.request.contextPath}/uuser.action',
					        data: {id:param.id.value,userNo:param.userNo.value,userName:param.userName.value,identity:param.identity.value,userPhone:param.userPhone.value,useremail:param.useremail.value,positionNo:param.positionNo.value,departmentsName:param.departmentsName.value,departmentsNo:param.departmentsNo.value},
					        success: function (data) {
					        	 alert("修改成功");
					            $('#userxg').dialog('close');
					            $("#userxg").css("display","none");
					            $('#usergl').datagrid('reload');
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
			                        alert("修改失败");
			                }
					    });
					}
				}
				return false; 
		}
	//添加用户
	function adduser(){
		$("#adduser").css("display","block");
				$('#adduser').dialog({    
						    title: '添加用户',    
						    width: 800,    
						    height: 800,    
						    closed: false,    
						    cache: false,    
						    modal: true   
						});
		//权限等级动态添加option
		$.getJSON('${pageContext.request.contextPath }/getpositionlist.action', function(data) {
		$("#adduser_position").html("");//清空info内容
		$.each(data, function(i, one) {
				      $("#adduser_position").append(
				        	"<option value="+one.positionNo+" >"+one.positionName+"</option>"
				       );
				 });
		});				
						
	}
	function saveuser(param)
	{
		var ss=true;
		var mytable = document.getElementById("adduser_table");
		var nonull=mytable.getElementsByTagName("input");
		for(var i=0;i<nonull.length-1;i++)
		{
			//目前先做一个不能为空的校验
			if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
			{
				alert("所有项不能为空");
				ss=false;
			}
		}
		if(ss)
		{
			if(isNaN(nonull[0].value.trim()))
			{
				alert("编号必须为数字");
				ss=false;
			}
			else{
				$.ajax({
				type: "POST",
				url: '${pageContext.request.contextPath}/adduser.action',
				data: {id:param.id.value,userNo:param.userNo.value,userName:param.userName.value,identity:param.identity.value,userPhone:param.userPhone.value,useremail:param.useremail.value,positionNo:param.positionNo.value,departmentsNo:param.departmentsNo.value},
				success: function (data) {
					       alert("添加成功");
					       $('#adduser').dialog('close');
					        $("#adduser").css("display","none");
					        $("#saveform_clear").form('clear');
					       $('#usergl').datagrid('reload');
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
			              alert("添加失败");
			    }
				});
			}
		}
		return false; 
	}
	function add_fp(){
		//当点击的时候就生成一个申请id
		$("#fpzb").css("display","block");
		$.getJSON('${pageContext.request.contextPath }/getfptype.action', function(data) {
					        $("#fpzb_fptype").html("");//清空info内容
					        $.each(data, function(i, item) {
						            $("#fpzb_fptype").append(
						                  	"<option value="+item.id+">"+item.fapiaoname+"</option>"
											);
						        });
					        })
		$('#fpzb').dialog({    
						    title: '详细信息',    
						    width: 400,    
						    height: 400,    
						    closed: false,    
						    cache: false,
						    modal: true   
						});
		$("#fpzb_cw").css("display","block");
		$("#fpsq_add_fpzb").form('clear');
		
	}
	function checkzbdate(param)
	{
		//非空校验
				$("#fpzb_cw").css("display","block");
				var  ss=true;
				var mytable = document.getElementById("fpsq_add_fpzb");
				var nonull=mytable.getElementsByTagName("input");
				for(var i=0;i<nonull.length-1;i++)
				{
					if(nonull[i].value.trim()==null || nonull[i].value.trim()=="")
					{
						document.getElementById("cuowu").innerHTML="信息不完全~！";
						ss=false;
					}
				}
				if(ss)
				{
					if(isNaN(nonull[1].value.trim()))
					{
						document.getElementById("cuowu").innerHTML="金额不是数字~！"
						ss=false;
					}
					else if(nonull[1].value.trim()<0){
						document.getElementById("cuowu").innerHTML="金额不能小于0~！"
						ss=false;
					}else{
						//var url = ; //访问控制器是upload，后面必须加'/'否则会报错"org.apache.catalina.connector.RequestFacade cannot be cast to org.springframework.web.multipart.Mult...",但是如果是多级的URL【例如XX/XXX/00/upload/0】又没问题了.
					   	var s1 = $('#file1')[0].files[0];//获取file控件中的内容
					   	var s2 = $('#file2')[0].files[0];
					    var fd = new FormData();
					    fd.append("fptype",param.fptype.value);
					    fd.append("fpid",param.fpid.value);
					    fd.append("money",param.money.value);
					    fd.append("errPic", s1);
					    fd.append("errPic2", s2);
					   	$.ajax({
					        type: "POST",
					        contentType:false, //必须false才会避开jQuery对 formdata 的默认处理 , XMLHttpRequest会对 formdata 进行正确的处理
					        processData: false, //必须false才会自动加上正确的Content-Type 
					        url: '${pageContext.request.contextPath}/add/fpzb.action',
					        data: fd,
					        success: function (msg) {
					        	 alert("添加成功");
					            $('#fpzb').dialog('close');
					            $("#count").attr("value",parseInt($("#count").attr("value"))+1+"");
								$("#money").attr("value",parseInt($("#money").attr("value"))+parseInt(param.money.value)+"");
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
			                        alert("添加失败");
			                    }
					    });
					}
				}
				return false; 
	}
	function spfpsqreult(param)
	{
			//非空校验
			var  ss=true;
			if(param.isno.value.trim()=="" &&param.isno.value==null)
			{
				alert("请选择是否通过");
				ss=false;
			}else if(param.isno.value==2){
				if(ss.nopass.value.trim()==""&&ss.nopass.value==null)
				{
					alert("请输入原因！");
					ss=false;
				}
			}
			if(ss)
			{
				$.ajax({
					        type: "POST",
					        url: '${pageContext.request.contextPath}/spfp_result.action',
					        data: {isno:param.isno.value,nopass:param.nopass.value,fpcountid:param.fpid.value},
					        dataType:"json",
					        success: function (data) {
					        	 alert(data.text);
					        	 if(data.status==200)
					        	 {
						        	$('#info_sp').dialog('close');
						            $('#dsp').datagrid('reload');
					            }
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
			                        alert("操作失败");
			                    }
					    });
			}
				
			return false; 
	}
   //获取发票申请列表数据
   function fpsqlist_getJson(param){
   		 	//通过ajax请求数据,(因为一般表单提交以后都会默认跳转页面，所以要自定义提交，并且阻止表单默认提交)
   		 	if(param.beginTime.value=="" || param.endTime.value=="")
   		 	{
   		 		document.getElementById("datenull").innerHTML="日期不能为空";
   		 	}
   		 	else{
   		 		var fapiaolist_json="fpsqlist.action";
   		 		document.getElementById("datenull").innerHTML="";
	  			$.ajax(
	  				{
						url:fapiaolist_json,
						data:{'beginTime':param.beginTime.value,'endTime':param.endTime.value,'UserNo':param.UserNo.value,'del':param.del.value},
						contentType:"application/json;charset=utf-8",  
						success:function(data){
							//服务端响应的json数据（存储的是student对象）
							//成功申请后作出的反应
							$("#"+biaoname).datagrid({
								pagination:true,//分页控件
								fit:true,	
								url:'${pageContext.request.contextPath }/pageServlet.action',
								method:"POST",
								autoRowHeight:true
							});
						}
	  				}
	  			);
  			}
		return false;
   }
</script>
</html>
