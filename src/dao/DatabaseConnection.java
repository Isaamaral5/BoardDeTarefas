package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    //URL de conexão com o banco de dados SQLite
    //O banco de dados será criado na raiz do projeto com o nome "board_tarefas.db"
    private static final String URL = "jdbc:sqlite:board_tarefas.db";

    /**
     * Estabelece uma conexão com o banco de dados e cria a tabela "tarefas" se ela não existir.
     * @return Uma instancia de Connection se a conexão for bem-sucedida, ou null se ocorrer um erro.
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Carrega o driver JDBC para SQLite
            Class.forName("org.sqlite.JDBC");
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
            
            // Cria a tabela "tarefas" se ela não existir
            createTable(conn);
} catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados ou criar tabela: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC SQLite não encontrado: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Cria a tabela "tarefas" no banco de dados se ela não existir.
     * @param conn A conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro SQL durante a criação da tabela.
     */
    private static void createTable(Connection conn) throws SQLException {
        // SQL para criar a tabela "tarefas"
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "titulo TEXT NOT NULL," +
                     "concluida BOOLEAN NOT NULL DEFAULT 0," + //0 para falso (pendente), 1 para verdadeiro (concluída)
                     ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'tarefas' verificada/criada com sucesso.");
        }
    }
    /**
     * Fecha a conexão com o banco de dados.
     * @param conn A conexão a ser fechada.
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão com o banco de dados fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
}
}
