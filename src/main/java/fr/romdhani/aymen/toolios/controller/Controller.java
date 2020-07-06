package fr.romdhani.aymen.toolios.controller;

import java.util.List;

public interface Controller<E> {
    void addToDB(E e);

    void addAllToDB(List<E> list);

    void findFromDB(Long i);

    void deleteAll();

    void deleteFromDB(Long i);

}
