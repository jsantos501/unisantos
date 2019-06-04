package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import model.Usuario;


public class UsuarioDAO {


	
    private static final String SQL_INSERIR_USUARIO = "INSERT INTO unisantos.usuario (login, senha, perfil, nome, cpf, email, cep, celular) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_LISTAR_USUARIO = "select * from unisantos.usuario order by login";
    private static final String SQL_CONSULTAR_USUARIO = "select * from unisantos.usuario where login=? and senha=?";
    private static final String SQL_EXCLUIR_USUARIO = "delete from unisantos.usuario where id = ?";
    private static final String SQL_ALTERAR_USUARIO = "UPDATE unisantos.usuario SET login = ?, senha = ?, perfil = ?, nome = ?, cpf = ?, email = ?, cep = ?, celular = ? WHERE `id` = ?";

    private Connection connection;

    public void adicionar(Produto produto) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_LISTAR_USUARIO);
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_USUARIO);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                	Usuario u = new Usuario();
                  	u.setId(String.valueOf(rs.getInt("id")));
                  	u.setLogin(rs.getString("login"));
                  	u.setSenha(rs.getString("senha"));
                  	u.setPerfil(String.valueOf(rs.getInt("perfil")));
                  	u.setNome(rs.getString("nome"));
                  	u.setCpf(String.valueOf(rs.getLong("cpf")));
                  	u.setEmail(rs.getString("email"));
                  	u.setCep(String.valueOf(rs.getInt("cep")));
                  	u.setCelular(String.valueOf(rs.getLong("celular")));
                	listaUsuario.add(u);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return listaUsuario;
    }
    
    public Usuario consultar(String usuario, String senha) throws SQLException {
    	Usuario u = null;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_USUARIO);
                stmt.setString(1, usuario);
                stmt.setString(2, senha);
                
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                	u = new Usuario();
                  	u.setId(String.valueOf(rs.getInt("id")));
                  	u.setLogin(rs.getString("login"));
                  	u.setSenha(rs.getString("senha"));
                  	u.setPerfil(String.valueOf(rs.getInt("perfil")));
                  	u.setNome(rs.getString("nome"));
                  	u.setCpf(String.valueOf(rs.getLong("cpf")));
                  	u.setEmail(rs.getString("email"));
                  	u.setCep(String.valueOf(rs.getInt("cep")));
                  	u.setCelular(String.valueOf(rs.getLong("celular")));
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return u;
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

   
    public void addUsuario(Usuario usuario) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
            	
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_USUARIO);
                stmt.setString(1, usuario.getLogin());
                stmt.setString(2, usuario.getSenha());
                stmt.setInt(3, Integer.parseInt(usuario.getPerfil()));
                stmt.setString(4, usuario.getNome());
                stmt.setLong(5, Long.parseLong(usuario.getCpf()));
                stmt.setString(6, usuario.getEmail());
                stmt.setInt(7, Integer.parseInt(usuario.getCep()));
                stmt.setLong(8, Long.parseLong(usuario.getCelular()));
                
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

	
    public void excluirUsuario(Usuario usuario) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_USUARIO);
                stmt.setInt(1, Integer.parseInt(usuario.getId()));
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    
    public void alterarUsuario(Usuario usuario) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_USUARIO);
                stmt.setString(1, usuario.getLogin());
                stmt.setString(2, usuario.getSenha());
                stmt.setInt(3, Integer.parseInt(usuario.getPerfil()));
                stmt.setString(4, usuario.getNome());
                stmt.setLong(5, Long.parseLong(usuario.getCpf()));
                stmt.setString(6, usuario.getEmail());
                stmt.setLong(7, Long.parseLong(usuario.getCep()));
                stmt.setLong(8, Long.parseLong(usuario.getCelular()));
                stmt.setInt(9, Integer.parseInt(usuario.getId()));
                
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
