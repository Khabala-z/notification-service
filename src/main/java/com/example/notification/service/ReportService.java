package com.example.notification.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ReportService {

    /**
     * Genera un reporte de usuarios en formato Excel (.xlsx)
     * He usado Apache POI porque es el estándar para manejar archivos de Office en Java.
     */
    public byte[] generateExcelReport() throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Reporte de Usuarios");

            // Header
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Nombre", "Email", "Fecha Registro"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Datos de prueba
            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue(1);
            row.createCell(1).setCellValue("Usuario Demo");
            row.createCell(2).setCellValue("demo@example.com");
            row.createCell(3).setCellValue("2024-04-24");

            workbook.write(out);
            return out.toByteArray();
        }
    }

    /**
     * Genera un PDF básico usando OpenPDF. 
     * Es ideal para facturas o reportes rápidos que no necesitan un diseño ultra complejo.
     */
    public byte[] generatePdfReport() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("REPORTE DE NOTIFICACIONES"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Este es un reporte generado automáticamente por el microservicio de Java."));
            document.add(new Paragraph("Estado del servicio: Operativo"));
            document.add(new Paragraph("Generado el: " + java.time.LocalDateTime.now()));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF", e);
        }
    }
}
