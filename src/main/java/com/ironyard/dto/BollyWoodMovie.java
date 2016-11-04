package com.ironyard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BollyWoodMovie {
    @JsonProperty(value = "Title")
    private String title;

    @JsonProperty(value = "Description")
    private String Description;

    @JsonProperty(value = "PosterPath")
    private String url;

    @JsonProperty(value = "Runtime")
    private int minutes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
