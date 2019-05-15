package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mock.MockDados;
import model.Contato;
import model.ListaCompras;
import model.Produto;
import model.Usuario;

public class UsuarioDAO {


	
    private static final String SQL_INSERIR_USUARIO = "insert into contatos (nome, email, endereco) values(?,?,?)";
    private static final String SQL_LISTAR_USUARIO = "select * from contatos order by nome";
    private static final String SQL_CONSULTAR_USUARIO = "select * from usuarios where user=? and senha=?";
    private static final String SQL_EXCLUIR_USUARIO = "delete from contatos where id = ?";
    private static final String SQL_ALTERAR_USUARIO = "update contatos set nome=?, email=?, endereco=? where id=?";

    private Connection connection;

    public void adicionar(Produto produto) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_USUARIO);
//                stmt.setString(1, produto.getNome());
//                stmt.setString(2, produto.getEmail());
//                stmt.setString(3, produto.getEndereco());
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> listaProduto = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_USUARIO);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                	Produto c = new Produto();
//                    c.setId(rs.getLong("id"));
//                    c.setNome(rs.getString("nome"));
//                    c.setEmail(rs.getString("email"));
//                    c.setEndereco(rs.getString("endereco"));
                    listaProduto.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return listaProduto;
    }
    
    public Usuario consultar(String usuario, String senha) throws SQLException {
    	Usuario user = null;
//        try {
//            connection = ConnectionFactory.getConnection();
//            try {
//                PreparedStatement stmt = connection.
//                        prepareStatement(SQL_CONSULTAR_USUARIO);
//                stmt.setString(1, usuario);
//                stmt.setString(2, senha);
//                
//                ResultSet rs = stmt.executeQuery();
//                while (rs.next()) {
    	user = MockDados.getUsuario();

//                    Contato c = new Contato();
//                    c.setId(rs.getLong("id"));
//                    c.setNome(rs.getString("nome"));
//                    c.setEmail(rs.getString("email"));
//                    c.setEndereco(rs.getString("endereco"));
//                    contatos.add(c);
//                }
//                stmt.close();
//                rs.close();
//            } finally {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            throw e;
//        }
        return user;
    }
    
    public void excluir(Long id) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_USUARIO);
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
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_USUARIO);
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
