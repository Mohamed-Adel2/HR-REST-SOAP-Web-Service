package iti.jets;

import iti.jets.domain.entities.VacationType;
import iti.jets.persistence.JpaManager;
import jakarta.persistence.EntityManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args)
    {
        EntityManager em = JpaManager.createEntityManager();
        VacationType vacationtype = new VacationType();
        vacationtype.setVacationName("Sick");
        em.getTransaction().begin();
        em.persist(vacationtype);
        em.getTransaction().commit();
    }
}
