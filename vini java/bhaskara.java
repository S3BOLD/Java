import java.util.Scanner;
import java.lang.Math;
public class bhaskara {
    public static void main(String[] args) {
        System.out.println("Faça o calculo de bhaskara");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Digite o primeiro valor:");
            double valor1 = scanner.nextDouble();
            System.out.println("Digite o segundo valor: ");
            double valor2 = scanner.nextDouble();
            System.out.println("Digite o Terceiro valor: ");
            double valor3 = scanner.nextDouble();

            double delta = (valor2 * valor2) - 4 * valor1 * valor3;

            if (delta < 0) {
                System.out.println("A equação não possui raízes reais.");
            }
                else{
                double x1 = (-valor2 + Math.sqrt(delta)) / (2 * valor1);
                double x2 = (-valor2 - Math.sqrt(delta)) / (2 * valor1);

                System.out.println("Raiz x1: " + x1);
                System.out.println("Raiz x2: " + x2);
                }
            
        } catch (Exception e) {
            System.out.println("Digite apenas valores numéricos!!!");
        } finally {
            scanner.close();
        }
    }
}
