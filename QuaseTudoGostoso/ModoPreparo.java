
public class ModoPreparo {
    private String etapa;

    public ModoPreparo(String etapa) {
        this.etapa = etapa;
    }

    public String getEtapa() {
        return etapa;
    }

    public String toJson() {
        return "{\"etapa\":\"" + escapeJson(etapa) + "\"}";
    }

    private String escapeJson(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}