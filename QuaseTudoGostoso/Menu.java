
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        GerenciadorDeReceitas gerenciadorDeReceitas = new GerenciadorDeReceitas();

        do {
            System.out.println("\n==== Menu ====");
            System.out.println("1. Adicionar Receita");
            System.out.println("2. Listar Receitas");
            System.out.println("3. Fechar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    gerenciadorDeReceitas.adicionarReceita(scanner);
                    // após adicionar localmente, enviar ao ApiServer via HTTP POST
                    try {
                        Receita ultima = gerenciadorDeReceitas.getUltimaReceita();
                        if (ultima != null) {
                            String json = ultima.toJson();
                            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
                            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                                    .uri(new java.net.URI("http://localhost:8089/receitas"))
                                    .header("Content-Type", "application/json")
                                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(json))
                                    .build();
                            java.net.http.HttpResponse<String> resp = client.send(request,
                                    java.net.http.HttpResponse.BodyHandlers.ofString());
                            System.out.println("Servidor resposta: " + resp.statusCode());
                        }
                    } catch (Exception e) {
                        System.out.println("Aviso: não foi possível enviar para o servidor: " + e.getMessage());
                    }
                    voltarMenu(scanner);
                    break;

                case 2:
                    gerenciadorDeReceitas.listarReceitas(scanner);
                    voltarMenu(scanner);
                    break;

                case 3:
                    System.out.println("\nEncerrando...");
                    break;

                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    voltarMenu(scanner);
                    break;
            }

        } while (opcao != 3);

        scanner.close();
    }

    public static void voltarMenu(Scanner scanner) {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }
}