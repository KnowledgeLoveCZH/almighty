package com.almighty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServlet;

public class QueryStockServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4689319292570477424L;

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request,response);
    }
 
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
 
        String stockId = request.getParameter("stockId");
        System.out.println(stockId);                                      //用于测试 ，判断是否成功获取到数据；
        String result = null;
        
        //调用查询股票信息的公共接口
        //1、先调用新浪的股票查询接口，小试牛刀
        // 创建一个URL对象
        String url = "http://hq.sinajs.cn/list=" + stockId;
        URL targetUrl = new URL(url);
        // 从URL对象中获得一个连接对象
        HttpURLConnection conn = (HttpURLConnection) targetUrl.openConnection();
        // 设置请求方式 注意这里的POST或者GET必须大写
        conn.setRequestMethod("POST");
        // 设置POST请求是有请求体的
        conn.setDoOutput(true);
        // 拼接发送的短信内容
        String params = "";
        // 写入参数
        conn.getOutputStream().write(params.toString().getBytes());
        // 发送请求
        conn.connect();
        // 通过connection连接，获取输入流
        if (conn.getResponseCode() == 200) {
            InputStream is = conn.getInputStream();
            // 封装输入流is，并指定字符集
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            // 存放数据
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        }
        
        out.println(result + " 解析成功 ");                                                 //将数据传到前端
    }
}
