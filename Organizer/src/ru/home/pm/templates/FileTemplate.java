package ru.home.pm.templates;


public class FileTemplate extends Template{
    String parameters[];
    String fields[];


    public FileTemplate(String fld[]) {
        this.fields = fld;
    }

    public FileTemplate(String fld[], String par[]) {
        if (fld.length != par.length) throw new ArrayIndexOutOfBoundsException();
        fields = fld;
        parameters = par;

    }

    @Override
    public String readTemplate() {
        String arr[] = new String[fields.length];
        for (int i = 0; i <fields.length ; i++) {
            arr[i] = fields[i] + parameters[i];
        }
        return String.join("\n", arr);
    }
}
