package a.albertocoscia.entities;

import a.albertocoscia.enums.EventTipe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")

public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate data;
    private String descrizione;
    private int maxPax;
    private EventTipe tipo;
    private String titolo;

    private Event() {
    }

    public Event(String descrizione, int maxPax, EventTipe tipo, String titolo) {
        this.descrizione = descrizione;
        this.maxPax = maxPax;
        this.tipo = tipo;
        this.titolo = titolo;
        this.data = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getMaxPax() {
        return maxPax;
    }

    public void setMaxPax(int maxPax) {
        this.maxPax = maxPax;
    }

    public EventTipe getTipo() {
        return tipo;
    }

    public void setTipo(EventTipe tipo) {
        this.tipo = tipo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
