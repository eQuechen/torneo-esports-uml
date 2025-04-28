package es.empresa.torneo.modelo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})

public class Jugador {

    private int id;
    private String nombre;
    private String apellidos;
    private int idEquipo;
}
