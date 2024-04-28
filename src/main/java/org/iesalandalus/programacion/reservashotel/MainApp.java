package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

import java.time.DateTimeException;


public class MainApp {

    public static void main(String[] args) {
        System.out.println("Programa para la Gestión de Hoteles IES Al-Ándalus");
        try {
            Modelo modelo = new Modelo();
            Vista vista = new Vista();
            Controlador controlador = new Controlador(modelo, vista);
            Opcion.setVista(vista);
            controlador.comenzar();
        } catch (NullPointerException | IllegalArgumentException | DateTimeException e){
            System.out.println(e.getMessage());
        }
    }
}
