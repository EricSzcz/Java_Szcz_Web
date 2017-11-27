package br.com.apex.javaweb.usuario;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/*" })
public class FiltroAutenticacao implements Filter {
	
	private String[] listaPublicaUrl = {"/login"}; 
	private String[] listaPublicaPath = {"/WEB-INF/views/usuarios/formulariologin.jsp","/sessoes.jsp"};
	private String[] listaPublicaResources = {"/resources/"};

  
    public FiltroAutenticacao() {
        
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String path = req.getServletPath();
		//verifica se existe usuario autenticado ou nao
		HttpSession session = req.getSession(false);
		
		if (isPathPublico(path)) {
			chain.doFilter(request, response);	
		} else if (session != null) {
				//verificar usuario autenticado
			Usuario usuarioLogado = (Usuario)session.getAttribute("usuarioLogado");
			if (usuarioLogado != null) {
				//tudo OK permite a continuaçao do processo
				chain.doFilter(request, response);
			}else {
				//tem sessao mas nao tem usuario, redireciona para login
				resp.sendRedirect(req.getContextPath()+"/login");
			}
		}else {
			// se nao é publica e não esta autenticada redireciona para login
			resp.sendRedirect(req.getContextPath()+"/login");
			

		}
				
	}


	private boolean isPathPublico(String path) {
		boolean resultado = false;
		
		for(String caminho: listaPublicaUrl) {
			resultado = path.startsWith(caminho);
			if (resultado) {
				return resultado;
			}
		}
		
		for(String caminho: listaPublicaPath) {
			resultado = path.startsWith(caminho);
			if (resultado) {
				return resultado;
			}
		}

		for(String caminho: listaPublicaResources) {
			resultado = path.startsWith(caminho);
			if (resultado) {
				return resultado;
			}
		}
		
		return resultado;
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
