public class Balao {
    String cor;
    double tamanho;
    String nome;
    int idade;
    int ordem;

    Balao(String cor, double tamanho, String nome, int idade, int ordem) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.nome = nome;
        this.idade = idade;
        this.ordem = ordem;
    }

    void estourar() {
        System.out.println("POP!");
    }
}