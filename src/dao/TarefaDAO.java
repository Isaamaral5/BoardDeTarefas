package dao;

import model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {
    /**
     * Adiciona uma nova tarefa ao banco de dados.
     * @param tarefa O objetivo Tarefa a ser adicionado. O ID será gerado pelo BD.
     */
    @Override
    public void adicionarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas(titulo, concluida) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection(); // Obtém a conexão com o banco de dados
            if (conn == null) {
                System.err.println("Erro: Não foi possível obter a conexão com o banco de dados.");
                return;
            }
            pstmt = conn.prepareStatement(sql); // Prepara a instrução SQL
            pstmt.setString(1, tarefa.getTitulo()); // Define o título da tarefa
            pstmt.setBoolean(2, tarefa.isConcluida()); // Define se a tarefa está concluída

            int affectedRows = pstmt.executeUpdate(); // Executa a atualização no banco de dados
            if (affectedRows > 0) {
                System.out.println("Tarefa adicionada com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa foi adicionada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar tarefa: " + e.getMessage());
        } finally {
            // Garante que o PreparedStatement e a Connection sejam fechados
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn); // Usa o método de fechamento da conexão
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    /**
     * Lista todas as tarefas presente no banco de dados.
     * @return Uma lista de objetos Tarefa.
     */
    @Override
    public List<Tarefa> listarTodasTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT id, titulo, concluida FROM tarefas ORDER BY id ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.err.println("Erro: Não foi possível obter a conexão com o banco de dados.");
                return tarefas; // Retorna uma lista vazia se a conexão falhar
            }

            pstmt = conn.prepareStatement(sql); // Prepara a instrução SQL
            rs = pstmt.executeQuery(); // Executa a consulta e obtém o ResultSet

            while (rs.next()) { //Itera sobre cada linha do resultado
                int id = rs.getInt("id"); // Obtém o ID da tarefa
                String titulo = rs.getString("titulo"); // Obtém o título da tarefa
                boolean concluida = rs.getBoolean("concluida"); // Obtém o status de conclusão da tarefa
                tarefas.add(new Tarefa(id, titulo, concluida)); // Cria um novo objeto Tarefa e adiciona à lista
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todas as tarefas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn); // Usa o método de fechamento da conexão
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return tarefas; // Retorna a lista de tarefas
    }

    /**
     * Lista as tarefas que estão marcadas como concluídas.
     * @return Uma lista de objetos Tarefa que estão concluídas.
     */
    @Override
    public List<Tarefa> listarTarefasConcluidas() {
        List<Tarefa> tarefasConcluidas = new ArrayList<>();
        String sql = "SELECT id, titulo, concluida FROM tarefas WHERE concluida = 1 ORDER BY id ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.err.println("Erro: Não foi possível obter a conexão com o banco de dados.");
                return tarefasConcluidas; // Retorna uma lista vazia se a conexão falhar
            }

            pstmt = conn.prepareStatement(sql); // Prepara a instrução SQL
            rs = pstmt.executeQuery(); // Executa a consulta e obtém o ResultSet

            while (rs.next()) { // Itera sobre cada linha do resultado
                int id = rs.getInt("id"); // Obtém o ID da tarefa
                String titulo = rs.getString("titulo"); // Obtém o título da tarefa
                boolean concluida = rs.getBoolean("concluida"); // Obtém o status de conclusão da tarefa
                tarefasConcluidas.add(new Tarefa(id, titulo, concluida)); // Cria um novo objeto Tarefa e adiciona à lista
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas concluídas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn); // Usa o método de fechamento da conexão
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return tarefasConcluidas; 
    }

    /**
     * Lista as tarefas que estão pendentes (não concluídas).
     * @return Uma lista de objetos Tarefa que estão pendentes.
     */

    @Override
    public List<Tarefa> listarTarefasPendentes() {
        List<Tarefa> tarefasPendentes = new ArrayList<>();
        String sql = "SELECT id, titulo, concluida FROM tarefas WHERE concluida = 0 ORDER BY id ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.err.println("Erro: Não foi possível obter a conexão com o banco de dados.");
                return tarefasPendentes; // Retorna uma lista vazia se a conexão falhar
            }

            pstmt = conn.prepareStatement(sql); // Prepara a instrução SQL
            rs = pstmt.executeQuery(); // Executa a consulta e obtém o ResultSet

            while (rs.next()) { // Itera sobre cada linha do resultado
                int id = rs.getInt("id"); // Obtém o ID da tarefa
                String titulo = rs.getString("titulo"); // Obtém o título da tarefa
                boolean concluida = rs.getBoolean("concluida"); // Obtém o status de conclusão da tarefa
                tarefasPendentes.add(new Tarefa(id, titulo, concluida)); // Cria um novo objeto Tarefa e adiciona à lista
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas pendentes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn); // Usa o método de fechamento da conexão
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return tarefasPendentes;
    }

    /**
     * Atualiza uma tarefa existente no banco de dados.
     * @param tarefa O objeto Tarefa com os dados atualizados (o ID é usado para encontrar a tarefa).
     */
    @Override
    public void atualizarTarefa(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET titulo = ?, concluida = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.err.println("Erro: Não foi possível obter a conexão com o banco de dados.");
                return;
            }

            pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, tarefa.getTitulo()); 
            pstmt.setBoolean(2, tarefa.isConcluida()); 
            pstmt.setInt(3, tarefa.getId()); // O ID é usado na cláusula WHERE para identificar a tarefa

            int affectedRows = pstmt.executeUpdate(); // Executa a atualização no banco de dados
            if (affectedRows > 0) {
                System.out.println("Tarefa atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa foi atualizada. Verifique se o ID está correto ou se os dados estão iguais.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tarefa: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn); 
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    /**
     * Exclui uma tarefa do banco de dados pelo seu ID.
     * @param id O ID da tarefa a ser excluída.
     */
    @Override
    public void excluirTarefa (int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.err.println("Erro: Não foi possível obter a conexão com o banco de dados.");
                return;
            }

            pstmt = conn.prepareStatement(sql); 
            pstmt.setInt(1, id); 

            int affectedRows = pstmt.executeUpdate(); // Executa a exclusão no banco de dados
            if (affectedRows > 0) {
                System.out.println("Tarefa excluída com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa foi excluída. ID não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir tarefa: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) DatabaseConnection.closeConnection(conn); 
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}