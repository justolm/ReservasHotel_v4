package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Reservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public class Modelo {
    private static IHabitaciones habitaciones;
    private static IReservas reservas;
    private static IHuespedes huespedes;

    public Modelo(){}

    public void comenzar() throws IllegalArgumentException, NullPointerException {
        habitaciones = new Habitaciones();
        reservas = new Reservas();
        huespedes = new Huespedes();
    }

    public void terminar(){
        System.out.println("El modelo ha finalizado.");
    }

    public void insertar (Huesped huesped) throws OperationNotSupportedException, NullPointerException {
        huespedes.insertar(huesped);
    }

    public Huesped buscar (Huesped huesped) throws IllegalArgumentException, NullPointerException {
        return huespedes.buscar(huesped);
    }

    public void borrar (Huesped huesped) throws OperationNotSupportedException, NullPointerException {
        huespedes.borrar(huesped);
    }

    public List<Huesped> getHuespedes(){
        return huespedes.get();
    }

    public void insertar (Habitacion habitacion) throws OperationNotSupportedException, NullPointerException {
        habitaciones.insertar(habitacion);
    }

    public Habitacion buscar (Habitacion habitacion) throws NullPointerException {
        return habitaciones.buscar(habitacion);
    }

    public void borrar (Habitacion habitacion) throws OperationNotSupportedException, NullPointerException {
        habitaciones.borrar(habitacion);
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones.get();
    }

    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion) throws NullPointerException {
        return habitaciones.get(tipoHabitacion);
    }

    public void insertar (Reserva reserva) throws OperationNotSupportedException, NullPointerException {
        reservas.insertar(reserva);
    }

    public Reserva buscar (Reserva reserva) throws NullPointerException {
        return reservas.buscar(reserva);
    }

    public void borrar (Reserva reserva) throws OperationNotSupportedException, NullPointerException {
        reservas.borrar(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas.get();
    }

    public List<Reserva> getReservas(Huesped huesped) throws NullPointerException {
        return reservas.getReservas(huesped);
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) throws NullPointerException {
        return reservas.getReservas(tipoHabitacion);
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion) throws NullPointerException {
        return reservas.getReservasFuturas(habitacion);
    }

    public void realizarCheckin (Reserva reserva, LocalDateTime fecha) throws IllegalArgumentException, NullPointerException {
        reservas.realizarCheckin(reserva, fecha);
    }

    public void realizarCheckout (Reserva reserva, LocalDateTime fecha) throws IllegalArgumentException, NullPointerException {
        reservas.realizarCheckout(reserva, fecha);
    }
}
