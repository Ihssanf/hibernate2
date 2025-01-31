package entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "salles")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;

    @OneToMany(mappedBy = "salle", fetch = FetchType.EAGER)
    private List<Machine> machines;

    public Salle() {
    }

    public Salle(String code) {
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
}
