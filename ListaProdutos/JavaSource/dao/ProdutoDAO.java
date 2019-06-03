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


	
    private static final String SQL_INSERIR_PRODUTO = "INSERT INTO unisantos.produto (pego, nome, marca, quantidade, descricao, valorProduto, dataProduto, mercado) VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_LISTAR_PRODUTO = "SELECT produto.id, produto.pego, produto.nome as nomeProduto, produto.marca as marcaProduto, produto.quantidade, produto.descricao, produto.valorProduto, produto.dataProduto, produto.mercado FROM unisantos.compras_produtos join unisantos.produto on compras_produtos.idProduto = produto.id where compras_produtos.idCompra = ? order by nomeProduto";
    private static final String SQL_CONSULTAR_PRODUTO = "SELECT * FROM unisantos.produto where produto.pego = ? and produto.nome = ? and produto.marca = ? and produto.quantidade = ? and produto.descricao = ? and produto.valorProduto = ? and produto.dataProduto = ? and produto.mercado = ?";
    
    private static final String SQL_EXCLUIR_PRODUTO = "delete from contatos where id = ?";
    private static final String SQL_ALTERAR_PRODUTO = "update contatos set nome=?, email=?, endereco=? where id=?";

    private Connection connection;

    public void adicionar(Produto produto) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_PRODUTO);
                
                stmt.setString(1, produto.getNome());
                stmt.setString(2, produto.getMarca());
                stmt.setString(3, produto.getQuantidade());
                stmt.setString(4, produto.getDescricao());
                stmt.setString(5, produto.getValorProduto());
                stmt.setString(6, produto.getDataProduto());
                stmt.setString(7, produto.getMercado());
                
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
    
    public Produto buscarProduto(Produto produto) throws SQLException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_PRODUTO);
                
                stmt.setString(1, produto.getNome());
                stmt.setString(2, produto.getMarca());
                stmt.setInt(3, Integer.parseInt(produto.getQuantidade()));
                stmt.setString(4, produto.getDescricao());
                stmt.setDouble(5, Double.parseDouble(produto.getValorProduto()));
                stmt.setString(6, produto.getDataProduto());
                stmt.setString(7, produto.getMercado());
                
                ResultSet rs = stmt.executeQuery();
                if(rs.isFirst()){
	                while (rs.next()) {
	                	produto.setId(String.valueOf(rs.getInt("id")));
	                      }
                }else {
                	produto = null;
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
