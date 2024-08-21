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
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Epicode-W15D3");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Faker faker = new Faker();

        EntityManager em = emf.createEntityManager();

        EventDAO ed = new EventDAO(em);
        LocationDAO ld = new LocationDAO(em);
        AttendanceDAO ad = new AttendanceDAO(em);
        PersonDAO pd = new PersonDAO(em);

        Location rockEnSeine = new Location("Parigi", "Domaine National de Saint-Cloud");
        Location casaMattarella = new Location("Roma", "Casa di Mattarella");

        Location rockEnSeineFromDb = ld.getById("c773ff82-a33c-4627-9d06-eadd947ef8db");
        Location casaMattarellaFromDb = ld.getById("e0b81ffb-f722-4ad8-8aaf-d373189aedc6");
        Event concerto = new Event("Concerto Fred", "Musica bella", 20000, EventType.PUBBLICO, rockEnSeineFromDb);
        Event cena = new Event("Cena con Mattarella", "Cibo buono", 2, EventType.PRIVATO, casaMattarellaFromDb);
        Event concerto2 = new Event("Concerto Lou Reed", "Musica bella x 2", 50000, EventType.PUBBLICO, rockEnSeineFromDb);

        Person sergioMattarella = new Person(PersonGender.M, "Sergio", "Mattarella", "sergio@mattarella.it", LocalDate.of(1943, 7, 23));
        Person gerryScotti = new Person(PersonGender.M, "Gerry", "Scotti", "gerry@scotti.it", LocalDate.of(1956, 8, 7));

        Person sergioMattarellaFromDb = pd.getById("797d8658-025e-4f87-b7b9-235f7d7058c6");
        Person gerryScottiFromDb = pd.getById("9d5ea538-e525-43df-aff4-6aaff8752cbe");
        Event cenaFromDb = ed.getById("9867ca32-f000-4446-8746-25ae92f8f15f");
        Attendance partecipazioneCena = new Attendance(AttendanceState.CONFERMATA, gerryScottiFromDb, cenaFromDb);

        Event concertoFromDb = ed.getById("f75263e9-d1a3-4038-ac7f-df2453779efc");
        Attendance partecipazioneConcerto = new Attendance(AttendanceState.DA_CONFERMARE, sergioMattarellaFromDb, concertoFromDb);

        Attendance partecipazioneGSCena = ad.getById("5053ce84-db18-4e9e-96e3-bb3f46e6f68b");
        Attendance partecipazioneGSConcerto = ad.getById("0a7dae01-c9a5-4eef-9d8d-d42ce7a754ca");
        List<Attendance> partecipazioniSM = new ArrayList<>();
        partecipazioniSM.add(partecipazioneGSCena);
        partecipazioniSM.add(partecipazioneGSConcerto);
        sergioMattarella.setListaPartecipazioni(partecipazioniSM);

        Event concerto2FromDb = ed.getById("4834ead2-86be-491e-b032-19aebffdd95e");

        List<Event> eventiRockEnSeine = new ArrayList<>();
        eventiRockEnSeine.add(concertoFromDb);
        eventiRockEnSeine.add(concerto2FromDb);
        rockEnSeineFromDb.setListaEventi(eventiRockEnSeine);

        System.out.println(sergioMattarellaFromDb);
        System.out.println(rockEnSeineFromDb);
        em.close();
        emf.close();
    }
}
