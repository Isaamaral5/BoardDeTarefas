package view;

import service.TarefaService;
import model.Tarefa;

import java.util.List;
import java.util.Scanner; // Para ler a entrada do usuário

public class App {
    private static TarefaService tarefaService = new TarefaService(); // Instancia o serviço de tarefas
    private static Scanner scanner = new Scanner(System.in); // Scanner para ler a entrada do usuário

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Gerenciador de Tarefas Pessoal!");
        exibirMenuPrincipal();
    }

    //Exibe o menu principal e gerencia as opções do usuário
    private static void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Criar Nova Tarefa");
            System.out.println("2. Listar Todas as Tarefas");
            System.out.println("3. Listar Tarefas Concluídas");
            System.out.println("4. Listar Tarefas Pendentes");
            System.out.println("5. Marcar Tarefa como Concluída");
            System.out.println("6. Marcar Tarefa como Pendente");
            System.out.println("7. Editar Tarefa (titulo)");
            System.out.println("8. Excluir Tarefa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    criarTarefa();
                    break;

                case 2:
                    listarTodasTarefas();
                    break;
                case 3:
                    listarTarefasConcluidas();
                    break;
                case 4:
                    listarTarefasPendentes();
                    break;
                case 5:
                    marcarTarefaConcluida();
                    break;
                case 6:
                    marcarTarefaPendente();
                    break;
                case 7:
                    editarTarefa();
                    break;
                case 8:
                    excluirTarefa();
                    break;
                case 0:
                    System.out.println("Saindo do Gerenciador de Tarefas. Até logo!");
                    break;    
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 0);
        scanner.close(); // Fecha o scanner ao sair do programa
        }

        // =======================================
        // Métodos para cada funcionalidade
        // =======================================

         private static void criarTarefa() {
        System.out.print("Digite o título da nova tarefa: ");
        scanner.nextLine(); // Consome a quebra de linha pendente após ler int
        String titulo = scanner.nextLine();
        tarefaService.criarTarefa(titulo);
    }

    private static void listarTodasTarefas() {
        System.out.println("\n--- Todas as Tarefas ---");
        List<Tarefa> tarefas = tarefaService.listarTodasTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa); // O método toString() da Tarefa será usado aqui
            }
        }
    }

    private static void listarTarefasConcluidas() {
        System.out.println("\n--- Tarefas Concluídas ---");
        List<Tarefa> tarefas = tarefaService.listarTarefasConcluidas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa concluída.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    private static void listarTarefasPendentes() {
        System.out.println("\n--- Tarefas Pendentes ---");
        List<Tarefa> tarefas = tarefaService.listarTarefasPendentes();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa pendente.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    private static void marcarTarefaConcluida() {
        System.out.print("Digite o ID da tarefa para marcar como CONCLUÍDA: ");
        int id = lerOpcaoMenu(); // Reutiliza a função de leitura de ID
        tarefaService.marcarTarefaConcluida(id);
    }

    private static void marcarTarefaPendente() {
        System.out.print("Digite o ID da tarefa para marcar como PENDENTE: ");
        int id = lerOpcaoMenu();
        tarefaService.marcarTarefaPendente(id);
    }

    private static void editarTarefa() {
        System.out.print("Digite o ID da tarefa para editar: ");
        int id = lerOpcaoMenu();

        // Opcional: buscar a tarefa para exibir o título atual e status
        // Para simplificar, vamos direto à edição com um novo título e status
        System.out.print("Digite o NOVO título da tarefa (deixe em branco para manter o atual): ");
        scanner.nextLine(); // Consome a quebra de linha pendente
        String novoTitulo = scanner.nextLine();

        System.out.print("A tarefa está CONCLUÍDA? (sim/não): ");
        String statusStr = scanner.nextLine().trim().toLowerCase();
        boolean novoStatusConcluida = statusStr.equals("sim");

        tarefaService.atualizarTarefa(id, novoTitulo, novoStatusConcluida);
    }

    private static void excluirTarefa() {
        System.out.print("Digite o ID da tarefa para EXCLUIR: ");
        int id = lerOpcaoMenu();
        tarefaService.excluirTarefa(id);
    }

    // ===================================
    // Métodos Auxiliares
    // ===================================

    // Lê a opção do menu e trata entradas inválidas
    private static int lerOpcaoMenu() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next(); // Descarta a entrada inválida
            System.out.print("Escolha uma opção: ");
        }
        int opcao = scanner.nextInt();
        return opcao;
    }
}