package com.ggomezr.bookingsystem.domain.exceptions;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(){
        super("Client not found");
    }
}
