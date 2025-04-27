package es.empresa.torneo.persistencia.constants;

import java.util.List;

public abstract class EquipoSchema {

    public static final String TABLE_NAME   = "equipos";
    public static final String COL_ID       = "id_equipo";
    public static final String COL_NAME     = "nombre_equipo";
    public static final String COL_COUNTRY  = "pais_equipo";

    public static final String COLUMNS_SQL_INSERT = String.join(", ", List.of(  COL_NAME,
                                                                                        COL_COUNTRY));

    public static final String COLUMNS_SQL_UPDATE = String.join(", ", List.of(  COL_NAME.concat(" = ?"),
                                                                                        COL_COUNTRY.concat(" = ?")));
}
