package com.proyect.keycenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

/**
 * Clase principal de la aplicación Spring Boot.
 * Inicializa la dirección IP local antes de ejecutar la aplicación.
 *
 * @author Alejandro Parrilla Ruiz
 */
@SpringBootApplication
public class KeycenterApplication {

	/**
	 * Método principal que inicia la aplicación.
	 * Intenta obtener la dirección IP local antes de iniciar la aplicación.
	 * @param args Argumentos de línea de comando.
	 * @throws UnknownHostException Si no se puede determinar la dirección IP local.
	 */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(KeycenterApplication.class, args);
	}
}
