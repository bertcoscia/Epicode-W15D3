package a.albertocoscia.entities;

import a.albertocoscia.enums.PersonGender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "people")
public class Person {

    PersonGender gender;
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;

    @OneToMany(mappedBy = "person")
    private List<Attendance> listaPartecipazioni;

}
