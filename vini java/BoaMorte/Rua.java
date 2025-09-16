package BoaMorte;

import java.util.ArrayList;

public class Rua {
    String nome;
    String identificacao;
    int numeroLote;

    ArrayList<Lapide> lapides = new ArrayList<>();

    public Rua(String nome, String identificacao, int numeroLote) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.numeroLote = numeroLote;
    }
}