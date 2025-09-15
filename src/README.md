# 🎟️ Ticket Flash

Sistema de gestión de eventos y entradas, inspirado en plataformas como TuEntrada.  
Permite crear eventos, vender entradas, generar QR y validar accesos.

---

## 🚀 Tecnologías
- Java 17
- Spring Boot
- Spring Data JPA (MySQL)
- Spring Security (roles y permisos)
- Maven
- [Opcional] Angular / Thymeleaf para front-end
- Integraciones externas: API Mercado Libre

---

## ⚙️ Instalación y ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/usuario/ticket-flash.git
   

#  EndPoints Principales

### Category : 

@PostMapping("/category/create")

-- Permite crear una categoria al usuario admin



### Events : 

@PostMapping("/event/create/{categoryId}") 

RequestBody EventDTO eventDto,
PathVariable Long categoryId

-- Permite crear un evento de una categoria especifica


@GetMapping ("/event/findById/{eventId}")

-- Busca eventos por id



@GetMapping("/events/search")

-- Busca eventoos por nombre



### CategoryTicket :

(@RequestMapping("/api/category-ticket")) -- Ruta de enpoints


@PostMapping("/create") RequestBody CategoryTicket categoryTicket

-- Crea Categoria de ticket para un evento

@GetMapping("/all") 

-- Trae todas las Categorias de tickets

@GetMapping("/find/{id}")

-- Busca Categorias de tickets por id

@PutMapping("/reduce-stock/{id}")

-- Reduce el stock de entradas de una categoria de ticket

### Ticket

@PostMapping("/ticket/create/{id_CategoryTicket}")RequestBody TicketDTO dto, PathVariable Long id_CategoryTicket

-- Crea un ticket para un evento, de una categoria en especifica 


@PostMapping("/Ticket/all")

-- Trae todos los tickets

@GetMapping ("/ticket/findById/{ticketId}")

-- Busca tickets por id

@GetMapping("/tickets/user/{userId}")

-- Busca tickets por id de usuario

### User

@PostMapping("/user/create")

-- Crea usuarios



### Payment

@PostMapping("/create-preference/{categoryId}") RequestBody TicketDTO ticketDTO,
PathVariable Long categoryId

-- Crea una preferencia de pago en MercadoPago a partir de un ticket y devuelve la URL para realizar el pago.


@PostMapping("/webhook") RequestBody Map<String, Object> payload)

-- Recibe las notificaciones de MercadoPago (pagos aprobados, rechazados, etc.) y las procesa internamente.


@GetMapping("/pago-exitoso") RequestParam("payment_id") Long paymentId

-- Confirma el pago realizado y genera el ticket correspondiente.






# Services








   


   



