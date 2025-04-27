package es.empresa.torneo.control;

import es.empresa.torneo.modelo.Equipo;
import es.empresa.torneo.persistencia.dao.EquipoDao;
import es.empresa.torneo.persistencia.dao.JugadorDao;
import es.empresa.torneo.persistencia.impl.EquipoDaoImpl;
import es.empresa.torneo.persistencia.impl.JugadorDaoImpl;

import java.util.List;

import static es.empresa.torneo.util.ValidateInput.*;

public final class GestorEquipos {

    private static JugadorDao jDao;
    private static EquipoDao eDao;

    private GestorEquipos() {}

    public static void getMenuEquipos(String textMenu) {

        jDao = new JugadorDaoImpl();
        eDao = new EquipoDaoImpl();
        int userInput = 0;

        while (userInput != 3) {
            userInput = validateInt(1, 4, textMenu);

            switch (userInput) {
                case 1 -> registrarEquipo();
                case 2 -> monstrarLista();
                case 4 -> validateClose();
            }
        }
    }

    private static void registrarEquipo() {

        List<Equipo> equipos = eDao.findAll();
        Equipo equipo = new Equipo();

        String nombre = validateString("Introduce el NOMBRE del nuevo equipo");
        String pais   = validateString("Introduce el PAÍS del nuevo equipo");

        equipo.setNombre(nombre);
        equipo.setPais(pais);

        if (equipos.stream().anyMatch(e -> e.getNombre().equalsIgnoreCase(equipo.getNombre()))) {

            System.out.printf("""
                                El equipo %s ya está registrado, utiliza otro nombre.
                                """,
                                equipo.getNombre());
        } else {
            System.out.printf("""
                            %n------------------------------------------
                            Equipo registrado correctamente
                            ------------------------------------------
                            %s
                            
                            Filas afectadas = %s
                            """,
                            equipo.getNombre(),
                            eDao.insertOne(equipo));
        }

        validateContinue();
    }

    private static void monstrarLista() {
        System.out.printf("""
                            %n---------------------------------------
                            Lista de equipos y jugadores:
                            ---------------------------------------%n""");

        System.out.println("Lista de EQUIPOS:");
        eDao.findAll().forEach(System.out::println);

        System.out.println("Lista de JUGADORES:");
        jDao.findAll().forEach(System.out::println);

        validateContinue();
    }

}
