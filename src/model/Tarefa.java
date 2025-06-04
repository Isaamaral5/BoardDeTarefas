package model;

public class Tarefa {
    private int id;
    private String titulo;
    private boolean concluida; //true para concluída, false para pendente

    //Construtor para criar novas tarefas (sem ID, pois sera gerado pelo BD)
    public Tarefa(String titulo) {
        this.titulo = titulo;
        this.concluida = false; // Por padrão, a tarefa é criada como pendente
    }

    //Construtor para carregar tarefas do BD (com ID)
    public Tarefa(int id, String titulo, boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.concluida = concluida;
    }

    //Métofos getters (para acessar os valores dos atributos)
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    //Usamos 'is' para booleans por convenção
    public boolean isConcluida() {
        return concluida;
    }

    //Métodos setters (para modificar os valores dos atributos)
    // O ID geralmente não é alterado, então precisamos de um setter para ele.
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    //Método to String para facilitar a visualização da tarefa
    @Override
    public String toString() {
        String status = concluida ? "[CONCLUÍDA]" : "[PENDENTE]";
        return "ID: " + id + " - " + status + " - " + titulo;
    }
}
