package ru.aolisov.traveling.data.entity;

import javax.persistence.*;

/**
 * Created by Alex on 3/14/2016.
 */

@Entity
public class Place implements PersistedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;
    private double x;
    private double y;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    public Place() {
    }

    public Place(String name, Country country, double x, double y) {
        this.name = name;
        this.country = country;
        this.x = x;
        this.y = y;
    }

    //region getters/setters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    //endregion

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (id != place.id) return false;
        if (Double.compare(place.x, x) != 0) return false;
        if (Double.compare(place.y, y) != 0) return false;
        if (name != null ? !name.equals(place.name) : place.name != null) return false;
        return country != null ? country.equals(place.country) : place.country == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
