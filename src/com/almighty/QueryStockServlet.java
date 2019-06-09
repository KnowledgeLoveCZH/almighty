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
        System.out.println(stockId);                                      //���ڲ��� ���ж��Ƿ�ɹ���ȡ�����ݣ�
        String result = null;
        
        //���ò�ѯ��Ʊ��Ϣ�Ĺ����ӿ�
        //1���ȵ������˵Ĺ�Ʊ��ѯ�ӿڣ�С��ţ��
        // ����һ��URL����
        String url = "http://hq.sinajs.cn/list=" + stockId;
        URL targetUrl = new URL(url);
        // ��URL�����л��һ�����Ӷ���
        HttpURLConnection conn = (HttpURLConnection) targetUrl.openConnection();
        // ��������ʽ ע�������POST����GET�����д
        conn.setRequestMethod("POST");
        // ����POST���������������
        conn.setDoOutput(true);
        // ƴ�ӷ��͵Ķ�������
        String params = "";
        // д�����
        conn.getOutputStream().write(params.toString().getBytes());
        // ��������
        conn.connect();
        // ͨ��connection���ӣ���ȡ������
        if (conn.getResponseCode() == 200) {
            InputStream is = conn.getInputStream();
            // ��װ������is����ָ���ַ���
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            // �������
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        }
        
        out.println(result + " �����ɹ� ");                                                 //�����ݴ���ǰ��
    }
}
