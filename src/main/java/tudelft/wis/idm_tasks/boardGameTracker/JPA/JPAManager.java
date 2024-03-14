package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tudelft.wis.idm_tasks.boardGameTracker.Credentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class JPAManager {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private void dropAll() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/idm1_user";
        Connection connection = DriverManager.getConnection(url, Credentials.user, Credentials.password);
      //  connection.createStatement()
       // connection.createStatement("DROP TABLE board_game, player, player_board_game");
    }
    public JPAManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        this.entityManager = this.entityManagerFactory.createEntityManager();

    }

    public void persist(Object o) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(o);
        this.entityManager.getTransaction().commit();
    }
}
