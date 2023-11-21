package xyz.project.dao;

public interface BaseDao<I, T, V, R> {

    R save(T request);
    R update(V request);
    R findById(I identifier);
}