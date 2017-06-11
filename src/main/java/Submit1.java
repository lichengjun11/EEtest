import com.mysql.jdbc.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by lichengjun on 2017/6/10.
 */
@WebServlet(urlPatterns = "/index")
public class Submit1 extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String ip = req.getParameter("ip");
        Connection Connection = Db1.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM db_1702.iptest WHERE inet_aton(?) BETWEEN inet_aton(start) AND inet_aton(stop)";
//
            if (Connection != null) {
                preparedStatement = Connection.prepareStatement(sql);
            }
//        if (Connection != null) {
//        }
//        } else {
//            req.setAttribute("qq","出现故障");
//            req.getRequestDispatcher("index.jsp").forward(req,resp);
//            return;
//        }
            preparedStatement.setString(1,ip);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                req.setAttribute("message", resultSet.getString("loc"));
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }else {
                req.setAttribute("message","没有结果");
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }finally {
            Db1.close(resultSet, preparedStatement,Connection);
        }
    }
    }


//@WebServlet(urlPatterns = "/index")
//public class Submit1 extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String ip = req.getParameter("ip");
//
//        try {
//            new com.mysql.jdbc.Driver();
//            Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");
//            String sql = "SELECT * FROM db_1702.iptest WHERE inet_aton(?) BETWEEN inet_aton(start) AND inet_aton(stop)";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, ip);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                req.setAttribute("message", resultSet.getString("loc"));
//                req.getRequestDispatcher("index.jsp").forward(req,resp);
//
//            }
//            else {
//                req.setAttribute("me","输入错误");
//                req.getRequestDispatcher("index.jsp").forward(req,resp);
//            }
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//}