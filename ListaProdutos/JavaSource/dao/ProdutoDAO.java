package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Contato;
import model.ListaCompras;
import model.Produto;

public class ProdutoDAO {


	
    private static final String SQL_INSERIR_PRODUTO = "insert into contatos (nome, email, endereco) values(?,?,?)";
    private static final String SQL_LISTAR_PRODUTO = "SELECT produto.id, produto.pego, produto.nome as nomeProduto, produto.marca as marcaProduto, produto.quantidade, produto.descricao, produto.valorProduto, produto.dataProduto, produto.mercado FROM unisantos.compras_produtos join unisantos.produto on compras_produtos.idProduto = produto.id where compras_produtos.idCompra = ? order by nomeProduto";
    private static final String SQL_CONSULTAR_PRODUTO = "select * from contatos where nome like ? order by nome";
    private static final String SQL_EXCLUIR_PRODUTO = "delete from contatos where id = ?";
    private static final String SQL_ALTERAR_PRODUTO = "update contatos set nome=?, email=?, endereco=? where id=?";

    private Connection connection;

    public void adicionar(Produto produto) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_PRODUTO);
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

    public List<Produto> listar(ListaCompras compras) throws SQLException {
        List<Produto> listaProduto = new ArrayList<Produto>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_PRODUTO);
                stmt.setInt(1, Integer.parseInt(compras.getIdListaCompras()));
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                	Produto c = new Produto();
                    c.setId(String.valueOf(rs.getInt("id")));
                    c.setPego(rs.getString("pego"));
                    c.setNome(rs.getString("nomeProduto"));
                    c.setMarca(rs.getString("marcaProduto"));
                    c.setQuantidade(String.valueOf(rs.getInt("quantidade")));
                    c.setDescricao(rs.getString("descricao"));
                    c.setValorProduto(String.valueOf(rs.getDouble("valorProduto")));
                    c.setDataProduto(rs.getString("dataProduto"));
                    c.setMercado(rs.getString("mercado"));
                    
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
    
    public List<Produto> consultar(String nome) throws SQLException {
        List<Produto> produto = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_PRODUTO);
                stmt.setString(1, '%' + nome + '%');
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
//                    Contato c = new Contato();
//                    c.setId(rs.getLong("id"));
//                    c.setNome(rs.getString("nome"));
//                    c.setEmail(rs.getString("email"));
//                    c.setEndereco(rs.getString("endereco"));
//                    contatos.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return produto;
    }
    
    public void excluir(Long id) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_PRODUTO);
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
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_PRODUTO);
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
