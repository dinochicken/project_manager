package ru.home.pm;

import ru.home.pm.templates.Template;

import javax.swing.*;

public abstract class Line extends JButton {
    float id;
    Template template;
    Project treeParent = null;

    @Override
    public String getText() {
        return super.getText();
    }

    public Line(String text) {
        this(text, null);
    }

    public Line(String text, Template template) {
        super(text);
        this.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public Template getTemplate() {
        return template;
    }

    public Project getTreeParent() {
        return treeParent;
    }

    public void setParamsByOther(Line l){
        this.setMaximumSize(l.getMaximumSize());
        this.setPreferredSize(l.getPreferredSize());
        this.setBackground(l.getBackground());
        this.setBorder(l.getBorder());
    }

    public float getId() {
        return id;
    }
}
