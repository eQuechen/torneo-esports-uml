package es.empresa.torneo.vista;

import es.empresa.torneo.util.ValidateInput;

public final class VistaPrincipal {

    private static final String TEXT_MENU = """
                                             _______________________________________________
                                            |                                               |
                                            | Introduce una opción entre 1 - 3:             |
                                            |                                               |
                                            | [1] - Acceder al menú de gestión de jugadores |
                                            | [2] - Acceder al menú de gestión de equipos   |
                                            | [3] - Salir                                   |
                                            |_______________________________________________|
                                            """;

    private VistaPrincipal() {}

    public static void lanzarVista() {

        while (true) {
            int userInput = ValidateInput.validateInt(1, 3, TEXT_MENU);

            switch (userInput) {
                case 1 -> VistaJugadores.lanzarVista();
                case 2 -> VistaEquipos.lanzarVista();
                case 3 -> ValidateInput.validateClose();
            }
        }
    }
}
