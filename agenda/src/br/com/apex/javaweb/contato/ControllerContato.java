package br.com.apex.javaweb.contato;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns= {
					"/contatos/lista",
					"/contatos/novo",
					"/contatos/salvar",
					"/contatos/editar",
					"/contatos/remover"})

public class ControllerContato extends HttpServlet {
	
	private List<Contato> contatos = new ArrayList<>();
	private Integer gerador = 5;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		inicializarDados();
	}

	private void inicializarDados() {
		contatos.add(new Contato(1,"João","33343678","Joao@Teste.com"));
		contatos.add(new Contato(2,"Maria","33347890","Maria@Teste.com"));
		contatos.add(new Contato(3,"Adao","33344567","Adao@Teste.com"));
		contatos.add(new Contato(4,"Eva","33343678","Eva@Teste.com"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case "/contatos/lista":
			listar(req,resp);
			break;
		case "/contatos/novo":
			novo(req,resp);
			break;			
		case "/contatos/editar":
			editar(req,resp);
			break;			

		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case "/contatos/salvar":
			salvar(req,resp);
			break;
			
		}
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Collections.sort(contatos);
		req.setAttribute("lista", contatos);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/contatos/lista.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void novo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/contatos/formulario.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void salvar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("contato.id");
		Integer id = sid != null && !"".equals(sid.trim()) ? Integer.valueOf(sid): null;
		String nome = req.getParameter("contato.nome");
		String email = req.getParameter("contato.email");
		String telefone = req.getParameter("contato.telefone");
		if (id == null) {
			id = gerador++;
			
		}
		
		Contato novoContato = new Contato(id, nome, telefone, email);
		if (!contatos.contains(novoContato)) {
			contatos.add(novoContato);
		} else {
			contatos.remove(novoContato);
			contatos.add(novoContato);
		}
		
		resp.sendRedirect(req.getContextPath()+"/contatos/lista");
	}
	
	private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sId = req.getParameter("id");
		Integer id = sId != null && !"".equals(sId.trim()) ? Integer.valueOf(sId): null;
		Contato atual = null;
		for (Contato contato : contatos) {
			if (contato.getId().equals(id)) {
				atual = contato;
				break;
			}
		}
		if (atual != null) {
			req.setAttribute("contatoAtual", atual);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/contatos/formulario.jsp");
			dispatcher.forward(req, resp);
		} else {
			resp.sendError(406,"Contato não encontrado");
		}		
	}
	
	
	
}
