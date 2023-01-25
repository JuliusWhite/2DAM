import entity.Personas;

import jakarta.persistence.*;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

//            Personas dalia = new Personas();
//            dalia.setId(4);
//            dalia.setNombre("Dalia");
//            dalia.setApellido("Abo Sheasha");
//            dalia.setSalario(3450);
//            entityManager.persist(dalia);

            // NamedQuery
            TypedQuery<Personas> namedQuery = entityManager.createNamedQuery("Personas.selectPersonas", Personas.class);
//            namedQuery.setParameter(1, "Daila");
            for (Personas p : namedQuery.getResultList()) {
                System.out.println(p);
            }


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
