package org.iesalandalus.programacion.reservashotel.controlador;

import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador (Modelo modelo, Vista vista){
        if (modelo==null){
            throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
        }
        if (vista==null){
            throw new NullPointerException("ERROR: La vista no puede ser nula.");
        }
        this.modelo=modelo;
        this.vista=vista;
        this.vista.setControlador(this);
    }

    public void comenzar(){
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar(){
        modelo.terminar();
        vista.terminar();
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException, NullPointerException {
        modelo.insertar(huesped);
    }

    public Huesped buscar(Huesped huesped) throws NullPointerException, IllegalArgumentException {
        return modelo.buscar(huesped);
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException, NullPointerException {
        modelo.borrar(huesped);
    }

    public List<Huesped> getHuespedes(){
        return modelo.getHuespedes();
    }

    public void insertar (Habitacion habitacion) throws OperationNotSupportedException, NullPointerException {
        modelo.insertar(habitacion);
    }

    public Habitacion buscar (Habitacion habitacion) throws NullPointerException {
        return modelo.buscar(habitacion);
    }

    public void borrar (Habitacion habitacion) throws OperationNotSupportedException, NullPointerException {
        modelo.borrar(habitacion);
    }

    public List<Habitacion> getHabitaciones() {
        return modelo.getHabitaciones();
    }

    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion) throws NullPointerException {
        return modelo.getHabitaciones(tipoHabitacion);
    }

    public void insertar (Reserva reserva) throws OperationNotSupportedException, NullPointerException {
        modelo.insertar(reserva);
    }

    public Reserva buscar (Reserva reserva) throws NullPointerException {
        return modelo.buscar(reserva);
    }

    public void borrar (Reserva reserva) throws OperationNotSupportedException, NullPointerException {
        modelo.borrar(reserva);
    }

    public List<Reserva> getReservas() {
        return modelo.getReservas();
    }

    public List<Reserva> getReservas(Huesped huesped) throws NullPointerException {
        return modelo.getReservas(huesped);
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) throws NullPointerException {
        return modelo.getReservas(tipoHabitacion);
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion) throws NullPointerException {
        return modelo.getReservasFuturas(habitacion);
    }

    public void realizarCheckin (Reserva reserva, LocalDateTime fecha) throws IllegalArgumentException, NullPointerException {
        modelo.realizarCheckin(reserva, fecha);
    }

    public void realizarCheckout (Reserva reserva, LocalDateTime fecha) throws IllegalArgumentException, NullPointerException {
        modelo.realizarCheckout(reserva, fecha);
    }
}
