package com.training.springboottoday.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;

import com.training.springboottoday.controller.TodayController;
import com.training.springboottoday.dto.TodayJsonDto;
import com.training.springboottoday.service.TodayService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = TodayController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodayControllerTest {

    private final String CONTROLLER_BASE_URL = "/springboottraining/api/v1/today";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TodayService todayService;

    @Test
    @DisplayName("Test response Hello")
    public void pingTest(){
        final String URI_TEST = "/ping";

        webTestClient.get()
                .uri(CONTROLLER_BASE_URL + URI_TEST)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(s -> s.toString(), equalTo("Hello !!!"));
    }





    //@Test
    //@Order(2)
    public void todayJsonTest(){
        final String URI_TEST = "/todayJson";

        TodayJsonDto mockDto = new TodayJsonDto("01/01/70 00:00:00");

        //condicionado de respuesta
        when(todayService.getTodayObject()).thenReturn(Mono.just(mockDto));

        verify(todayService.getTodayObject());


        webTestClient.get()
                .uri(CONTROLLER_BASE_URL + URI_TEST)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.today")
                .isEqualTo("01/01/70 00:00:00");

    }
}
