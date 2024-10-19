package entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "machines")
@NamedNativeQuery(name = "findBetweenDateNative", query = "SELECT * FROM machines WHERE date BETWEEN :d1 AND :d2", resultClass = Machine.class)
@NamedQuery(name = "findBetweenDate", query = "SELECT m FROM Machine m WHERE m.dateAchat BETWEEN :d1 AND :d2")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ref;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    @ManyToOne
    private Salle salle;

    public Machine() {
    }

    public Machine(String ref, Date dateAchat, Salle salle) {
        this.ref = ref;
        this.dateAchat = dateAchat;
        this.salle = salle;
    }

    public Machine(String ref, Date dateAchat) {
        this.ref = ref;
        this.dateAchat = dateAchat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public String getRef() {
        return ref;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public Salle getSalle() {
        return salle;
    }
}
