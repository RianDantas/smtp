import java.io.*; // Importa classes para manipular fluxos de entrada e saída.
import java.net.*; // Importa classes para comunicação de rede, como Sockets e ServerSocket.

public class Server { // Define a classe principal do servidor.

    public static void main(String[] args) { // Método principal que inicia a execução do servidor.

        int port = 12345; // Porta para escutar conexões

        // Cria um objeto ServerSocket que escuta na porta especificada.
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {  
            
            System.out.println("Servidor iniciado na porta " + port); // Exibe mensagem indicando que o servidor está pronto.

            // Aguarda uma conexão de um cliente. Quando um cliente se conecta, retorna um objeto Sockets.
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress()); // Exibe o IP do cliente conectado.

            // Cria fluxos de comunicação para ler e enviar mensagens entre o servidor e o cliente.
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message; // Declara a variável message para armazenar as mensagens recebidas.
            // Inicia um loop que lê mensagens do cliente enquanto houver dados.
            while ((message = in.readLine()) != null) {
                System.out.println("Cliente: " + message); // Exibe a mensagem recebida do cliente.
                out.println("Mensagem recebida: " + message); // Responde ao cliente confirmando que a mensagem foi recebida.

                // Se a mensagem for "bye", encerra a comunicação.
                if (message.equalsIgnoreCase("bye")) break; 
            }

            clientSocket.close(); // Fecha a conexão com o cliente.

        } catch (IOException e) { // Trata exceções relacionadas a entrada/saída.
            System.err.println("Erro no servidor: " + e.getMessage()); // Exibe mensagem de erro.
        }
    }
}
