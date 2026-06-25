# 🧩 Sistema de Consultoría - Microservicios

## 📌 Descripción del dominio

Este sistema representa una plataforma de consultoría empresarial basada en microservicios.

Permite gestionar:
- Clientes
- Tickets
- Proyectos
- Reuniones
- Facturación
- Reportes
- Auditoría
- Notificaciones
- Autenticación

Todo está centralizado mediante **API Gateway** y registrado en **Eureka Server**.

---

## 👨‍🎓 Estudiantes

- Thomas Escobar
- Giovanny Reyes

---

## 🧠 Microservicios técnicos del sistema

Estos servicios forman la infraestructura base del sistema:

### 🔵 Eureka Server (Service Discovery)
- 📍 Puerto: `8761`
- Función: Registro y descubrimiento de microservicios
- Todos los servicios se registran aquí automáticamente

---

### 🌐 API Gateway (Spring Cloud Gateway)
- 📍 Puerto: `8010`
- Función: Punto único de entrada al sistema
- Redirige las peticiones a los microservicios

---

## 🧱 Microservicios de negocio (registrados en Eureka)

Estos servicios se conectan a Eureka y son consumidos vía Gateway:

| Microservicio | Puerto | Ruta Gateway |
|--------------|--------|-------------|
| Autenticación | 8080 | `/autenticacion/**` |
| Clientes | 8081 | `/clientes/**` |
| Tickets | 8082 | `/tickets/**` |
| Proyectos | 8083 | `/proyectos/**` |
| Reuniones | 8084 | `/reuniones/**` |
| Facturación | 8085 | `/facturas/**` |
| Reportes | 8086 | `/reportes/**` |
| Auditoría | 8087 | `/auditorias/**` |
| Notificaciones | 8088 | `/notificaciones/**` |

---

## 🌐 API Gateway - Rutas principales

```yaml id="gatewayroutes"
server:
  port: 8010

spring:
  application:
    name: gateway

  cloud:
    gateway:
      mvc:
        routes:
          - id: servicio-autenticacion
            uri: http://localhost:8080
            predicates:
              - Path=/autenticacion/**

          - id: servicio-clientes
            uri: http://localhost:8081
            predicates:
              - Path=/clientes/**

          - id: servicio-tickets
            uri: http://localhost:8082
            predicates:
              - Path=/tickets/**

          - id: servicio-proyectos
            uri: http://localhost:8083
            predicates:
              - Path=/proyectos/**

          - id: servicio-reuniones
            uri: http://localhost:8084
            predicates:
              - Path=/reuniones/**

          - id: servicio-facturas
            uri: http://localhost:8085
            predicates:
              - Path=/facturas/**

          - id: servicio-reportes
            uri: http://localhost:8086
            predicates:
              - Path=/reportes/**

          - id: servicio-auditoria
            uri: http://localhost:8087
            predicates:
              - Path=/auditorias/**

          - id: servicio-notificaciones
            uri: http://localhost:8088
            predicates:
              - Path=/notificaciones/**
