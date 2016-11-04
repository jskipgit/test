package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jasonskipper on 10/31/16.
 */
@Entity
public class Movie {
    private String title;
    private String rating;
    private String remoteUrl;
    private String description;
    private int minutes;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Movie() {
    }

    public Movie(String title, String rating, String remoteUrl, String description, int minutes) {
        this.title = title;
        this.rating = rating;
        this.remoteUrl = remoteUrl;
        this.description = description;
        this.minutes = minutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return String.format("( %s : %s : %s)", getId(), getTitle(), getRating());
    }
}
