package BoaMorte;
public class Lapide implements Comparable<Lapide> {
    String nome;
    String dataNascimento;
    String dataFalecimento;
    String foto;
    int status;
    String epitafio;
    Rua rua;

    static int largura = 50;
    static int altura = 30;
    static int profundidade = 10;

    public Lapide(String nome, String dataNascimento, String dataFalecimento, String foto, int status, String epitafio, Rua rua) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataFalecimento = dataFalecimento;
        this.foto = foto;
        this.status = status;
        this.epitafio = epitafio;
        this.rua = rua;

        rua.lapides.add(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Lapide lapide = (Lapide) obj;
        return nome.equals(lapide.nome);
    }
    
    @Override
    public int compareTo(Lapide other) {
        // Example: Sort by 'nome' in ascending order
        return this.dataNascimento.compareTo(other.dataNascimento);
    }
}
