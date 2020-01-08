package si.fri.rso.sn.beers.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "beers")
@NamedQueries(value =
{
    @NamedQuery(name = "Beers.getAll", query = "SELECT b FROM beers b")
})
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private double value;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_inserted")
    private Instant dateInserted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Instant dateInserted) {
        this.dateInserted = dateInserted;
    }
}