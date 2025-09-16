public class Lapide {
    String nome;
    String dataNascimento;
    String dataFalecimento;
    String foto;
    int status;
    String epitafio;

    static int largura = 50;
    static int altura = 30;
    static int profundidade = 10;

    public Lapide(String nome, String dataNascimento, String dataFaleciomento, String foto, int status, String epitafio){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataFalecimento = dataFaleciomento;
        this.foto = foto;
        this.status = status;
        this.epitafio = epitafio;
    }



}
