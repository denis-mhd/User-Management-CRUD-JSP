package com.user.management.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.management.dao.UserDaoImpl;
import com.user.management.model.entity.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	
	public void init() {
		userDao = new UserDaoImpl();
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getServletPath();
		
		try {
			switch(action) {
				case "/new":
					showForm(req, res);
					break;
				case "/insert":
					insertUser(req, res);
					break;
				case "/delete":
					deleteUser(req, res);
					break;
				case "/edit":
					showEditForm(req, res);
					break;
				case "/update":
					updateUser(req, res);
					break;
				case "/search":
					searchUser(req, res);
					break;
				default:
					listUser(req, res);
					break;
			}
		}catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}
	
	private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void insertUser(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
		int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
		String emailAddress = req.getParameter("emailAddress");
		User newUser = new User(firstName, lastName, dateOfBirth, phoneNumber, emailAddress);
		userDao.insertUser(newUser);
		res.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDao.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUsers = userDao.getAll();
		request.setAttribute("listUser", listUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}


	private void updateUser(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
		String phoneNumber1 = req.getParameter("phoneNumber");
		int phoneNumber = Integer.parseInt(phoneNumber1);
		String emailAddress = req.getParameter("emailAddress");
		User user = new User(id, firstName, lastName, dateOfBirth, phoneNumber, emailAddress);
		userDao.updateUser(user);
		res.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		userDao.deleteUser(id);
		res.sendRedirect("list");
	}
	
	private void searchUser(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException {
		String name = req.getParameter("q");
		List<User> users = userDao.findUser(name);
		req.setAttribute("listUser", users);
		res.sendRedirect("list");
	}
	

}
