package es.empresa.torneo.persistencia.dao;

import es.empresa.torneo.modelo.Jugador;

import java.util.List;

public interface JugadorDao extends GenericDao<Jugador, Integer, Integer> {

        List<Jugador> findByTeam(int primaryKey);
}
