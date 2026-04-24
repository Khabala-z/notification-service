package com.example.notification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    // Usamos @Async para que el hilo principal no se quede colgado esperando a que el servidor de correo responda.
    // Esto es clave para que la API responda rápido mientras el proceso pesado corre de fondo.
    @Async
    public void sendEmail(String to, String subject, String bodyContent) {
        log.info("Preparando envío de correo para: {}", to);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);

            String htmlBody = """
                <div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #eee; padding: 20px; border-radius: 10px; background-color: #f9f9f9;'>
                    <h2 style='color: #4A90E2;'>¡Bienvenido al Microservicio!</h2>
                    <p style='color: #333; font-size: 16px;'>Este es un ejemplo de correo <strong>asíncrono</strong> generado con Java Spring Boot.</p>
                    <div style='background: #4A90E2; color: white; padding: 15px; border-radius: 5px; text-align: center; margin-top: 20px;'>
                        <p style='margin: 0; font-size: 18px;'>Tu reporte está listo para ser generado</p>
                    </div>
                    <p style='color: #777; font-size: 12px; margin-top: 30px;'>Este es un mensaje automático, por favor no respondas.</p>
                </div>
                """;

            helper.setText(htmlBody, true);

            mailSender.send(message);
            log.info("Correo enviado exitosamente a: {}", to);
        } catch (MessagingException e) {
            log.error("Error al enviar el correo a {}: {}", to, e.getMessage());
        }
    }
}
