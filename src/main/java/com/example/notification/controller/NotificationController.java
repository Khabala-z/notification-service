package com.example.notification.controller;

import com.example.notification.service.EmailService;
import com.example.notification.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;
    private final ReportService reportService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String to) {
        emailService.sendEmail(to, "Bienvenida al Microservicio", "Hola! Este es un correo <b>asíncrono</b> enviado desde Java.");
        return ResponseEntity.ok("Envío de correo iniciado en segundo plano para: " + to);
    }

    @GetMapping("/report/excel")
    public ResponseEntity<byte[]> downloadExcel() throws IOException {
        byte[] content = reportService.generateExcelReport();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(content);
    }

    @GetMapping("/report/pdf")
    public ResponseEntity<byte[]> downloadPdf() {
        byte[] content = reportService.generatePdfReport();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(content);
    }
}
