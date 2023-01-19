import entity.Employee;
import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Employee dalia = new Employee();
            dalia.setId(6);
            dalia.setFirstName("Dalia");
            dalia.setLastName("Abo Sheasha");
            entityManager.persist(dalia);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
