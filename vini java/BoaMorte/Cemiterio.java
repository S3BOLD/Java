package BoaMorte;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Cemiterio {
    public static void main(String[] args) {
        SortedSet<Lapide> lapides = new TreeSet<>();
        Rua r1 = new Rua("Rua da Boa Morte", "RBM-01", 100);
        Lapide a = new Lapide("João Silva", "01/01/1950", "15/03/2020", "foto1.jpg", 1, "Descansa em paz", r1);
        lapides.add(a);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do falecido: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a data de nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Digite a data de falecimento (dd/mm/aaaa): ");
        String dataFalecimento = scanner.nextLine();
        System.out.print("Digite o nome do arquivo da foto: ");
        String foto = scanner.nextLine();
        System.out.print("Digite o status (1 para ativo, 0 para inativo):");
        int status = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o epitáfio: ");
        String epitafio = scanner.nextLine();

        Lapide b = new Lapide(nome, dataNascimento, dataFalecimento, foto, status, epitafio, r1);
        lapides.add(b);
        
        for (Lapide lapide : lapides) {
            System.out.println("Informações da Lápide");
            System.out.println("Nome: " + lapide.nome);
            System.out.println("Data de Nascimento: " + lapide.dataNascimento);
            System.out.println("Data de Falecimento: " + lapide.dataFalecimento);
            System.out.println("Foto: " + lapide.foto);
            System.out.println("Status: " + (lapide.status == 1 ? "Ativo" : "Inativo"));
            System.out.println("Epitáfio: " + lapide.epitafio);
        }

        for (Lapide lapide : r1.lapides) {
            System.out.println("Lápide na rua " + r1.nome + ": " + lapide.nome);
        }
        scanner.close();
    }
}
