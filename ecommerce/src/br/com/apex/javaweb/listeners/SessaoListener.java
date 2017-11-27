package br.com.apex.javaweb.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessaoListener implements HttpSessionListener {
	int contador = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent evento) {
		evento.getSession().getServletContext().setAttribute("totalSecoes", ++contador);
		evento.getSession().setMaxInactiveInterval(9999);
		
	}
	
	public void sessionDestroyed(HttpSessionEvent evento) {
		evento.getSession().getServletContext().setAttribute("totalSecoes", --contador);
		
	}
		
}
