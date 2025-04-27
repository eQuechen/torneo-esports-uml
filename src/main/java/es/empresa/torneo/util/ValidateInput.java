package es.empresa.torneo.util;

import es.empresa.torneo.modelo.Equipo;
import es.empresa.torneo.modelo.Jugador;
import es.empresa.torneo.persistencia.config.MysqlConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;

public final class ValidateInput {

    static Scanner scan;

    static {
        scan = new Scanner(System.in);
    }

    private ValidateInput() {}

    public static String validateString(String textToShow) {
        String userInput = "";

        while (userInput.isBlank() || userInput.matches("[0-9]+")) {
            System.out.print("\n" + textToShow + " --> ");
            userInput = scan.nextLine();
        }

        return userInput;
    }

    public static int validateInt(int minInput, int maxInput, String textToShow) {
        int userInput = -99;

        while (userInput < minInput || userInput > maxInput) {
            System.out.print("\n" + textToShow + " --> ");

            if (!scan.hasNextInt()) {
                scan.next();
                continue;
            }

            userInput = scan.nextInt();
        }

        scan.nextLine(); // nextLine() trap.
        return userInput;
    }

    public static void validateContinue() {
        String userInput = "a";

        while (!userInput.isBlank()) {
            System.out.print("\nPulsa ENTER para continuar.");
            userInput = scan.nextLine();
        }
    }

    public static int validateMinId(List lista) {
        OptionalInt minId = OptionalInt.empty();

        if (lista.getFirst() instanceof Jugador) {
            List<Jugador> listaJugadores = new ArrayList<Jugador>(lista);

            minId = listaJugadores.stream()
                .mapToInt(Jugador::getId)
                .min();

        } else {
            List<Equipo> listaEquipos = new ArrayList<Equipo>(lista);

            minId = listaEquipos.stream()
                .mapToInt(Equipo::getId)
                .min();
        }

        return (minId.isPresent()) ? minId.getAsInt() : -1;
    }

    public static int validateMaxId(List lista) {
        OptionalInt maxId = OptionalInt.empty();

        if (lista.getFirst() instanceof Jugador) {

            List<Jugador> listaJugadores = new ArrayList<Jugador>(lista);

            maxId = listaJugadores.stream()
                .mapToInt(Jugador::getId)
                .max();

        } else {

            List<Equipo> listaEquipos = new ArrayList<Equipo>(lista);

            maxId = listaEquipos.stream()
                .mapToInt(Equipo::getId)
                .max();
        }

        return (maxId.isPresent()) ? maxId.getAsInt() : -1;
    }

    public static void validateClose() {
        System.out.printf("""
                            %n#####################################
                                   Finalizando el programa
                            
                            Java: v%s
                            JDBC: v%s
                            
                            Alumno: Emilio B. Quechen Romero
                            #####################################
                            """,
                            System.getProperty("java.version"),
                            MysqlConnector.getInstance().getJdbcVersion());

        scan.close();
        System.exit(0);
    }
}
