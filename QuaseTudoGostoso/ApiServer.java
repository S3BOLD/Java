import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiServer {
    private static GerenciadorDeReceitas gerenciador = new GerenciadorDeReceitas();

    public static void main(String[] args) throws Exception {
    int port = 8089;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

    server.createContext("/receitas", new ReceitasHandler());
    // context com barra final para capturar paths com id (/receitas/{id})
    server.createContext("/receitas/", new ReceitasHandler());
        server.createContext("/ingredientes", new IngredientesHandler());
        server.createContext("/modos", new ModosHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("API rodando em http://localhost:" + port);
    }

    static class ReceitasHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            try {
                String method = exchange.getRequestMethod();
                String path = exchange.getRequestURI().getPath();
                if (path != null && path.startsWith("/receitas/")) {
                    String[] parts = path.split("/");
                    if (parts.length >= 3) {
                        try {
                            Integer.parseInt(parts[2]);
                            if ("GET".equalsIgnoreCase(method)) {
                                handleGetById(exchange);
                            } else if ("DELETE".equalsIgnoreCase(method)) {
                                handleDeleteById(exchange);
                            } else {
                                sendResponse(exchange, 405, "Method Not Allowed");
                            }
                            return;
                        } catch (NumberFormatException nfe) {
                            // fallthrough to 404
                        }
                    }
                    sendResponse(exchange, 404, "Not Found");
                } else if ("/receitas".equals(path) || "/receitas/".equals(path)) {
                    if ("GET".equalsIgnoreCase(method)) {
                        handleGet(exchange);
                    } else if ("POST".equalsIgnoreCase(method)) {
                        handlePost(exchange);
                    } else {
                        sendResponse(exchange, 405, "Method Not Allowed");
                    }
                } else {
                    sendResponse(exchange, 404, "Not Found");
                }
            } catch (Exception e) {
                try { sendResponse(exchange, 500, "Internal Server Error"); } catch (Exception ex) {}
            }
        }

        private void handleGetById(HttpExchange exchange) throws Exception {
            String path = exchange.getRequestURI().getPath();
            String[] parts = path.split("/");
            int id = Integer.parseInt(parts[2]);
            List<Receita> lista = gerenciador.getReceitas();
            if (id < 0 || id >= lista.size()) {
                sendResponse(exchange, 404, "not found");
                return;
            }
            sendJson(exchange, 200, lista.get(id).toJson());
        }

        private void handleDeleteById(HttpExchange exchange) throws Exception {
            String path = exchange.getRequestURI().getPath();
            String[] parts = path.split("/");
            int id = Integer.parseInt(parts[2]);
            List<Receita> lista = gerenciador.getReceitas();
            if (id < 0 || id >= lista.size()) {
                sendResponse(exchange, 404, "not found");
                return;
            }
            lista.remove(id);
            sendResponse(exchange, 204, "");
        }

        private void handleGet(HttpExchange exchange) throws Exception {
            List<Receita> lista = gerenciador.getReceitas();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < lista.size(); i++) {
                sb.append(lista.get(i).toJson());
                if (i < lista.size() - 1) sb.append(",");
            }
            sb.append("]");
            sendJson(exchange, 200, sb.toString());
        }

        private void handlePost(HttpExchange exchange) throws Exception {
            String body = JsonUtil.readBody(exchange.getRequestBody());
            Receita r = JsonUtil.parseReceita(body);
            if (r == null) {
                sendResponse(exchange, 400, "invalid json");
                return;
            }
            gerenciador.adicionarReceita(r);
            sendResponse(exchange, 201, "created");
        }
    }

    static class IngredientesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            try {
                if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                    List<Receita> lista = gerenciador.getReceitas();
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    boolean first = true;
                    for (Receita r : lista) {
                        for (Ingrediente ing : r.getIngredientes()) {
                            if (!first) sb.append(",");
                            sb.append("{\"nome\":\"").append(escape(ing.getNome())).append("\",\"quantidade\":\"").append(escape(ing.getQuantidade())).append("\"}");
                            first = false;
                        }
                    }
                    sb.append("]");
                    sendJson(exchange, 200, sb.toString());
                } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    sendResponse(exchange, 501, "Not implemented: create ingrediente via receita POST instead");
                } else {
                    sendResponse(exchange, 405, "Method Not Allowed");
                }
            } catch (Exception e) {
                try { sendResponse(exchange, 500, "Internal Server Error"); } catch (Exception ex) {}
            }
        }
    }

    static class ModosHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            try {
                if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                    List<Receita> lista = gerenciador.getReceitas();
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    boolean first = true;
                    for (Receita r : lista) {
                        for (ModoPreparo m : r.getModoPreparo()) {
                            if (!first) sb.append(",");
                            sb.append("{\"etapa\":\"").append(escape(m.getEtapa())).append("\"}");
                            first = false;
                        }
                    }
                    sb.append("]");
                    sendJson(exchange, 200, sb.toString());
                } else {
                    sendResponse(exchange, 405, "Method Not Allowed");
                }
            } catch (Exception e) {
                try { sendResponse(exchange, 500, "Internal Server Error"); } catch (Exception ex) {}
            }
        }
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static void sendJson(HttpExchange exchange, int code, String body) throws Exception {
        Headers h = exchange.getResponseHeaders();
        h.set("Content-Type", "application/json; charset=utf-8");
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    private static void sendResponse(HttpExchange exchange, int code, String body) throws Exception {
        Headers h = exchange.getResponseHeaders();
        h.set("Content-Type", "text/plain; charset=utf-8");
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
