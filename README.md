# 🚀 Java Notification & Report Service

<p align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
  <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" />
</p>

¡Hola! Este es un microservicio que armé para demostrar cómo manejar procesos pesados en el backend de forma eficiente. La idea principal fue resolver dos problemas comunes: enviar correos sin bloquear la app y generar reportes profesionales (Excel/PDF) al vuelo.

---

## 🛠️ Tech Stack
Aquí están las herramientas que elegí para este proyecto y por qué:

| Tecnología | Propósito |
| :--- | :--- |
| **Java 17** | Lenguaje principal por su robustez y tipado fuerte. |
| **Spring Boot 3** | Framework base para crear la API de forma ágil. |
| **Apache POI** | Para generar Excels reales y compatibles con Office. |
| **OpenPDF** | Generación de reportes PDF de alto rendimiento. |
| **Maven** | Gestión de dependencias y automatización del build. |

---

## ⚡ Lo que hace especial a este servicio
Lo más importante aquí es el uso de **`@Async`**. Cuando pides enviar un correo, la API te responde al instante que ya empezó el proceso, mientras que el envío real ocurre en un hilo separado. Esto es vital para que una aplicación se sienta rápida y profesional.

## 🚀 Cómo ponerlo a correr
Si quieres probarlo en tu máquina:

1.  Clona el repo o descarga la carpeta.
2.  Abre el proyecto en **IntelliJ IDEA** o tu IDE favorito.
3.  Configura tus credenciales de **Mailtrap** en `application.properties`.
4.  Lanza la app y usa estos endpoints:

*   **PDF**: `GET /api/notifications/report/pdf`
*   **Excel**: `GET /api/notifications/report/excel`
*   **Email**: `POST /api/notifications/send-email?to=tu@correo.com`

---
<p align="center">
  <b>Desarrollado con ❤️ por tu nombre</b><br>
  <i>Full Stack Developer | Backend Enthusiast</i>
</p>
