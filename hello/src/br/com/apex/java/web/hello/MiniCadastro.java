package br.com.apex.java.web.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/minicadastro.do"},name="MiniCadastro") 

public class MiniCadastro extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome  = req.getParameter("nome");
		String idade = req.getParameter("idade");
		PrintWriter writer = resp.getWriter();
		
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>O nome digitado foi: "+nome+"</h1>");
		writer.println("<h1>A idade digitada foi: "+idade+"</h1>");
		writer.println("</body>");
		writer.println("</html>");
		
		resp.setContentType("text/html");
	}

}
