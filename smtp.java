import com.sun.security.ntlm.Server;
import com.sun.security.ntlm.Client;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class smtp {
 public static void main(String[] args) {
        // Configurações do servidor SMTP
        String host = "sandbox.smtp.mailtrap.io"; // Para Mailtrap (ou use outro servidor como o Gmail)
        int port = 587; // Porta para STARTTLS
        String username = "d228ea68375714"; // Seu usuário do Mailtrap
        String password = "fcdf15e7048942"; // Sua senha do Mailtrap

        // Configuração das propriedades para a conexão SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS para criptografar

        // Criação da sessão de autenticação com o servidor SMTP
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); // Autenticação SMTP
            }
        });

        try {
            // Criação do e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("de@exemplo.com")); // E-mail do remetente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("para@exemplo.com")); // E-mail do destinatário
            message.setSubject("Teste SMTP em Java"); // Assunto do e-mail
            message.setText("Este é um e-mail de teste enviado pelo protocolo SMTP em Java!"); // Corpo do e-mail

            // Envio do e-mail
            Transport.send(message); // Envia o e-mail

            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }   
}
