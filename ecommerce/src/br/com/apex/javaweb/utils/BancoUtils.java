package br.com.apex.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoUtils {
	
	private static Connection conexao;
	private static String url = "jdbc:mariadb://localhost:3306/mariadb";
	private static String usuario = "root";
	private static String senha = "toor";
	
	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Falha ao carregar o driver");
		}
	}
	
	public static Connection getConexao()  throws SQLException{
		try {
			if (conexao == null || conexao.isClosed()) {
				conexao = DriverManager.getConnection(url,usuario,senha);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Falha ao criar a conexão com o banco de dados");
		}
		return conexao;
	}
	
	public static void main(String[] args) throws SQLException {
		Connection con = BancoUtils.getConexao();
		System.out.println(con);
		con.close();
	}
	
	

}
