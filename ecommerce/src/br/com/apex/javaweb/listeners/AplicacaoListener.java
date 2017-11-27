package br.com.apex.javaweb.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AplicacaoListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Aplicação morreu!");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Aplicação subiu!!!");
	}

}
