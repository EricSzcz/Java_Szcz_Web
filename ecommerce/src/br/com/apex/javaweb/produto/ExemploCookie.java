package br.com.apex.javaweb.produto;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/addcookies",
						  "/vercookies"	})
public class ExemploCookie extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case "/addcookies":
			addCookies(req,resp);
			break;
		case "/vercookies":
			verCookies(req,resp);
			break;

		}
	}


	private void addCookies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie cookie1 =new Cookie("cursointeresse","JavaWeb");
		cookie1.setMaxAge(60);
		Cookie cookie2 = new Cookie("cursointeresse2","FormacaoFrontend");
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);
		resp.getWriter().println("Cookies adicionados");
		
	}
	
	private void verCookies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		PrintWriter writer = resp.getWriter();
		for(Cookie atual : cookies) {
			writer.println("chave = "+ atual.getName() + "Valor = "+ atual.getValue());
		}
		
	}
	
}
