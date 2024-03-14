package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import tudelft.wis.idm_tasks.boardGameTracker.Credentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class JPAManager {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private static void dropAll() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/idm1_jpa";
        Connection connection = DriverManager.getConnection(url, Credentials.user, Credentials.password);
        Statement statement = connection.createStatement();
        String query = "DROP TABLE board_game, player, player_board_game;";
        connection.prepareStatement(query).executeQuery();
    }
    public JPAManager() {
        try {
            //dropAll();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        this.entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        this.entityManager = this.entityManagerFactory.createEntityManager();

    }

    public void persist(Object o) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(o);
        this.entityManager.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
