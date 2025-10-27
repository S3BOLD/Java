
public class Ingrediente {
    private String nome;
    private String quantidade;

    public Ingrediente(String nome, String quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return quantidade + " de " + nome;
    }

    public String getNome() {
        return nome;
    }

    public String getQuantidade() {
        return quantidade;
    }
}