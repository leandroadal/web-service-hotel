package api.rest.client;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ClientReservation {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Type (solteiro, casal, familia): ");
        String type = scanner.nextLine();

        System.out.print("Category (standard, executivo, luxo): ");
        String category = scanner.nextLine();

        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Start date (yyyy-mm-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.next());

        System.out.print("End date (yyyy-mm-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.next());

        scanner.close();

        // cria a requisição com os parâmetros recebidos
        Request request = new Request();
        request.setType(type);
        request.setCategory(category);
        request.setQuantity(quantity);
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        // cria o cabeçalho da requisição com o tipo de mídia
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // cria a entidade HTTP com o corpo da requisição e o cabeçalho
        HttpEntity<Request> entity = new HttpEntity<Request>(request, headers);

        // cria uma instância do RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/reservations", entity, String.class);
        System.out.println(response.getBody());

    }
}