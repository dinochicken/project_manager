package ru.home;

import ru.home.pm.Line;
import ru.home.pm.Project;
import ru.home.pm.ToDoFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager extends JPanel implements Scrollable{
    ArrayList<? super Line> list = new ArrayList<>();
    Dimension size = new Dimension(580, 23);


    public ProjectManager() {
        BoxLayout mgr = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(mgr);
    }

    private List<? super Line> getListFromFiles() throws IOException{
        List<? super Line> list = new ArrayList<>();
        for (int i = 1; i <= FileManager.getNumberOfProjects() ; i++) {
            list.add(new Project(FileManager.readProject((int)i)));
        }
        return list;
    }

    private void refreshList(){

    }

    private void buldFromList() throws Exception{
        for (Object c:
             list) {
            Line l;
            if (c instanceof Project || c instanceof ToDoFile){
                try{
                    l = (Project)c;
                }catch (ClassCastException e){
                    l = (ToDoFile) c;
                }
            } else throw new Exception("piss off");
            l.setMaximumSize(size);
            l.setLocation(0,23*list.indexOf(c));
            l.setBackground(Color.WHITE);
            l.setBorder(null);
            if (l instanceof Project) {
                Project finalL = (Project) l;
                l.addActionListener(e-> {
                    if (finalL.isOpened()) collapse(finalL);
                    else expand(finalL);
                });
            }

            this.add(l);
        }
    }

    private void expand(Project p){
        p.setOpened(true);
        int ind = this.getComponentZOrder(p);
        for (Object l :
                p.getChildren()) {

            if (l instanceof ToDoFile || l instanceof Project) {
                Line line;
                try{
                    line = ((ToDoFile) l);
                }catch (ClassCastException e){
                    line  = ((Project)l );
                }
                line.setParamsByOther(p);
                this.add(line, ++ind);
                this.setSize(this.getWidth(), (int)(this.getHeight() + size.getHeight()));
            }


        }
        this.repaint();
        this.validate();
    }

    private void collapse(Project p){
        p.setOpened(false);
        for (Object l:
                p.getChildren()) {
            this.remove((Component) l);
            this.setSize(this.getWidth(), (int)(this.getHeight() - size.getHeight()));
        }
        this.repaint();
        this.validate();
    }

    public static int giveProjectId(){
        return FileManager.getNumberOfProjects();
    }

    public static int giveFileId(float projectId){
        try {
            return FileManager.getNumberInProject(projectId);
        }catch (IOException e){
            System.out.println("File manager error: " + e.toString());
            return 0;
        }
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(580,list.size() * 23);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 17;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 50;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
