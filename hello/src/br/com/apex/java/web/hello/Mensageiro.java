package br.com.apex.java.web.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/mensageiro.do"},name="mensageiro")

public class Mensageiro extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mensagem = req.getParameter("mensagem");
		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title> Dedo Duro </title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1> A Mensagem informada foi "+mensagem+"</h1>");
		writer.println("</body>");
		writer.println("</html>");
		
		resp.setContentType("text/html");
		
	}

}
