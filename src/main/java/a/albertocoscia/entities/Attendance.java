package a.albertocoscia.entities;

import a.albertocoscia.enums.AttendanceState;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "partecipazioni")

public class Attendance {

    @Id
    @GeneratedValue
    private UUID id;
    private AttendanceState stato;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}
