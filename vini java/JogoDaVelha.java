import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char a = ' '; // 0,0
        char b = ' '; // 0,1
        char c = ' '; // 0,2
        char d = ' '; // 1,0
        char e = ' '; // 1,1
        char f = ' '; // 1,2
        char g = ' '; // 2,0
        char h = ' '; // 2,1
        char i = ' '; // 2,2
        char vitoria = ' ';

        for (int count = 1; count <= 9; count++) {
            System.out.println("Jogada " + count);
            System.out.println(count % 2 == 0 ? "Jogue 'O'" : "Jogue 'X'");
            System.out.println(a + "|" + b + "|" + c);
            System.out.println("-|-|-");
            System.out.println(d + "|" + e + "|" + f);
            System.out.println("-|-|-");
            System.out.println(g + "|" + h + "|" + i);
            boolean valorOk = false;
            do {
                System.out.println("Informe a linha: ");
                int linha = scanner.nextInt();
                System.out.println("Informe a coluna");
                int coluna = scanner.nextInt();

                if (linha > 2 || coluna > 2) {
                    System.out.println("Linha ou coluna inválida, por favor informe novamente.");
                    continue;
                } else if (
                    (linha == 0 && coluna == 0 && a != ' ')
                    || (linha == 0 && coluna == 1 && b != ' ')
                    || (linha == 0 && coluna == 2 && c != ' ')
                    || (linha == 1 && coluna == 0 && d != ' ')
                    || (linha == 1 && coluna == 1 && e != ' ')
                    || (linha == 1 && coluna == 2 && f != ' ')
                    || (linha == 2 && coluna == 0 && g != ' ')
                    || (linha == 2 && coluna == 1 && h != ' ')
                    || (linha == 2 && coluna == 2 && i != ' ')
                 ) {
                    System.out.println("Informação já preenchida, escolha outra.");
                    continue;
                }

                valorOk = true;

                if (linha == 0 && coluna == 0) {
                    a = count % 2 == 0 ? 'O' : 'X';
                }
                else if (linha == 0 && coluna == 1 ) {
                    b = count % 2 == 0 ? 'O' : 'X';
                }
                else if (linha == 0 && coluna == 2 ) {
                    c = count % 2 == 0 ? 'O' : 'X';
                }
                else if (linha == 1 && coluna == 0 ) {
                    d = count % 2 == 0 ? 'O' : 'X';
                }
                else if (linha == 1 && coluna == 1 ) {
                    e = count % 2 == 0 ? 'O' : 'X';
                }
                else if (linha == 1 && coluna == 2 ) {
                    f = count % 2 == 0 ? 'O' : 'X';
                }
                else if (linha == 2 && coluna == 0 ) {
                    g = count % 2 == 0 ? 'O' : 'X';
                }
                else if (linha == 2 && coluna == 1 ) {
                    h = count % 2 == 0 ? 'O' : 'X';
                }
                else if (linha == 2 && coluna == 2 ) {
                    i = count % 2 == 0 ? 'O' : 'X';
                }
            } while (!valorOk);
            
            // Verificar a vitoria ou não vitória
            if (a == b && b == c && a != ' ' && b != ' ' && c != ' ') {
                vitoria = a;
                break;
            } else if (a == d && d == g && a != ' ' && d != ' ' && g != ' ') {
                vitoria = a;
                break;
            } else if (a == e && e == i && a != ' ' && e != ' ' && i != ' ') {
                vitoria = a;
                break;
            } else if (c == e && e == g && c != ' ' && e != ' ' && g != ' ') {
                vitoria = c;
                break;
            } else if (d == e && e == f && d != ' ' && e != ' ' && f != ' ') {
                vitoria = d;
                break;
            } else if (g == h && h == i && g != ' ' && h != ' ' && h != ' ') {
                vitoria = g;
                break;
            } else if (b == e && e == h && b != ' ' && e != ' ' && h != ' ') {
                vitoria = b;
                break;
            } else if (c == f && f == i && c != ' ' && f != ' ' && i != ' ') {
                vitoria = c;
                break;
            }
        }

        System.out.println(a + "|" + b + "|" + c);
        System.out.println("-|-|-");
        System.out.println(d + "|" + e + "|" + f);
        System.out.println("-|-|-");
        System.out.println(g + "|" + h + "|" + i);

        if (vitoria != ' ') {
            System.out.println("Ganharam as '" + vitoria + "'!");
        } else {
            System.out.println("Deu velha!");
        }

        scanner.close();
    }
}
