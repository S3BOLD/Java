
import java.util.ArrayList;
import java.util.List;

public class Receita {
    private String nome;
    private List<Ingrediente> ingredientes;
    private List<ModoPreparo> modoPreparo;
    
    public Receita(String nome) {
        this.nome = nome;
        this.ingredientes = new ArrayList<>();
        this.modoPreparo = new ArrayList<>();

    }

    public String getNome() {
        return nome;
    }

    public java.util.List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public java.util.List<ModoPreparo> getModoPreparo() {
        return modoPreparo;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"nome\":\"").append(escapeJson(nome)).append("\"");

        sb.append(",\"ingredientes\":[");
        for (int i = 0; i < ingredientes.size(); i++) {
            Ingrediente ing = ingredientes.get(i);
            sb.append("{");
            sb.append("\"nome\":\"").append(escapeJson(ing.getNome())).append("\"");
            sb.append(",\"quantidade\":\"").append(escapeJson(ing.getQuantidade())).append("\"");
            sb.append("}");
            if (i < ingredientes.size() - 1) sb.append(",");
        }
        sb.append("]");

        sb.append(",\"modoPreparo\":[");
        for (int i = 0; i < modoPreparo.size(); i++) {
            sb.append(modoPreparo.get(i).toJson());
            if (i < modoPreparo.size() - 1) sb.append(",");
        }
        sb.append("]");

        sb.append("}");
        return sb.toString();
    }

    private String escapeJson(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void adicionarModoPreparo(ModoPreparo etapa) {
        modoPreparo.add(etapa);
    }

    public void mostrarDetalhes() {
        System.out.println("\n Receita: " + nome);
        System.out.println("\n Igrediente: ");
        for (Ingrediente ing : ingredientes) {
            System.out.println(" " + ing);
        }

        System.out.println("\n Modo de Preparo:");
        for (int i = 0; i < modoPreparo.size(); i++) {
            System.out.println(" Passo" + (i + 1) + ": " + modoPreparo.get(i).getEtapa());
        }
    }
}