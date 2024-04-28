package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public interface IReservas {
    public List<Reserva> get();
    public int getTamano();
    public void insertar(Reserva reserva) throws OperationNotSupportedException;
    public Reserva buscar(Reserva reserva);
    public void borrar(Reserva reserva) throws OperationNotSupportedException;
    public List<Reserva> getReservas(Huesped huesped);
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion);
    public List<Reserva> getReservasFuturas(Habitacion habitacion);
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha);
    public void realizarCheckout(Reserva reserva, LocalDateTime fecha);
}
