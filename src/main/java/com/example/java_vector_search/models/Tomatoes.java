package com.example.java_vector_search.models;

import java.util.Date;

public class Tomatoes {
    private Viewer viewer;
    private Date lastUpdated;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }
}
