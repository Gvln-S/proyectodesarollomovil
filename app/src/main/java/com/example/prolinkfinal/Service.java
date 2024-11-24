package com.example.prolinkfinal;

public class Service {
    private String title;
    private int availableProviders;
    private int imageResourceId;  // New field for service image

    public Service(String title, int availableProviders, int imageResourceId) {
        this.title = title;
        this.availableProviders = availableProviders;
        this.imageResourceId = imageResourceId;
    }

    public Service(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    // Add getter for imageResourceId
    public int getImageResourceId() { return imageResourceId; }
    // Existing getters remain the same
    public String getTitle() {
        return title;
    }
    public int getAvailableProviders() {
        return availableProviders;
    }
}
