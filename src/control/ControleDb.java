package control;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControleDb {
    
    public Statement stm; // Responsavel por preparar e realizar pesquisas no banco de dados;
    public ResultSet rs; // Responsavel por armazenar o resultado de um pesquisa passada para o statement;
    private final String driver = "org.postgresql.Driver";
    private final String caminho = "jdbc:postgresql://localhost:5432/postgres"; // pgbouncer.millenamoveiseeletro.com.br
    private final String usuario = "postgres"; // java
    private final String senha = "andre170190"; // 0nG8i3 Pos!gr%es
    public Connection conexao; // Responsavel por realizar a conexão com o banco de dados;
    
public void conectar() { // Metodo responsavel por realizar a conexão;
    try {
        System.setProperty("jdbc.Drivers", driver); // Seta a propriedade do driver de conexão;
        conexao = DriverManager.getConnection(caminho, usuario, senha); // Realiza a conexão com o banco;
    } catch (SQLException ex) {
        Logger.getLogger(ControleDb.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Erro de conexão!\nERRO: " + ex.getMessage(), "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
    }
}
    
public void desconectar() { // Metodo responsavel por fechar a conexão
    try {
        conexao.close(); // Fechar conexão
    } catch (SQLException ex) {
        Logger.getLogger(ControleDb.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!\nERRO: " + ex.getMessage(), "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
    }
}
}



