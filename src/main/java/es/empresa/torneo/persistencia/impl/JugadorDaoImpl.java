package es.empresa.torneo.persistencia.impl;

import es.empresa.torneo.modelo.Equipo;
import es.empresa.torneo.modelo.Jugador;
import es.empresa.torneo.persistencia.dao.JugadorDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static es.empresa.torneo.persistencia.constants.JugadorSchema.*;

public final class JugadorDaoImpl extends GenericAbstractImpl implements JugadorDao {

    @Override
    public Optional<Jugador> findById(Integer primaryKey) {
        sql = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, COL_ID);
        Jugador object = new Jugador();

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, primaryKey);

            try(ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    object.setId(rs.getInt(COL_ID));
                    object.setNombre(rs.getString(COL_NAME));
                    object.setApellidos(rs.getString(COL_LAST_NAME));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (object.getId() != 0) ? Optional.of(object) : Optional.empty();
    }

    @Override
    public Integer insertOne(Jugador object) {
        sql = "INSERT INTO %s (%s) VALUES (?, ?, ?)".formatted(TABLE_NAME, COLUMNS_SQL_INSERT);

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getApellidos());
            ps.setInt(3, object.getIdEquipo());

            rowCount = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowCount;
    }

    @Override
    public Integer updateOne(Jugador object) {
        sql = "UPDATE %s SET %s WHERE %s = ?".formatted(TABLE_NAME, COLUMNS_SQL_UPDATE, COL_ID);

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getApellidos());
            ps.setInt(3, object.getIdEquipo());

            ps.setInt(4, object.getId());

            rowCount = ps.executeUpdate();

        } catch (SQLException e) {
            rowCount = -1;
            e.printStackTrace();
        }

        return rowCount;
    }

    @Override
    public Integer deleteById(Integer primaryKey) {
        sql = "DELETE FROM %s WHERE %s = ?".formatted(TABLE_NAME, COL_ID);

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, primaryKey);

            rowCount = ps.executeUpdate();

        } catch (SQLException e) {
            rowCount = -1;
            e.printStackTrace();
        }

        return rowCount;
    }

    @Override
    public Integer deleteByObject(Jugador object) {
        return deleteById(object.getId());
    }

    @Override
    public List<Jugador> findAll() {
        sql = "SELECT * FROM %s".formatted(TABLE_NAME);
        List<Jugador> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Jugador object = new Jugador();

                object.setId(rs.getInt(COL_ID));
                object.setNombre(rs.getString(COL_NAME));
                object.setApellidos(rs.getString(COL_LAST_NAME));
                object.setIdEquipo(rs.getInt(COL_TEAM_FK));

                list.add(object);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Jugador> findByTeam(int primaryKey) {
        sql = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, COL_TEAM_FK);
        List<Jugador> list = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, primaryKey);

            try(ResultSet rs = ps.executeQuery()) {

                while (rs.next() && rs.getInt(COL_TEAM_FK) == primaryKey) {
                    Jugador object = new Jugador();

                    object.setId(rs.getInt(COL_ID));
                    object.setNombre(rs.getString(COL_NAME));
                    object.setApellidos(rs.getString(COL_LAST_NAME));
                    object.setIdEquipo(primaryKey);

                    list.add(object);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
