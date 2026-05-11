package com.getourguide.interview.service;

import com.getourguide.interview.entity.Order;
import com.getourguide.interview.repository.OrderRepository;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CheckoutServiceIntegrationTest {

    private static WireMockServer wireMock;

    @Autowired private CheckoutService checkoutService;
    @Autowired private OrderRepository orderRepository;

    @BeforeAll
    static void startWireMock() {
        wireMock = new WireMockServer(wireMockConfig().port(8081));
        wireMock.start();
        wireMock.stubFor(post(urlEqualTo("/payments/charge")).willReturn(aResponse().withStatus(200)));
        wireMock.stubFor(post(urlEqualTo("/emails/confirmation")).willReturn(aResponse().withStatus(200)));
    }

    @AfterAll
    static void stopWireMock() {
        wireMock.stop();
    }

    @Test
    void checkoutReservesOrder() {
        Order order = orderRepository.findAll().getFirst();

        checkoutService.checkout(order.getId());

        assertEquals("RESERVED", orderRepository.findById(order.getId()).orElseThrow().getStatus());
    }
}
