import java.util.Scanner;

public class Media {
    public static void main(String[] args) {
        System.out.println("Calcula a media");
        Scanner scanner = new Scanner(System.in);
        try {

            System.out.println("Digite a Primeira nota: ");
            double nota1 = scanner.nextDouble();
            System.out.println("Digite a Segunda nota: ");
            double nota2 = scanner.nextDouble();
            System.out.println("Digite a Terceira nota: ");
            double nota3 = scanner.nextDouble();

            double media = (nota1 + nota2 + nota3) / 3;

            System.out.println("A média é: " + media);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}