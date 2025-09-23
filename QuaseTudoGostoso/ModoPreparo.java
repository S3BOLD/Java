public class ModoPreparo {
    private String etapa;

    public ModoPreparo(String etapa) {
        this.etapa = etapa;
    }

    public String getEtapa() {
        return etapa;
    }

    @Override
    public String toString() {
        return "- " + etapa;
    }
    
}
