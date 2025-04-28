package es.empresa.torneo.persistencia.impl;

import es.empresa.torneo.modelo.Equipo;
import es.empresa.torneo.persistencia.dao.EquipoDao;
import es.empresa.torneo.persistencia.dao.JugadorDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static es.empresa.torneo.persistencia.constants.EquipoSchema.*;

public final class EquipoDaoImpl extends GenericAbstractImpl implements EquipoDao {

    private JugadorDao jDao;

    public EquipoDaoImpl() {
        jDao = new JugadorDaoImpl();
    }

    @Override
    public Optional<Equipo> findById(Integer primaryKey) {
        sql = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, COL_ID);
        Equipo object = new Equipo();

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, primaryKey);

            try(ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    object.setId(rs.getInt(COL_ID));
                    object.setNombre(rs.getString(COL_NAME));
                    object.setPais(rs.getString(COL_COUNTRY));
                    object.setJugadores(jDao.findAll());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (object.getId() != 0) ? Optional.of(object) : Optional.empty();
    }

    @Override
    public Integer insertOne(Equipo object) {
        sql = "INSERT INTO %s (%s) VALUES (?, ?)".formatted(TABLE_NAME, COLUMNS_SQL_INSERT);

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getPais());

            rowCount = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowCount;
    }

    @Override
    public Integer updateOne(Equipo object) {
        sql = "UPDATE %s SET %s WHERE %s = ?".formatted(TABLE_NAME, COLUMNS_SQL_UPDATE, COL_ID);

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getPais());

            ps.setInt(3, object.getId());

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
    public Integer deleteByObject(Equipo object) {
        return deleteById(object.getId());
    }

    @Override
    public List<Equipo> findAll() {
        sql = "SELECT * FROM %s".formatted(TABLE_NAME);
        List<Equipo> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Equipo object = new Equipo();

                object.setId(rs.getInt(COL_ID));
                object.setNombre(rs.getString(COL_NAME));
                object.setPais(rs.getString(COL_COUNTRY));
                object.setJugadores(jDao.findByTeam(object.getId()));

                list.add(object);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
