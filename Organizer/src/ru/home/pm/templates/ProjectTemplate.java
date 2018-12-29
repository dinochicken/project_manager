package ru.home.pm.templates;

public class ProjectTemplate extends Template {
    String description;

    public ProjectTemplate(String description) {
        this.description = description;
    }

    @Override
    public String readTemplate() {
        return description;
    }
}
