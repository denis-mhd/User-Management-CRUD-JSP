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
import com.user.management.model.binding.UserBindingModel;
import com.user.management.model.entity.User;
import com.user.management.model.service.UserServiceModel;
import com.user.management.service.UserService;
import com.user.management.service.UserServiceImpl;
import com.user.management.util.mapper.Mapper;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private UserService userService;
	private Mapper mapper; 

	
	public void init() {
		userDao = new UserDaoImpl();
		mapper = new Mapper();
		userService = new UserServiceImpl(userDao, mapper);
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
		UserBindingModel newUser = new UserBindingModel(firstName, lastName, dateOfBirth, phoneNumber, emailAddress);
		userService.insert(this.mapper.map(newUser, UserServiceModel.class));
		res.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		UserBindingModel existingUser = this.userService.select(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<UserBindingModel> listUsers = this.userService.getAll();
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
		UserBindingModel user = new UserBindingModel(id, firstName, lastName, dateOfBirth, phoneNumber, emailAddress);
		userService.update(this.mapper.map(user, UserServiceModel.class));
		res.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		this.userService.delete(id);
		res.sendRedirect("list");
	}
	
	private void searchUser(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException, ServletException {
		String name = req.getParameter("q");
		List<UserBindingModel> listUsers = userService.find(name);
		req.setAttribute("listUser", listUsers);
		RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(req, res);
	}
	
	

}
