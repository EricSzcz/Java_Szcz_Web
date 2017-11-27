package br.com.apex.javaweb.usuario;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns= {"/login",
						  "/logout"})
public class ControleUsuario extends HttpServlet {
	
	private RepositorioUsuario repositorio = new RepositorioUsuario();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/login":
			login(req, resp);
			break;

		case "/logout":
			logout(req, resp);
			break;
		}
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}		
		resp.sendRedirect(req.getContextPath()+"/login");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		autenticar(req, resp);
	
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/usuarios/formulariologin.jsp");
		dispatcher.forward(req,resp);

	}
	
	private void autenticar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("login.email");
		String senha = req.getParameter("login.senha");
		Usuario usuario = repositorio.buscarUsuarioPorEmail(email);
		if (usuario != null && usuario.getSenha().equals(senha)) {
			HttpSession session = req.getSession();
			session.setAttribute("usuarioLogado", usuario);
			resp.sendRedirect(req.getContextPath()+"/produtos/listaprodutos");
		}else {
			//Colocar mensagem avisando que o login falhou
			HttpSession session = req.getSession();
			session.setAttribute("mensagemErro", "Email e/ou Senha inválidos");
			usuario = new Usuario();
			usuario.setEmail(email);
			session.setAttribute("login", usuario);
			resp.sendRedirect(req.getContextPath()+"/login");
		}
	}

}
