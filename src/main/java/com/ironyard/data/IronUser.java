package com.ironyard.data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jasonskipper on 10/31/16.
 */
@Entity
public class IronUser {
    private String username;
    private String password;
    private String displayName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Movie> favs;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> abilities;

    public Set<Movie> getFavs() {
        return favs;
    }

    public void setFavs(Set<Movie> favs) {
        this.favs = favs;
    }

    public Set<Permission> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Permission> abilities) {
        this.abilities = abilities;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public IronUser() {
    }

    public IronUser(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
