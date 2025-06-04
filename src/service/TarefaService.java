package service;

import dao.ITarefaDAO;
import dao.TarefaDAO; // Usamos a implementação concreta do DAO
import model.Tarefa; // Importa a classe Tarefa

import java.util.List; // Importa a interface List para a lista de tarefas

public class TarefaService {
    private ITarefaDAO tarefaDAO; // Usamos a interface para maior flexibilidade

    // Construtor que recebe a instancia de ITarrefaDAO
    // Isso é um exemplo de Injeção de Dependência, tornando o código mais flexível e testável
    public TarefaService(ITarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO;
    }

    // Sobrecarga do construtor para facilitar o uso no Main, usando a implementação padrão
    public TarefaService() {
        this.tarefaDAO = new TarefaDAO();
    }

    /**
     * Valida e adiciona uma nova tarefa.
     * @param titulo O título da tarefa a ser adicionada.
     */
    public void criarTarefa(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Erro: O título da tarefa não pode ser vazio.");
            return;
        }
        Tarefa novaTarefa = new Tarefa(titulo); // O ID e status 'concluida' são definidos no construtor da Tarefa
        tarefaDAO.adicionarTarefa(novaTarefa);
    }

    /**
     * Obtém todas as tarefas.
     * @return Uma lista de todas as tarefas.
     */
    public List<Tarefa> listarTodasTarefas() {
        return tarefaDAO.listarTodasTarefas();
    }

    /**
     * Obtém apenas as tarefas concluídas.
     * @return Uma lista de tarefas concluídas.
     */
    public List<Tarefa> listarTarefasConcluidas() {
        return tarefaDAO.listarTarefasConcluidas();
    }

    /**
     * Obtém apenas as tarefas pendentes.
     * @return Uma lista de tarefas pendentes.
     */
    public List<Tarefa> listarTarefasPendentes() {
        return tarefaDAO.listarTarefasPendentes();
    }

    /**
     * Atualiza uma tarefa existente. Permite mudar título e/ou status.
     * @param id O ID da tarefa a ser atualizada.
     * @param novoTitulo O novo título da tarefa (pode ser o mesmo para não mudar).
     * @param novoStatusConcluida O novo status (true para concluída, false para pendente).
     */
    public void atualizarTarefa(int id, String novoTitulo, boolean novoStatusConcluida) {
        // Poderíamos buscar a tarefa primeiro para validar se ela existe
        // Fora do escopo para este projeto, mas uma boa prática
        Tarefa tarefaParaAtualizar = new Tarefa(id, novoTitulo, novoStatusConcluida);
        tarefaDAO.atualizarTarefa(tarefaParaAtualizar);
    }

    /**
     * Marca uma tarefa como concluída.
     * @param id O ID da tarefa a ser marcada como concluída.
     */
    public void marcarTarefaConcluida(int id) {
        // Idealmente, buscaríamos a tarefa por ID primeiro para obter o título
        // Para simplificar, estamos criando uma Tarefa com um título genérico.
        // O importante é que o ID e o status 'true' serão usados na atualização.
        Tarefa tarefaConcluida = new Tarefa(id, "Titulo não usado", true); // Título não importa para a atualização de status
        tarefaDAO.atualizarTarefa(tarefaConcluida);
    }

    /**
     * Marca uma tarefa como pendente.
     * @param id O ID da tarefa a ser marcada como pendente.
     */
    public void marcarTarefaPendente(int id) {
        Tarefa tarefaPendente = new Tarefa(id, "Titulo não usado", false); // Título não importa para a atualização de status
        tarefaDAO.atualizarTarefa(tarefaPendente);
    }

    /**
     * Exclui uma tarefa pelo seu ID.
     * @param id O ID da tarefa a ser excluída.
     */
    public void excluirTarefa(int id) {
        if (id <= 0) {
            System.out.println("Erro: ID de tarefa inválido para exclusão.");
            return;
        }
        tarefaDAO.excluirTarefa(id);
    }
}
