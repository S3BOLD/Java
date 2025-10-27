
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDeReceitas {

    private List<Receita> receitas = new ArrayList<>();

   
    public void adicionarReceita(Scanner scanner) {
        System.out.print("\nQual o nome da receita? ");
        String nomeReceita = scanner.nextLine();
        
        Receita novaReceita = new Receita(nomeReceita);

        System.out.println("Adicione os ingredientes (digite 'fim' no nome para parar):");
        while (true) {
            System.out.print("Nome do ingrediente: ");
            String nomeIngrediente = scanner.nextLine();
            if (nomeIngrediente.equalsIgnoreCase("fim")) {
                break;
            }
            System.out.print("Quantidade (ex: 2 xícaras, 100g): ");
            String qtdIngrediente = scanner.nextLine();
            
            novaReceita.adicionarIngrediente(new Ingrediente(nomeIngrediente, qtdIngrediente));
        }

        System.out.println("\nAdicione o modo de preparo (digite 'fim' para parar):");
        int passo = 1;
        while (true) {
            System.out.print("Passo " + passo + ": ");
            String etapa = scanner.nextLine();
            if (etapa.equalsIgnoreCase("fim")) {
                break;
            }
            novaReceita.adicionarModoPreparo(new ModoPreparo(etapa));
            passo++;
        }

        this.receitas.add(novaReceita);
        System.out.println("\nReceita '" + nomeReceita + "' adicionada com sucesso!");
    }

    // novo método para API: adicionar receita já construída
    public void adicionarReceita(Receita receita) {
        this.receitas.add(receita);
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public Receita getUltimaReceita() {
        if (receitas.isEmpty()) return null;
        return receitas.get(receitas.size() - 1);
    }

    public void listarReceitas(Scanner scanner) {
        System.out.println("\n=== Lista de Receitas Cadastradas ===");
        if (receitas.isEmpty()) {
            System.out.println("Nenhuma receita cadastrada.");
            return; 
        }
        
        for (int i = 0; i < receitas.size(); i++) {
            System.out.println((i + 1) + ". " + receitas.get(i).getNome());
        }

        System.out.print("\nDigite o número da receita para ver os detalhes (ou 0 para voltar): ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= receitas.size()) {
            Receita receitaSelecionada = receitas.get(escolha - 1);
            receitaSelecionada.mostrarDetalhes();
        }
    }
}