package a.albertocoscia;

import a.albertocoscia.dao.AttendanceDAO;
import a.albertocoscia.dao.EventDAO;
import a.albertocoscia.dao.LocationDAO;
import a.albertocoscia.dao.PersonDAO;
import a.albertocoscia.entities.Attendance;
import a.albertocoscia.entities.Event;
import a.albertocoscia.entities.Location;
import a.albertocoscia.entities.Person;
import a.albertocoscia.enums.AttendanceState;
import a.albertocoscia.enums.EventType;
import a.albertocoscia.enums.PersonGender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Epicode-W15D3");

    public static void main(String[] args) {
        System.out.println("Hello World!");

        EntityManager em = emf.createEntityManager();

        EventDAO ed = new EventDAO(em);
        LocationDAO ld = new LocationDAO(em);
        AttendanceDAO ad = new AttendanceDAO(em);
        PersonDAO pd = new PersonDAO(em);

        Location rockEnSeine = new Location("Parigi", "Rock en Seine");
        Location casaMattarella = new Location("Roma", "Casa di Mattarella");

        //ld.save(rockEnSeine);
        //ld.save(casaMattarella);

        Location rockEnSeineFromDb = ld.getById("0fdd3ea4-750b-4768-a253-6dbb9784eeb4");
        Location casaMattarellaFromDb = ld.getById("a334b1a8-c845-4e41-9fae-9fded2b26ac6");

        Event concerto = new Event("Concerto Fred", "Musica bella", 20000, EventType.PUBBLICO, rockEnSeineFromDb);
        Event concerto2 = new Event("Concerto Lou Reed", "Musica bella x 2", 50000, EventType.PUBBLICO, rockEnSeineFromDb);
        Event cena = new Event("Cena con Mattarella", "Cibo buono", 2, EventType.PRIVATO, casaMattarellaFromDb);

        //ed.save(concerto);
        //ed.save(concerto2);
        //ed.save(cena);

        Person sergioMattarella = new Person(PersonGender.M, "Sergio", "Mattarella", "sergio@mattarella.it", LocalDate.of(1943, 7, 23));
        Person gerryScotti = new Person(PersonGender.M, "Gerry", "Scotti", "gerry@scotti.it", LocalDate.of(1956, 8, 7));

        //pd.save(sergioMattarella);
        //pd.save(gerryScotti);

        Person sergioMattarellaFromDb = pd.getById("d8eba89d-75b3-4e74-869a-c940ad5f53be");
        Person gerryScottiFromDb = pd.getById("758f6c67-fdc9-4eef-9c73-3fc3d252f3ea");
        Event cenaFromDb = ed.getById("811b9497-40ba-4b8a-8c59-233723708736");
        Attendance partecipazioneCenaSM = new Attendance(AttendanceState.CONFERMATA, sergioMattarellaFromDb, cenaFromDb);
        Attendance partecipazioneCenaGS = new Attendance(AttendanceState.CONFERMATA, gerryScottiFromDb, cenaFromDb);

        //ad.save(partecipazioneCenaSM);
        //ad.save(partecipazioneCenaGS);

        Event concertoFromDb = ed.getById("eb9064a5-590f-4605-9e8e-2c862be458f3");
        Attendance partecipazioneConcertoSM = new Attendance(AttendanceState.DA_CONFERMARE, sergioMattarellaFromDb, concertoFromDb);

        //ad.save(partecipazioneConcertoSM);

        em.close();
        emf.close();
    }
}
