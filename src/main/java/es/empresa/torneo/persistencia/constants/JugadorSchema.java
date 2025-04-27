package es.empresa.torneo.persistencia.constants;

import java.util.List;

public abstract class JugadorSchema {

    public static final String TABLE_NAME       = "jugadores";
    public static final String COL_ID           = "id_jugador";
    public static final String COL_NAME         = "nombre_jugador";
    public static final String COL_LAST_NAME    = "apellidos_jugador";
    public static final String COL_TEAM_FK      = "id_equipo";

    public static final String COLUMNS_SQL_INSERT = String.join(", ", List.of(  COL_NAME,
                                                                                        COL_LAST_NAME,
                                                                                        COL_TEAM_FK));

    public static final String COLUMNS_SQL_UPDATE = String.join(", ", List.of(  COL_NAME.concat(" = ?"),
                                                                                        COL_LAST_NAME.concat(" = ?"),
                                                                                        COL_TEAM_FK.concat(" = ?")));
}
