package com.proyect.keycenter.entities;

import lombok.Getter;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Clase para representar la dirección IP local del sistema.
 * Utiliza Lombok para simplificar la creación de getters y manejar la excepción UnknownHostException.
 *
 * @author Alejandro Parrilla Ruiz
 */
public class IP {
    /**
     * LocalHost del sistema.
     */
    static InetAddress localHost;
    /**
     * Dirección IP local del sistema.
     */
    @Getter
    static String ipAddress;

    /**
     * Inicializa la dirección IP local del sistema.
     * @throws UnknownHostException Si no se puede determinar la dirección IP local.
     */
    public IP() throws UnknownHostException {
        localHost = InetAddress.getLocalHost();
        ipAddress = localHost.getHostAddress();
    }
}
