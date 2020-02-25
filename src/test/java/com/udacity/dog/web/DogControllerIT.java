package com.udacity.dog.web;

import com.udacity.dog.entity.Dog;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DogControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllDogs() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(port).path("/dogs")
                .build().encode().toUri();
        String auth = Base64.getEncoder().encodeToString("pep:password".getBytes(Charset.defaultCharset())).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Basic " + auth);

        RequestEntity<Object> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ResponseEntity<Dog[]> responseEntity = restTemplate.exchange(requestEntity, Dog[].class);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        LogManager.getLogger(this.getClass()).info(Arrays.asList(responseEntity.getBody()));
    }

    @Test
    public void testGetDogById() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(port).path("/dog").queryParam("id", 1L)
                .build().encode().toUri();
        String auth = Base64.getEncoder().encodeToString("manager:password".getBytes(Charset.defaultCharset())).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Basic " + auth);

        RequestEntity<Object> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ResponseEntity<Dog> responseEntity = restTemplate.exchange(requestEntity, Dog.class);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        LogManager.getLogger(this.getClass()).info(responseEntity.getBody());
    }
}
