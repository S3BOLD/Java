import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    // Lê corpo do InputStream para String
    public static String readBody(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    // Parse bem simples de um JSON de receita esperado no formato:
    // {"nome":"...","ingredientes":[{"nome":"...","quantidade":"..."},...],"modoPreparo":[{"etapa":"..."},...]}
    public static Receita parseReceita(String json) {
        if (json == null || json.trim().isEmpty()) return null;
        try {
            String s = json.trim();
            String nome = extractString(s, "nome");
            Receita r = new Receita(nome != null ? nome : "");

            String ingredientesArray = extractArray(s, "ingredientes");
            if (ingredientesArray != null) {
                List<String> items = splitTopLevelObjects(ingredientesArray);
                for (String item : items) {
                    String nomeIng = extractString(item, "nome");
                    String qtd = extractString(item, "quantidade");
                    if (nomeIng != null) r.adicionarIngrediente(new Ingrediente(nomeIng, qtd != null ? qtd : ""));
                }
            }

            String modosArray = extractArray(s, "modoPreparo");
            if (modosArray != null) {
                List<String> items = splitTopLevelObjects(modosArray);
                for (String item : items) {
                    String etapa = extractString(item, "etapa");
                    if (etapa != null) r.adicionarModoPreparo(new ModoPreparo(etapa));
                }
            }

            return r;
        } catch (Exception e) {
            return null;
        }
    }

    private static String extractString(String s, String key) {
    String quotedKey = '"' + key + '"';
    int keyIdx = s.indexOf(quotedKey);
    if (keyIdx == -1) return null;
    int colon = s.indexOf(':', keyIdx + quotedKey.length());
    if (colon == -1) return null;
    int firstQuote = s.indexOf('"', colon);
    if (firstQuote == -1) return null;
    int start = firstQuote + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < s.length()) {
                sb.append(s.charAt(i + 1));
                i++;
                continue;
            }
            if (c == '"') break;
            sb.append(c);
        }
        return sb.toString();
    }

    private static String extractArray(String s, String key) {
    String quotedKey = '"' + key + '"';
    int keyIdx = s.indexOf(quotedKey);
    if (keyIdx == -1) return null;
    int colon = s.indexOf(':', keyIdx + quotedKey.length());
    if (colon == -1) return null;
    int start = s.indexOf('[', colon);
        if (start == -1) return null;
        int depth = 0;
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') depth++;
            if (c == ']') depth--;
            if (depth == 0) {
                return s.substring(start + 1, i);
            }
        }
        return null;
    }

    private static List<String> splitTopLevelObjects(String s) {
        List<String> res = new ArrayList<>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                if (depth == 0) start = i;
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) {
                    res.add(s.substring(start, i + 1));
                }
            }
        }
        return res;
    }
}
