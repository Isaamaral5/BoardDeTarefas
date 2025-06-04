package dao;

import model.Tarefa; // Importa a classe Tarefa
import java.util.List; // Importa a interface List para a lista de tarefas

public interface ITarefaDAO {
    // Método para adicionar uma nova tarefa ao banco de dados
    void adicionarTarefa(Tarefa tarefa);

    // Método para listar todas as tarefas 
    List<Tarefa> listarTodasTarefas();

    // Método para listar tarefas concluídas
    List<Tarefa> listarTarefasConcluidas();

    // Método para listar tarefas pendentes
    List<Tarefa> listarTarefasPendentes();

    // Método para atualizar uma tarefa existente
    void atualizarTarefa(Tarefa tarefa);

    // Método para excluir uma tarefa pelo seu ID
    void excluirTarefa(int id);
} 