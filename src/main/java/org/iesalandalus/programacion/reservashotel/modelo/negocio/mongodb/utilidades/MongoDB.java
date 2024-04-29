package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;

import org.bson.Document;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MongoDB {
    public static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter FORMATO_DIA_HORA = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static final String SERVIDOR = "reservashotel.vmvt8ee.mongodb.net/";
    private static final int PUERTO = 27017;
    private static final String BD = "reservashotel";
    private static final String USUARIO = "reservashotel";
    private static final String CONTRASENA = "reservashotel-2024";
    public static final String HUESPED = "huesped";
    public static final String NOMBRE = "nombre";
    public static final String DNI = "dni";
    public static final String TELEFONO = "telefono";
    public static final String CORREO = "correo";
    public static final String FECHA_NACIMIENTO = "fecha_nacimiento";
    public static final String HUESPED_DNI = HUESPED + "." + DNI;
    public static final String HABITACION = "habitacion";
    public static final String IDENTIFICADOR = "identificador";
    public static final String PLANTA = "planta";
    public static final String PUERTA = "puerta";
    public static final String PRECIO = "precio";
    public static final String HABITACION_IDENTIFICADOR = HABITACION + "." + IDENTIFICADOR;
    public static final String TIPO = "tipo";
    public static final String HABITACION_TIPO = HABITACION + "." + TIPO;
    public static final String TIPO_SIMPLE = "SIMPLE";
    public static final String TIPO_DOBLE = "DOBLE";
    public static final String TIPO_TRIPLE = "TRIPLE";
    public static final String TIPO_SUITE = "SUITE";
    public static final String CAMAS_INDIVIDUALES = "camas_individuales";
    public static final String CAMAS_DOBLES = "camas_dobles";
    public static final String BANOS = "banos";
    public static final String JACUZZI = "jacuzzi";
    public static final String RESERVA = "reserva";
    public static final String REGIMEN = "regimen";
    public static final String FECHA_INICIO_RESERVA = "fecha_inicio_reserva";
    public static final String FECHA_FIN_RESERVA = "fecha_fin_reserva";
    public static final String CHECKIN = "checkin";
    public static final String CHECHOUT = "checkout";
    public static final String PRECIO_RESERVA = "precio_reserva";
    public static final String NUMERO_PERSONAS = "numero_personas";
    private static MongoClient conexion;

    private MongoDB() {
        // Para que no genere el constructor por defecto.
    }

    public static MongoDatabase getBD() {
        if (conexion == null) {
            establecerConexion();
        }
        return conexion.getDatabase(BD);
    }

    private static void establecerConexion(){
        String connectionString;
        ServerApi serverApi;
        MongoClientSettings settings;

        if (!SERVIDOR.equals("localhost"))
        {
            connectionString = "mongodb+srv://"+ USUARIO+ ":" + CONTRASENA + "@"+ SERVIDOR +"/?retryWrites=true&w=majority";
            serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();

            settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(serverApi)
                    .build();
        }
        else
        {
            connectionString="mongodb://" + USUARIO + ":" + CONTRASENA + "@" + SERVIDOR + ":" + PUERTO ;
            MongoCredential credenciales = MongoCredential.createScramSha1Credential(USUARIO, BD, CONTRASENA.toCharArray());

            settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .credential(credenciales)
                    .build();
        }


        //Creamos la conexión con el servidor según el setting anterior
        conexion = MongoClients.create(settings);

        try
        {
            if (!SERVIDOR.equals("localhost"))
            {
                MongoDatabase database = conexion.getDatabase(BD);
                database.runCommand(new Document("ping", 1));
            }
        }
        catch (MongoException e)
        {
            e.printStackTrace();

        }

        System.out.println("Conexión a MongoDB realizada correctamente.");
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            conexion.close();
            conexion = null;
            System.out.println("Conexión a MongoDB cerrada.");
        }
    }

    public static Document getDocumento(Huesped huesped) {
        if (huesped == null) {
            return null;
        }
        String nombre = huesped.getNombre();
        String dni = huesped.getDni();
        String correo = huesped.getCorreo();
        String telefono = huesped.getTelefono();
        String fechaNacimiento = huesped.getFechaNacimiento().format(FORMATO_DIA);
        return new Document().append(NOMBRE, nombre).append(DNI, dni).append(CORREO, correo).append(TELEFONO, telefono).append(FECHA_NACIMIENTO, fechaNacimiento);
    }

    public static Huesped getHuesped(Document documentoHuesped) {
        if (documentoHuesped == null) {
            return null;
        }
        String nombre = documentoHuesped.getString(NOMBRE);
        String dni = documentoHuesped.getString(DNI);
        String correo = documentoHuesped.getString(CORREO);
        String telefono = documentoHuesped.getString(TELEFONO);
        LocalDate fechaNacimiento = LocalDate.parse(documentoHuesped.getString(FECHA_NACIMIENTO), DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA));
        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    public static Document getDocumento(Habitacion habitacion) {
        if (habitacion == null){
            return null;
        }
        int planta = habitacion.getPlanta();
        int puerta = habitacion.getPuerta();
        double precio = habitacion.getPrecio();
        Document documentoHabitacion = new Document().append(PLANTA, planta).append(PUERTA, puerta).append(PRECIO, precio);
        if (habitacion instanceof Doble) {
            int numCamasIndividuales = ((Doble) habitacion).getNumCamasIndividuales();
            int numCamasDobles = ((Doble) habitacion).getNumCamasDobles();
            documentoHabitacion.append(CAMAS_INDIVIDUALES, numCamasIndividuales).append(CAMAS_DOBLES,numCamasDobles);
        }
        else if (habitacion instanceof Triple) {
            int numBanos = ((Triple) habitacion).getNumBanos();
            int numCamasIndividuales = ((Triple) habitacion).getNumCamasIndividuales();
            int numCamasDobles = ((Triple) habitacion).getNumCamasDobles();
            documentoHabitacion.append(BANOS,numBanos).append(CAMAS_INDIVIDUALES, numCamasIndividuales).append(CAMAS_DOBLES, numCamasDobles);
        }
        else if (habitacion instanceof Suite) {
            int numBanos = ((Suite) habitacion).getNumBanos();
            boolean tieneJacuzzi = ((Suite) habitacion).isTieneJacuzzi();
            documentoHabitacion.append(BANOS, numBanos).append(JACUZZI, tieneJacuzzi);
        }
        return documentoHabitacion;
    }

    public static Habitacion getHabitacion(Document documentoHabitacion) {
        if (documentoHabitacion == null) {
            return null;
        }
        Habitacion habitacion;
        if (documentoHabitacion.containsKey(JACUZZI)) {
            habitacion = new Suite(documentoHabitacion.getInteger(PLANTA),documentoHabitacion.getInteger(PUERTA),documentoHabitacion.getDouble(PRECIO),documentoHabitacion.getInteger(BANOS), documentoHabitacion.getBoolean(JACUZZI));
        }
        else if (documentoHabitacion.containsKey(BANOS)){
            habitacion = new Triple(documentoHabitacion.getInteger(PLANTA),documentoHabitacion.getInteger(PUERTA),documentoHabitacion.getDouble(PRECIO),documentoHabitacion.getInteger(BANOS),documentoHabitacion.getInteger(CAMAS_INDIVIDUALES),documentoHabitacion.getInteger(CAMAS_DOBLES));
        }
        else if (documentoHabitacion.containsKey(CAMAS_DOBLES)) {
            habitacion = new Doble(documentoHabitacion.getInteger(PLANTA),documentoHabitacion.getInteger(PUERTA),documentoHabitacion.getDouble(PRECIO),documentoHabitacion.getInteger(CAMAS_INDIVIDUALES),documentoHabitacion.getInteger(CAMAS_DOBLES));
        }
        else {
            habitacion = new Simple(documentoHabitacion.getInteger(PLANTA),documentoHabitacion.getInteger(PUERTA),documentoHabitacion.getDouble(PRECIO));
        }
        return habitacion;
    }

    public static Document getDocumento(Reserva reserva) {
        if (reserva == null){
            return null;
        }
        Huesped huesped = reserva.getHuesped();
        Habitacion habitacion = reserva.getHabitacion();
        Regimen regimen = reserva.getRegimen();
        LocalDate fechaInicioReserva = reserva.getFechaInicioReserva();
        LocalDate fechaFinReserva = reserva.getFechaFinReserva();
        int numPersonas = reserva.getNumeroPersonas();
        return new Document().append(HUESPED, huesped).append(HABITACION, habitacion).append(REGIMEN, regimen).append(FECHA_INICIO_RESERVA, fechaInicioReserva).append(FECHA_FIN_RESERVA, fechaFinReserva).append(NUMERO_PERSONAS,numPersonas);
    }

    public static Reserva getReserva(Document documentoReserva) {
        if (documentoReserva == null) {
            return null;
        }
        Huesped huesped = (Huesped) documentoReserva.get(HUESPED);
        Habitacion habitacion = (Habitacion) documentoReserva.get(HABITACION);
        Regimen regimen = (Regimen) documentoReserva.get(REGIMEN);
        LocalDate fechaInicioReserva = LocalDate.parse(documentoReserva.getString(FECHA_INICIO_RESERVA),FORMATO_DIA);
        LocalDate fechaFinReserva = LocalDate.parse(documentoReserva.getString(FECHA_FIN_RESERVA),FORMATO_DIA);
        int numPersonas = documentoReserva.getInteger(NUMERO_PERSONAS);
        return new Reserva(huesped, habitacion, regimen, fechaInicioReserva,fechaFinReserva,numPersonas);
    }

}
