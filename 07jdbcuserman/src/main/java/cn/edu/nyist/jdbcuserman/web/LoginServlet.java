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
		// 1����ȡ�û�����
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");

		// System.out.println(name + " " + pwd);
		// ���û��������֤�����������֤����жԱȣ�������ȣ������������ݿ⣬���Ҫ�ڲ�ѯ���ݿ�֮ǰ�Ա�
		String vcode = request.getParameter("vcode");// ���ܿͻ����������֤��
		HttpSession session = request.getSession();
		String serverVcode = (String) session.getAttribute("validateCode");// ��ȡϵͳ��������֤��

		if (!vcode.equalsIgnoreCase(serverVcode)) {// �Ա�
			// ����ʱ��Ҫ��ʾ��֤������û�������
			request.setAttribute("msg", "��֤�����");
			request.setAttribute("name", name);// ʧ���û�������
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		// 2�������ݿ�ȶ�
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
		// �ж�һ�ѣ�ʧ�ܻ�����¼ҳ�棬�ɹ�����ҳ��
		if (ret) {
			// �ɹ�
			response.sendRedirect("main.jsp");
		} else {
			// ʧ��
			// ʧ��ʱ��ʾ���û������������
			request.setAttribute("msg", "�û������������");
			request.setAttribute("name", name);
			;// ʧ���û�������
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
