<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>first page</title>
<script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<div>
	股票代码：<input id="stockId"></input>
	<button onclick="queryStockInfo()">查询股票信息</button>
	<br/>
	<span id="msg"></span>
</div>
<script type="text/javascript">
	function queryStockInfo(){
		$("#msg").text("查询股票代码是： " + $("#stockId").val());
		var param = {stockId : $("#stockId").val()};
		$.ajax({
            type: "POST",                            //传数据的方式
            url: "query/stock",                             //servlet地址
            data: param,            //传的数据  form表单 里面的数据
            success: function(result){               //传数据成功之后的操作   result是servlet传过来的数据  这个函数对result进行处理，让它显示在 输入框中
                alert(result);
            }
        });
	}
</script>
</body>
</html>