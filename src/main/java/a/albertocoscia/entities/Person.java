package a.albertocoscia.entities;

import a.albertocoscia.enums.PersonGender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
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


}
