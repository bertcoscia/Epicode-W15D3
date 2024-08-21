package a.albertocoscia.dao;

import a.albertocoscia.entities.Event;
import a.albertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EventDAO {
    private final EntityManager em;

    public EventDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Event event) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(event);
        transaction.commit();
        System.out.println("Evento " + event.getTitolo() + " salvato correttamente");
    }

    public Event getById(String eventId) {
        Event found = em.find(Event.class, eventId);
        if (found == null) throw new NotFoundException(eventId);
        return found;
    }

    public void getByIdAndDelete(String eventId) {
        Event found = getById(eventId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Evento " + found.getTitolo() + " eliminato correttamente");
    }
}
