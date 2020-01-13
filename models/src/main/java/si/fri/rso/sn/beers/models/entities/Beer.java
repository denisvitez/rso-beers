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

    @Column(name = "alcohol")
    private double alcohol;

    @Column(name = "style")
    private String style;

    @Column(name = "breweryId")
    private int breweryId;

    @Column(name = "name")
    private String name;

    @Column(name = "date_inserted")
    private Instant dateInserted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Instant dateInserted) {
        this.dateInserted = dateInserted;
    }
}