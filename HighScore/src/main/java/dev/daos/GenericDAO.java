package dev.daos;

import dev.model.HighScore;

import java.util.List;

public interface GenericDAO <T>{
    //CREATE
    T insert(T t);
    //READ
    T getById(int id);
    //READ
    List<T> getAll();
    //UPDATE
    void update(T t);
    //DELETE
    void delete(int id);
}
