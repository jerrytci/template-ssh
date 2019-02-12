package practice.dao;

import java.util.List;

public interface BasicDao {
    <T> T get(Class<T> tClass, long id);

    <T> List<T> get(Class<T> tClass, List ids);

    <T> List<T> getHqlResult(String hql);

    <T> List<T> list(Class<T> tClass);

    <T> List<T> list(Class<T> tClass, String orderBy);

    <T> void save(T t);

    <T> void delete(T t);
}
