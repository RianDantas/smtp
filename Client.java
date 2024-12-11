import java.io.*; // Importa classes para manipular fluxos de entrada e saída.
import java.net.*; // Importa classes para comunicação de rede, como Sockets.
import java.util.Base64;

public class Client { // Define a classe principal do cliente.

    private static void sendCommand(PrintWriter writer, BufferedReader reader, String command) throws IOException {
        writer.println(command);//Envia o comando
        
        System.out.println("Enviado: " + command);
        
        System.out.println("Reposta do servidor: " + reader.readLine());
        //Lê a resposta do server
    }


    public static void main(String[] args) { // Método principal que inicia a execução do cliente.

        String host = "smtp.mailtrap.io"; // Define o host do servidor (localhost para conexões locais).
        int port = 2525; // Define a porta do servidor para se conectar.


        
        // Cria uma conexão com o servidor no host e porta especificados.
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conectado ao servidor em " + host + ":" + port); // Exibe mensagem indicando que a conexão foi bem-sucedida.

            // Cria fluxos de entrada e saída para comunicação com o servidor, além de ler a entrada do teclado.
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        

            
             // Recebe a saudação do servidor       
            System.out.println("Resposta do servidor SMTP: " + reader.readLine());

            

            sendCommand(writer, reader, "Hello smtp.mailtrap.io");
            
            sendCommand(writer, reader, "Mail From:<seu-email@email.com>");

            sendCommand(writer, reader, "Recept to:<destinarario@email.com>");

            sendCommand(writer, reader, "DATA");

            writer.println("Subject: Teste de e-mail");

            writer.println("From: seu-email@email.com");

            writer.println("To: destinarario@email.com");

            writer.println();

            writer.println(".");

            System.out.println("Resposta: " + reader.readLine());


            sendCommand(writer, reader, "QUIT");
            socket.close();


            String reponse = reader.readLine();

            if(!response.startWith("250") && !response.startWith("354")){
                throw new IOException("Erro ao processar comando: " + reponse);
            }

            sendCommand(writer, reader, "AUTH Login");
            sendCommand(writer, Base64.getEncoder().encodeToString("Seu-Usuario".getBytes()));

            sendCommand(writer, Base64.getEncoder().encodeToString("Sua-Senha:".getBytes()));

            sendCommand(writer, reader, "QUIT");
            socket.close();
            System.out.println("Conexão Encerrada.");


        } catch (IOException e) { // Trata exceções relacionadas a entrada/saída.
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage()); // Exibe mensagem de erro.
        }
    }
}