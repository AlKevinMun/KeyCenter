package com.proyect.keycenter.modelo;

import lombok.Getter;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {
    static InetAddress localHost;
    @Getter
    static String ipAddress;

    public IP() throws UnknownHostException {
        localHost = InetAddress.getLocalHost();
        ipAddress = localHost.getHostAddress();
    }

}
