package es.empresa.torneo.control;

import es.empresa.torneo.modelo.Equipo;
import es.empresa.torneo.modelo.Jugador;
import es.empresa.torneo.persistencia.dao.EquipoDao;
import es.empresa.torneo.persistencia.dao.JugadorDao;
import es.empresa.torneo.persistencia.impl.EquipoDaoImpl;
import es.empresa.torneo.persistencia.impl.JugadorDaoImpl;

import java.util.List;

import static es.empresa.torneo.util.ValidateInput.*;

public final class GestorJugadores {

    private static JugadorDao jDao;
    private static EquipoDao eDao;

    private GestorJugadores() {}

    public static void getMenuJugadores(String textMenu) {
        jDao = new JugadorDaoImpl();
        eDao = new EquipoDaoImpl();
        int userInput = 0;

        while (userInput != 3) {
            userInput = validateInt(1, 4, textMenu);

            switch (userInput) {
                case 1 -> altaJugador();
                case 2 -> monstrarLista();
                case 4 -> validateClose();
            }
        }
    }

    private static void altaJugador() {
        List<Jugador> jugadores = jDao.findAll();
        List<Equipo> equipos = eDao.findAll();

        Jugador jugador = null;
        Equipo equipo = null;

        int minIdJugadores = validateMinId(jugadores);
        int maxIdJugadores = validateMaxId(jugadores);

        int minIdEquipos = validateMinId(equipos);
        int maxIdEquipos = validateMaxId(equipos);

        if (minIdJugadores == -1)
            System.out.println("No hay jugadores registrados, registra al menos un jugador.");

        if (minIdEquipos == -1)
            System.out.println("No hay equipos registrados, registra al menos un equipo.");

        else {

            System.out.printf("""
                    %n---------------------------------------
                    Lista de jugadores:
                    ---------------------------------------%n""");
            jugadores.forEach(System.out::println);

            int idJugador = validateInt(minIdJugadores, maxIdJugadores, "Introduce el ID del jugador");
            jugador = jugadores.get(idJugador);

            System.out.printf("""
                    %n---------------------------------------
                    Lista de equipos:
                    ---------------------------------------%n""");
            equipos.forEach(System.out::println);

            int idEquipo = validateInt(minIdEquipos, maxIdEquipos, "Introduce el ID del equipo");
            equipo = equipos.get(idEquipo);

            if (equipo.getId() == jugador.getId()) {
                System.out.printf("""
                                El jugador %s %s ya está inscrito al equipo %s, prueba otro equipo.
                                """,
                        jugador.getNombre(),
                        jugador.getApellidos(),
                        equipo.getNombre());

            } else {
                jugador.setEquipo(equipo);
                jDao.updateOne(jugador);
            }

            validateContinue();
        }
    }

    private static void monstrarLista() {
        System.out.printf("""
                            %n---------------------------------------
                            Lista de equipos y jugadores:
                            ---------------------------------------%n""");

        System.out.println("EQUIPOS:");
        eDao.findAll().forEach(System.out::println);

        System.out.println("JUGADORES:");
        jDao.findAll().forEach(System.out::println);

        validateContinue();
    }
}
