package ru.aolisov.traveling.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 3/16/2016.
 */
@Entity
public class Article implements PersistedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;
    private String url;
    private int year;
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private List<Place> places = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags = new ArrayList<>();

    public Article() {
    }

    public Article(String name, String url, int year, String photo, List<Place> places, List<String> tags) {
        this.name = name;
        this.url = url;
        this.year = year;
        this.photo = photo;
        this.places = places;
        this.tags = tags;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    //endregion

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", year=" + year +
                ", photo='" + photo + '\'' +
                ", places=" + places +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (year != article.year) return false;
        if (name != null ? !name.equals(article.name) : article.name != null) return false;
        if (url != null ? !url.equals(article.url) : article.url != null) return false;
        if (photo != null ? !photo.equals(article.photo) : article.photo != null) return false;
        if (places != null ? !places.equals(article.places) : article.places != null) return false;
        return tags != null ? tags.equals(article.tags) : article.tags == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (places != null ? places.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
