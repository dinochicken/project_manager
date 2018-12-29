package ru.home;

import ru.home.pm.Project;
import ru.home.pm.ToDoFile;

import java.io.*;
import java.util.regex.Pattern;

public class FileManager{
    public static BufferedReader in;
    public static BufferedWriter out;
    static String DIR = "C://Project";

    public static int getNumberOfProjects(){
        try {
            return new File(DIR).list().length;
        } catch (NullPointerException e){
            return 0;
        }
    }

    public static int getNumberInProject(float id) throws IOException {
        in = new BufferedReader(new FileReader(DIR + findDirById(id)));
        in.readLine();
        in.readLine();
        Pattern p = Pattern.compile("^\\d ");


        int ans = 0;
        while (true){
            String s = in.readLine();
            if (s == null) return ans;
            if (p.matcher(s).find()) ans++;
        }
    }

    public static void createNewProject(Project project)throws IOException{
        out = new BufferedWriter(new FileWriter(new File( DIR + "//" +
                Integer.toString((int)project.getId()) + ". " + project.getText() + ".pf")));
        out.write(project.getTemplate().readTemplate());
        out.close();
    }

    public static String readProject(float id) throws IOException{
        String fileDir = findDirById(id);
        if (fileDir == null) return null;
        in = new BufferedReader(new FileReader(new File(fileDir)));
        String e = in.readLine();
        in.close();
        return fileDir.split("\\. |\\.")[1] + " "  + (e);
    }

    public static void createNewToDoFile(ToDoFile file) throws IOException{
        String fileDir = DIR + "//"
                + file.getTreeParent().getId() + file.getTreeParent().getName();
        out = new BufferedWriter(new FileWriter(new File(fileDir),true));
        String s = file.getText() + file.getTemplate().readTemplate();
        out.write(s);
        out.close();
    }

    public static String readToDoFile(float id) throws IOException{
        String fileDir = findDirById(id);
        int localId;
        float newid;
        int it = 0;
        while (true){
            it++;
             newid = (float) (id * Math.pow(10, it));
            if (String.valueOf(newid).endsWith(".0")){
                localId = (int) newid/10;
                break;
            }
        }

        in = new BufferedReader(new FileReader(new File(fileDir)));
        while (true){
            String s = in.readLine();
            if (s == null) break;
            if (s.startsWith(Integer.toString(localId) + " ")){
                String st = "";
                for (String a = " "; !a.equals(""); a = in.readLine()){
                    st += a + "\n";
                }
                return st;
            }
            for (String a = "1"; !a.equals(""); a = in.readLine());

        }
        return null;
    }

    private static String findDirById(float id) throws IOException{
        String s[] = new File(DIR).list();
        String fileDir = null;
        for (String st : s) {
            if (st.startsWith(Integer.toString((int) id))) fileDir = DIR + "/" + st;
        }
        return fileDir;
    }

}
