package david_seu.snake_game.dao.impl;

import java.sql.*;

public abstract class GenericDao<T> {

    protected Statement stmt;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/snake_game", "postgres", "Liverpool16");
            stmt = con.createStatement();
        } catch(Exception ex) {
            System.out.println("eroare la connect:"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    private final Class<T> entityClass;

//    @Transactional
//    public void create(T entity) {
//        em.persist(entity);
//    }
//
//    public T find(Object id) {
//        return em.find(entityClass, id);
//    }
//
//    @Transactional
//    public void update(T entity) {
//        em.merge(entity);
//    }
//
//    @Transactional
//    public void delete(Object id) {
//        T entity = em.find(entityClass, id);
//        if (entity != null) {
//            em.remove(entity);
//        }
//    }
//
//    public List<T> findAll() {
//        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
//    }
}