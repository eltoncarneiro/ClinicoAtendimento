package br.com.clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.clinica.domain.Usuario;
import br.com.clinica.factory.ConnectionFactory;

public class UsuarioDAO {
	public void salvar(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_USUARIO");
		sql.append("(usuario,senha)");
		sql.append("VALUES(?,?)");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, u.getUsuario());
		comando.setString(2, u.getSenha());

		comando.executeUpdate();
	}

	public void excluir(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE INTO TB_USUARIO");
		sql.append("WHERE ID_USUARIO = ?");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, u.getId());

		comando.executeUpdate();
	}

	public void editar(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE INTO TB_USUARIO");
		sql.append("SET usuario = ?,senha = ?");
		sql.append("WHERE ID_USUARIO = ?");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, u.getUsuario());
		comando.setString(2, u.getSenha());
		comando.setLong(3, u.getId());

		comando.executeUpdate();
	}

	public Usuario buscarPorCodigo(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_USUARIO,USUARIO,SENHA");
		sql.append("FROM TB_USUARIO");
		sql.append("WHERE ID_USUARIO = ?");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, u.getId());

		ResultSet resultadoUsuario = comando.executeQuery();

		Usuario retorno = null;

		if (resultadoUsuario.next()) {
			retorno = new Usuario();
			retorno.setId(resultadoUsuario.getLong("id"));
			retorno.setUsuario(resultadoUsuario.getString("usuario"));
			retorno.setSenha(resultadoUsuario.getString("senha"));
		}
		return retorno;
	}

	public ArrayList<Usuario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_STATUS,USUARIO,SENHA");
		sql.append("FROM TB_USUARIO");
		sql.append("ORDER BY USUARIO DESC");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultadoUsuario = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultadoUsuario.next()) {
			Usuario u = new Usuario();
			u.setId(resultadoUsuario.getLong("id"));
			u.setUsuario(resultadoUsuario.getString("usuario"));
			u.setSenha(resultadoUsuario.getString("senha"));

			lista.add(u);
		}
		return lista;
	}

	public ArrayList<Usuario> buscaPorUsuario(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECTUSUARIO,SENHA");
		sql.append("FROM TB_USUARIO");
		sql.append("WHERE USUARIO LIKE ?");
		sql.append("ORDER BY USUARIO DESC");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + u.getUsuario() + "%");

		ResultSet resultadoUsuario = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultadoUsuario.next()) {
			Usuario item = new Usuario();
			item.setId(resultadoUsuario.getLong("id"));
			item.setUsuario(resultadoUsuario.getString("usuario"));
			item.setSenha(resultadoUsuario.getString("senha"));

			lista.add(item);
		}
		return lista;
	}
}
