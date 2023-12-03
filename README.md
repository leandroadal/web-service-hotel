# web-service-hotel

## Descrição

Reserva de Hotel

- O `ClientReservation.java` faz a requisição de reserva ao servidor no `formato JSON` ao fornecer a data de check-in e check-out, além do tipo de quarto desejado (solteiro, casal, família) e a categoria (padrão, executivo, luxo).

- O `ReservationController.java` tem os endpoints da aplicação.
  
- Em seguida, o servidor fornece informações sobre a disponibilidade (verdadeira ou falsa), o custo por noite e o valor total da estadia, em que a classe `Reservation.java` define as propriedades da reserva, a classe `Room.java` define as propriedades do quarto e a `ReservationService.java` estabelece as regras de negócio.

- `WebServiceHotelApplication.java` sobe a aplicação.

## Como executar

- 1 - Iniciar o servidor REST executando `WebServiceHotelApplication.java` que ficara ouvindo na porta `8080`.

- 2 - Enviar requisições aos endpoints de `GET` e `POST` no formato de `JSON`.
