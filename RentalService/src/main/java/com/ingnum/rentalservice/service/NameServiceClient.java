package com.ingnum.rentalservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NameServiceClient {

    private final RestTemplate restTemplate;
    private final String nameServiceUrl;

    public NameServiceClient(@Value("${nameservice.url:http://localhost:8080}") String nameServiceUrl) {
        this.restTemplate = new RestTemplate();
        this.nameServiceUrl = nameServiceUrl;
    }

    public String getName() {
        try {
            return restTemplate.getForObject(nameServiceUrl, String.class);
        } catch (Exception e) {
            return "Erreur lors de la communication avec NameService: " + e.getMessage();
        }
    }

    public CustomerInfo getCustomerInfo(String customerName) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(nameServiceUrl)
                    .path("/customer/{name}")
                    .buildAndExpand(customerName)
                    .toUriString();
            CustomerResponse response = restTemplate.getForObject(url, CustomerResponse.class);
            if (response != null && response.getName() != null) {
                return new CustomerInfo(response.getName());
            }
            return new CustomerInfo("Client inconnu");
        } catch (Exception e) {
            return new CustomerInfo("Erreur lors de la communication avec NameService: " + e.getMessage());
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class CustomerResponse {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CustomerInfo {
        private String name;

        public CustomerInfo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

