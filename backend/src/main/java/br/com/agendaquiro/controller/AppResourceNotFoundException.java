package br.com.agendaquiro.controller;

public class AppResourceNotFoundException extends Throwable{
    public  AppResourceNotFoundException(String msg) {
        super(msg);
    }
}
