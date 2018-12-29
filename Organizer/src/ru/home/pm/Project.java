package ru.home.pm;


import ru.home.ProjectManager;
import ru.home.pm.templates.ProjectTemplate;
import ru.home.pm.templates.Template;

import java.util.ArrayList;

public class Project extends Line {
    private boolean isOpened = false;

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    private ArrayList<? super Line> children = new ArrayList<>();

    public Project(String text) {
        super(text);
        this.id = ProjectManager.giveProjectId();
        template = null;
    }

    public Project(String text, Template template) {
        super(text, template);
        if (template instanceof ProjectTemplate) this.template = template;
        else template = null;
    }

    public ArrayList<? super Line> getChildren() {
        return children;
    }

    @Override
    public Template getTemplate() {
        return super.getTemplate();
    }

    @Override
    public float getId() {
        return super.getId();
    }
}
