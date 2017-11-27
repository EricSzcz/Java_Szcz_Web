package br.com.apex.javaweb.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.apex.javaweb.usuario.Usuario;
import br.com.apex.javaweb.utils.BancoUtils;

public class RepositorioProduto {
	private static final String SELECT_ALL = "SELECT * FROM PRODUTOS";
	private static final String SELECT_PRODUTOS_BY_NOME = "SELECT * FROM PRODUTOS WHERE NOME = ?";
	private static final String SELECT_PRODUTOS_BY_ID = "SELECT * FROM PRODUTOS WHERE ID = ?";
	private static final String INSERT_PRODUTOS = "INSERT INTO PRODUTOS(NOME, DESCRICAO, PRECO) VALUES(?,?,?)"; 
	private static final String UPDATE_PRODUTOS = "UPDATE PRODUTOS SET NOME=?, DESCRICAO=?, PRECO=? WHERE ID=?";
	private static final String DELETE_PRODUTOS = "DELETE FROM PRODUTOS WHERE ID=?";
	
	public List<Produto> buscarTodos() {
		List<Produto> lista = new ArrayList<>();
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(SELECT_ALL);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				lista.add(carregaDadosProduto(resultSet));
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao buscar lista de produtos");
		}
		return lista;
	}
	
	public Produto buscarProdutoPorNome(String nome) {
		Produto produto = null;
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(SELECT_PRODUTOS_BY_NOME);
			stmt.setString(1, nome);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				produto = carregaDadosProduto(resultSet);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao buscar produto com nome "+nome);
		}
		return produto;		
		
	}
	
	public Produto buscarProdutoPorIdl(Integer id) {
		Produto produto = null;
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(SELECT_PRODUTOS_BY_ID);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				produto = carregaDadosProduto(resultSet);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao buscar usuario com id "+id);
		}
		return produto;		
		
	}
	
	public void remover(Integer id) {
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(DELETE_PRODUTOS);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao remover produto ");
		}					
	}
	
	public void salvarProduto(Produto produto) {
		if (produto != null) {
			if (produto.getId() != null) {
				atualizar(produto);
			}else {
				inserir(produto);
			}
		}
	}
	
	private void atualizar(Produto produto) {
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(UPDATE_PRODUTOS);
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getId());
			stmt.executeUpdate();
			stmt.close();			
		} catch (SQLException e) {
			System.err.println("Falha ao atualizar produto ");
		}			
	}

	
	private void inserir(Produto produto) {
		try {
			Connection conexao = BancoUtils.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(INSERT_PRODUTOS);
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPreco());
			stmt.execute();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				produto.setId(resultSet.getInt(1));
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Falha ao inserir produto ");
		}			
		
	}


	private Produto carregaDadosProduto(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("ID");
		String nome = resultSet.getString("NOME");	
		String descricao = resultSet.getString("DESCRICAO");
		Double preco = resultSet.getDouble("PRECO");
		return new Produto(id, nome, descricao, preco);
	}
	
}	
