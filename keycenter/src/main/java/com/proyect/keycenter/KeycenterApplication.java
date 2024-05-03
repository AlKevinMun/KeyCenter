package com.proyect.keycenter;

import com.proyect.keycenter.modelo.IP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

@SpringBootApplication
public class KeycenterApplication {

	public static void main(String[] args) throws UnknownHostException {
		new IP();
		SpringApplication.run(KeycenterApplication.class, args);
	}
}
