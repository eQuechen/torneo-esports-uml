package es.empresa.torneo.vista;

import es.empresa.torneo.control.GestorJugadores;

public final class VistaJugadores {

        private static final String TEXT_MENU = """
                                                 _______________________________________________
                                                |                                               |
                                                | Introduce una opción entre 1 - 4:             |
                                                |                                               |
                                                | [1] - Añadir jugador a un equipo              |
                                                | [2] - Consultar lista de equipos y jugadores  |
                                                | [3] - Volver al menú principal                |
                                                | [4] - Salir                                   |
                                                |_______________________________________________|
                                                """;

        private VistaJugadores() {}

        static void lanzarVista() {
            GestorJugadores.getMenuJugadores(TEXT_MENU);
        }

}
