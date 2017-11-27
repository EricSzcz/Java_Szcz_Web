package br.com.apex.java.web.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		PrintWriter writer = resp.getWriter();  //Criando variavel para escrever texto
		writer.println("Primeiro Servlet!!!");
		writer.println("Seu nome é: "+nome);
		writer.println(LocalDateTime.now());
	}
	
}
