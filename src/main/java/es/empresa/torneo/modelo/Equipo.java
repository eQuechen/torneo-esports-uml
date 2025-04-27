package es.empresa.torneo.modelo;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})

public class Equipo {

    private int id;
    private String nombre;
    private String pais;
    private List<Jugador> jugadores;
}
