package es.empresa.torneo.vista;

import es.empresa.torneo.control.GestorEquipos;

public final class VistaEquipos {

        private static final String TEXT_MENU = """
                                                 _______________________________________________
                                                |                                               |
                                                | Introduce una opción entre 1 - 4:             |
                                                |                                               |
                                                | [1] - Registrar un equipo                     |
                                                | [2] - Consultar lista de equipos y jugadores  |
                                                | [3] - Volver al menú principal                |
                                                | [4] - Salir                                   |
                                                |_______________________________________________|
                                                """;

        private VistaEquipos() {}

        static void lanzarVista() {
            GestorEquipos.getMenuEquipos(TEXT_MENU);
        }
}
