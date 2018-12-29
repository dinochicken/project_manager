package ru.home;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrgWindow extends JFrame {
    private ProjectManager projectManager = new ProjectManager();
    private JLabel dir = new JLabel(Settings.getDir());
    private JMenuBar bar = new JMenuBar();
    private MyJSP scrollPane = new MyJSP(projectManager);

    private Dimension minSize = new Dimension(280, 405);
    private Dimension maximumSize = this.getGraphicsConfiguration().getBounds().getSize();
    private Dimension size = new Dimension(600,820);

    private class MyJSP extends JScrollPane{
        public MyJSP(Component view) {
            super(view);
            this.setSize(580,707);
            this.setLocation(2, 50);

        }

        @Override
        public String toString(){
            return "MyJSP";
        }
    }

    private void initDir(){
        dir.setBounds(2, 10, 580, 40);
        dir.setFont(new Font("Arial", Font.PLAIN, 40));
    }

    private void initBar(){
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Help");

        JMenuItem it = new JMenuItem("New Project");
        it.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        it.addActionListener(e -> {
            System.out.println("created new project");
        });
        m1.add(it);

        it = new JMenuItem("Settings");
        it.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        it.addActionListener(e ->{
            System.out.println("accessed settings");
        });
        m1.add(it);

        it = new JMenuItem("Exit");
        it.addActionListener(e->{
            System.exit(0);
        });
        m1.add(it);

        it = new JMenuItem("Get Help");
        it.addActionListener(e->{
            System.out.println("helped");
        });
        m2.add(it);

        bar.add(m1);
        bar.add(m2);
    }


    public OrgWindow() throws HeadlessException {

        this.setTitle("Diary");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(size);
        this.setMinimumSize(minSize);

        this.setMaximumSize(maximumSize);
        this.setLocationRelativeTo(null);

        this.setLayout(null);

        initDir();
        initBar();

        this.add(dir);
        this.add(scrollPane);
        this.setJMenuBar(bar);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                String s [] = e.paramString().split(" \\(")[1].split(" ")[1].split("x");
                int params[] = {Integer.parseInt(s[0]), Integer.parseInt(s[1].split("\\)")[0])};
                Dimension newSize = new Dimension(params[0], params[1]);

                if (newSize.equals(maximumSize)) {
                    OrgWindow.this.size = maximumSize;
                }
                if (newSize.equals(minSize)) {
                    OrgWindow.this.size = minSize;
                }

                params[0] = ((int) (newSize.getWidth() - OrgWindow.this.size.getWidth()));
                params[1] = ((int) (newSize.getHeight() - OrgWindow.this.size.getHeight()));
                synchronized (getTreeLock()) {
                    for (Component c :
                            OrgWindow.this.getContentPane().getComponents()) {
                        if (c instanceof JMenuBar || c instanceof JLabel) continue;
                        c.setSize(c.getWidth() + params[0], c.getHeight() + params[1]);
                        c.validate();
                    }
                }
                OrgWindow.this.repaint();
                OrgWindow.this.size = newSize;
            }
        });

        this.setVisible(true);

    }



}
