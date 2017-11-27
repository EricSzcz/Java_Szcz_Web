package br.com.apex.javaweb.produto;

import java.io.IOException;
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

@WebServlet(urlPatterns = { "/produtos/listaprodutos",
							"/produtos/novo",
							"/produtos/salvar",
							"/produtos/editar",
							"/produtos/remover"},
							name="ControllerProduto")

public class ControllerProduto extends HttpServlet {

	private RepositorioProduto repositorio = new RepositorioProduto();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/produtos/listaprodutos":
			listar(req, resp);
			break;
		case "/produtos/novo":
			novo(req, resp);
			break;
		case "/produtos/editar":
			editar(req, resp);
			break;
		case "/produtos/remover":
			remover(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/produtos/salvar":
			salvar(req, resp);
			break;
		}
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Produto> produtos = repositorio.buscarTodos();
		String titulo = getServletConfig().getInitParameter("tituloListaProduto");	
		req.setAttribute("tituloHeader", titulo);		
		req.setAttribute("lista", produtos);		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/produtos/listaprodutos.jsp");
		dispatcher.forward(req, resp);
	}

	private void novo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titulo = getServletConfig().getInitParameter("tituloNovo");	
		req.setAttribute("tituloHeader", titulo);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/produtos/formulario.jsp");
		dispatcher.forward(req, resp);
	}

	private void salvar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sId = req.getParameter("produto.id");
		Integer id = sId != null && !"".equals(sId.trim()) ? Integer.valueOf(sId) : null;
		String nome = req.getParameter("produto.nome");
		String descricao = req.getParameter("produto.descricao");
		String sPreco = req.getParameter("produto.preco");
		Double preco = sPreco != null && !"".equals(sPreco.trim()) ? Double.valueOf(sPreco) : null;

		
		Produto novoProduto = new Produto(id, nome, descricao, preco);
		repositorio.salvarProduto(novoProduto);

		resp.sendRedirect(req.getContextPath()+"/produtos/listaprodutos");
	}
	
	private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titulo = getServletConfig().getInitParameter("tituloEditar");	
		req.setAttribute("tituloHeader", titulo);
		String sid = req.getParameter("id");
		Integer id = sid != null && !"".equals(sid.trim()) ? Integer.valueOf(sid) : null;
		Produto atual = repositorio.buscarProdutoPorIdl(id);
		
		if (atual != null) {
			req.setAttribute("produtoAtual", atual);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/produtos/formulario.jsp");
			dispatcher.forward(req, resp);
		}else {
			resp.sendError(406,"Produto nao encontrado");
		}
		
	}
	
	private void remover(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sId = req.getParameter("id");
		Integer id = sId != null && !"".equals(sId.trim()) ? Integer.valueOf(sId) : null;
		repositorio.remover(id);
		resp.sendRedirect(req.getContextPath()+"/produtos/listaprodutos");
	}
	
}
