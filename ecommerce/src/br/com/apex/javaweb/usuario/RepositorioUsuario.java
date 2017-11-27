package br.com.apex.javaweb.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.apex.javaweb.utils.BancoUtils;

public class RepositorioUsuario {
	private static final String SELECT_ALL = "SELECT * FROM USUARIO";
	private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM USUARIO WHERE EMAIL = ?";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM USUARIO WHERE ID = ?";
	private static final String INSERT_USER = "INSERT INTO USUARIO(NOME, EMAIL, SENHA) VALUES(?,?,?)"; 
	private static final String UPDATE_USER = "UPDATE USUARIOS SET NOME=?, EMAIL=?, SENHA=? WHERE ID=?";
	private static final String DELETE_USER = "DELETE FROM USUARIO WHERE ID=?";
	
	public List<Usuario> buscarTodos() {
		List<Usuario> lista = new ArrayList<>();
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(SELECT_ALL);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				lista.add(carregaDadosUsuario(resultSet));
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao buscar lista de usuarios");
		}
		return lista;
	}
	
	public Usuario buscarUsuarioPorEmail(String email) {
		Usuario usuario = null;
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(SELECT_USER_BY_EMAIL);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				usuario = carregaDadosUsuario(resultSet);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao buscar usuario com email "+email);
		}
		return usuario;		
		
	}
	
	public Usuario buscarUsuarioPorIdl(Integer id) {
		Usuario usuario = null;
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(SELECT_USER_BY_ID);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				usuario = carregaDadosUsuario(resultSet);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao buscar usuario com id "+id);
		}
		return usuario;		
		
	}
	
	public void remover(Integer id) {
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(DELETE_USER);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao remover usuario ");
		}					
	}
	
	public void salvarUsuario(Usuario usuario) {
		if (usuario != null) {
			if (usuario.getId() != null) {
				atualizar(usuario);
			}else {
				inserir(usuario);
			}
		}
	}
	
	private void atualizar(Usuario usuario) {
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(UPDATE_USER);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setInt(4, usuario.getId());
			stmt.executeUpdate();
			stmt.close();			
		} catch (SQLException e) {
			System.err.println("Falha ao atualizar usuario ");
		}			
	}

	
	private void inserir(Usuario usuario) {
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(INSERT_USER);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.execute();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				usuario.setId(resultSet.getInt(1));
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao inserir usuario ");
		}			
		
	}


	private Usuario carregaDadosUsuario(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("ID");
		String nome = resultSet.getString("NOME");
		String email = resultSet.getString("EMAIL");
		String senha = resultSet.getString("SENHA");
		return new Usuario(id, nome, email, senha);
	}
	
}
