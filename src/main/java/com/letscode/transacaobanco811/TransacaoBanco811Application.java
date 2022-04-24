package com.letscode.transacaobanco811;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TransacaoBanco811Application {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoBanco811Application.class, args);
	}

}
