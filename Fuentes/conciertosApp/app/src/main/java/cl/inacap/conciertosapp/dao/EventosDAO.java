package cl.inacap.conciertosapp.dao;

import java.util.List;

import cl.inacap.conciertosapp.dto.Evento;

public interface EventosDAO {

    List<Evento> getAll();
    void add(Evento e);

}
