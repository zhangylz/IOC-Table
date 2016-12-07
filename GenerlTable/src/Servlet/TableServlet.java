package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BeanFactory.IOCFactory;
import TableUtils.TableUtils;
import Test.Demo;

@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TableServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        if(type.equals("initTable")){
        	String tableRows = TableUtils.transTableRows("血型","星座","日期");
    		out.write(tableRows);
    		out.close();
        }else{
        	try {
        		String jsonData = request.getParameter("jsonData");
        		//IOC容器获取Bean
        		Demo demo = IOCFactory.newInstance().getBean(Demo.class);
        		demo.setTableData(jsonData);
            	System.out.println(demo);
            	out.write(jsonData);
            	out.close();
			} catch (Exception e) {
				out.write("失败！");
				out.close();
			}
        	
        }
	}

}
