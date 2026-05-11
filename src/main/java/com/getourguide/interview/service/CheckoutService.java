package com.getourguide.interview.service;

import com.getourguide.interview.entity.Order;
import com.getourguide.interview.repository.OrderRepository;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final OrderRepository orderRepository;

    @Transactional
    public void checkout(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus("RESERVED");
        orderRepository.save(order);

        chargeCustomer(order);
        sendConfirmationEmail(order);
    }

    @SneakyThrows
    void chargeCustomer(Order order) {

        String body = """
            {"orderId": %d, "amount": %s}
            """.formatted(order.getId(), order.getPrice());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8081/payments/charge"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    void sendConfirmationEmail(Order order) {

        String body = """
            {"orderId": %d, "bookingReference": "%s"}
            """.formatted(order.getId(), order.getBookingReference());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8081/emails/confirmation"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
