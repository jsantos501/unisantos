/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectionFactory;

/**
 *
 * @author ciro
 */
public class ContatoDAO {

    private static final String SQL_INSERIR_CONTATO = "insert into contatos (nome, email, endereco) values(?,?,?)";
    private static final String SQL_LISTAR_CONTATOS = "select * from contatos order by nome";
    private static final String SQL_CONSULTAR_CONTATO = "select * from contatos where nome like ? order by nome";
    private static final String SQL_EXCLUIR_CONTATO = "delete from contatos where id = ?";
    private static final String SQL_ALTERAR_CONTATO = "update contatos set nome=?, email=?, endereco=? where id=?";

    private Connection connection;

    public void adicionar(Contato contato) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_CONTATO);
                stmt.setString(1, contato.getNome());
                stmt.setString(2, contato.getEmail());
                stmt.setString(3, contato.getEndereco());
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<Contato> listar() throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_CONTATOS);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Contato c = new Contato();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setEmail(rs.getString("email"));
                    c.setEndereco(rs.getString("endereco"));
                    contatos.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return contatos;
    }
    
    public List<Contato> consultar(String nome) throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_CONTATO);
                stmt.setString(1, '%' + nome + '%');
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Contato c = new Contato();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setEmail(rs.getString("email"));
                    c.setEndereco(rs.getString("endereco"));
                    contatos.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return contatos;
    }
    
    public void excluir(Long id) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_CONTATO);
                stmt.setLong(1, id);
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void alterar(Contato contato) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                System.out.println(contato);
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_CONTATO);
                stmt.setString(1, contato.getNome());
                stmt.setString(2, contato.getEmail());
                stmt.setString(3, contato.getEndereco());
                stmt.setLong(4, contato.getId());
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
}
