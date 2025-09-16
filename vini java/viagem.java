
import java.util.Scanner;

public class viagem {
    public static void main(String[] args) {
        System.out.println("Calcule a velocidade média de uma viagem");
        Scanner scanner = new Scanner(System.in);

        try{
        System.out.println("Digite a distancia em km: ");
        double km = scanner.nextDouble();
        System.out.println("Digite o tempo de viagem: ");
        double tempo = scanner.nextDouble();

        double velocidadeM = km / tempo;

        System.out.println("A velocidade média de uma viagem é: "+ velocidadeM);
        }catch(Exception e){
            System.out.println("Digite apenas valores numéricpos!!!");
        }finally{
            scanner.close();
        }
    }
}
