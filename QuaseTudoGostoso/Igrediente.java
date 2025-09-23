public class Igrediente {
    private String nome;
    private String quantidade;
    
    public Igrediente(String nome , String quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return quantidade + " de" + nome;
    }
}
