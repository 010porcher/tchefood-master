package br.com.senac.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql {
    private String nomeServidor = "127.0.0.1";
    private static String usuario = "root";
    private static String senha = "senac";
    private String nomeBancoDados = "TcheFood";

    public static Connection obterConexao() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/TcheFood";

        Connection connection;

        connection = DriverManager.getConnection(url, usuario, senha);
        return connection;
    }
}
