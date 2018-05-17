package cn.edu.nyist.jdbcuserman.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1、获取用户输入
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");

		// System.out.println(name + " " + pwd);
		// 将用户输入的验证码与产生的验证码进行对比，如果不等，则不能连接数据库，因此要在查询数据库之前对比
		String vcode = request.getParameter("vcode");// 接受客户端输入的验证码
		HttpSession session = request.getSession();
		String serverVcode = (String) session.getAttribute("validateCode");// 获取系统产生的验证码

		if (!vcode.equalsIgnoreCase(serverVcode)) {// 对比
			// 不等时，要显示验证码错误，用户名回填
			request.setAttribute("msg", "验证码错误");
			request.setAttribute("name", name);// 失败用户名回填
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		// 2、到数据库比对
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean ret = false;
		try {
			conn = DsUtil.getconn();
			String sql = "select * from tb02 where name=? and pwd=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if (rs.next()) {
				ret = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DsUtil.free(rs, stmt, conn);
		}
		// 判断一把，失败还到登录页面，成功到主页面
		if (ret) {
			// 成功
			response.sendRedirect("main.jsp");
		} else {
			// 失败
			// 失败时显示：用户名或密码错误
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("name", name);
			;// 失败用户名回填
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
