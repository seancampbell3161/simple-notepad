
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MyMenuFrame extends JFrame {
    
    private final JScrollPane jScrollPane1;
    private final JTextArea textArea;
    private final JMenuBar menuBar;
    private final JMenu fileMenu;
    private final JMenuItem OpenFileMenuItem;
    private final JMenuItem SaveFileMenuItem;
    private final JMenuItem ExitFileMenuItem;
    private final JMenu editMenu;
    private final JMenu jMenu1;
    //private final JMenu jMenu2;
    private final JMenuItem ColorMenuItem;
    private final JRadioButtonMenuItem [] fonts;
    private final JCheckBoxMenuItem [] styleItems;
    private final ButtonGroup fontButtonGroup;
    private final JMenu printMenu;
    private final JMenuItem printMenuItem;
    private final JMenu helpMenu;
    private final JMenuItem aboutItem;
    private final JMenuItem homeItem;
    
  //start constructor
    public MyMenuFrame () {
        
        super("My Notepad");
        
        //set border layout
        this.setLayout(new BorderLayout()); 
        
        // create text area, scroll pane, menu bar
        textArea = new JTextArea();

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setViewportView(textArea);
        
        menuBar = new JMenuBar();
        
        //create file component
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        
        //open item for file componenet
        OpenFileMenuItem = new JMenuItem("Open");
        OpenFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke('O', 
                CTRL_DOWN_MASK));
        //action event for open
        OpenFileMenuItem.addActionListener(new ActionListener() {
        
            public void actionPerformed (ActionEvent event) {
                OpenFileMenuItemActionPerformed(event);
            }
    });
        //add open option
        fileMenu.add(OpenFileMenuItem);
        fileMenu.addSeparator();
        
        //create save option
        SaveFileMenuItem = new JMenuItem("Save");
        SaveFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke('S', 
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        //action event for save
        SaveFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                SaveFileMenuItemActionPerformed(event);
            }
        });
        fileMenu.add(SaveFileMenuItem);
        fileMenu.addSeparator();
        
        //exit option
        ExitFileMenuItem = new JMenuItem("Exit");
        ExitFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke('X',
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        //action event for exit
        ExitFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ExitFileMenuItemActionPerformed(event);
            }
        });
        fileMenu.add(ExitFileMenuItem);
        
        //add file to the bar
        menuBar.add(fileMenu);
        
        //create edit section
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic('D');
        
        //color component
        jMenu1 = new JMenu("Color");
        jMenu1.setMnemonic('C');
        
        //color item
        ColorMenuItem = new JMenuItem("Change Color");
        ColorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke('C', 
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        //action event for color item
        ColorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ColorMenuItemActionPerformed(event);
            }
        });
        
        //add color item and add color component to edit
        jMenu1.add(ColorMenuItem);
        editMenu.add(jMenu1);
        editMenu.addSeparator();
        
        //jMenu2 = new JMenu("Font");
        //jMenu2.setMnemonic('F');
        
        //create string  array of font names
        String [] fontNames = {"Times New Roman", "Arial", "Serif"};
        JMenu fontMenu = new JMenu ("Font");
        fontMenu.setMnemonic('F');
        //item handler for fonts
        ItemHandler itemHandler = new ItemHandler();
        
        //radio button for fonts and create button group
        fonts = new JRadioButtonMenuItem [fontNames.length];
        fontButtonGroup = new ButtonGroup();
        
        //iterate through string array
        for(int count=0; count<fontNames.length; count++) {
            
            fonts[count] = new JRadioButtonMenuItem(fontNames[count]);
            fontMenu.add(fonts[count]);
            fontButtonGroup.add(fonts[count]);
            fonts[count].addActionListener(itemHandler);
            
        }
        
        //select font
        fonts[0].setSelected(true);
        fontMenu.addSeparator();
        
        //string array for font styles
        String[] styleNames = {"Bold", "Italic"};
        //checkbox object
        styleItems = new JCheckBoxMenuItem [styleNames.length];
        StyleHandler styleHandler = new StyleHandler ();
        
        //iterate through styles
        for (int count=0; count < styleNames.length; count++) {
            
            styleItems[count] = new JCheckBoxMenuItem (styleNames[count]);
            fontMenu.add(styleItems[count]);
            styleItems[count].addItemListener(styleHandler);
        }
        
        //add font items to edit menu
        editMenu.add(fontMenu);
        
        //create print menu object
        printMenu = new JMenu("Print");
        printMenu.setMnemonic('P');
        
        //create menu item object
        printMenuItem = new JMenuItem("Send to Printer");
        //create shortcut
        printMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke('P', 
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        //action event for print
        printMenuItem.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                
                int value = JOptionPane.showOptionDialog(MyMenuFrame.this,
                        "Do you want to print this file?", "Confirmation",
                JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                        
                        // if user clicked ok show printing dialog
                        if(value == 0) {
                            JOptionPane.showMessageDialog(null,
                             "The file is successfully printed", "Confirmation",
                             JOptionPane.INFORMATION_MESSAGE);
                        }
            }
        });
        
        //add item to print menu
        printMenu.add(printMenuItem);
        
        //create help object
        helpMenu = new JMenu ("Help");
        helpMenu.setMnemonic('H');
        
        //menu item for about
        aboutItem = new JMenuItem("About");
        //shortcut
        aboutItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke('A', 
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        
        //action event for print
        aboutItem.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                //message dialog alert
                JOptionPane.showMessageDialog(null,
                        "This software is developed in 2019\nVersion is 1.0",
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }

            
            
            
        });
        
        //create menu item home
        homeItem = new JMenuItem("Visit Homepage");
        //shortcut
        homeItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke('V',
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        //action event for home
        homeItem.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                
                openWebpage("http://www.microsoft.com");
            }
        }
        ); //end action event
        
        //add items to help component
        helpMenu.add(aboutItem);
        helpMenu.addSeparator();
        helpMenu.add(homeItem);
        
        //add edit, print, help menus to bar
        menuBar.add(editMenu);
        menuBar.add(printMenu);
        menuBar.add(helpMenu);
        
        //create group layout referencing content pane
        //set vertical and horizontal layouts
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
        );


        pack();
        
     //set bar
     setJMenuBar(menuBar);
       
    } //end constructor
    
    //method for open file
    private void OpenFileMenuItemActionPerformed(java.awt.event.ActionEvent e) {                                                 
        
        JFileChooser fc = new JFileChooser ();
        
        //parent frame
        int i = fc.showOpenDialog(this);
        
        if(i == JFileChooser.APPROVE_OPTION) {
            
            File file = fc.getSelectedFile();
            
            //try with resources to ensure resource is close
            try(Scanner scanner = new Scanner
            (new BufferedReader(new FileReader(file)))) {
                
                String content="";
                
                while(scanner.hasNextLine()) {
                    
                    content += scanner.nextLine() + "\n";
                }
                
                textArea.setText(content);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyMenuFrame.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    } //end open method
    
    //method for save file
    private void SaveFileMenuItemActionPerformed(ActionEvent e) {                                                 
        
        //file chooser object
        JFileChooser fc = new JFileChooser ();
        //store answer as int
        int answer = fc.showSaveDialog(this);
        
        //logical path to get file
        if (answer == JFileChooser.APPROVE_OPTION) {
            
            String path = fc.getSelectedFile().getPath();
            
            //try catch to get filename
            try(FileWriter writer = new FileWriter (path)) {
                
                writer.write(textArea.getText());
            
        }   
            catch (IOException ex) {
                Logger.getLogger(MyMenuFrame.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
        
    } //end save method
    
    //method for exit
    private void ExitFileMenuItemActionPerformed(java.awt.event.ActionEvent e) {                                             
        
        System.exit(0);
        
    } //end exit method
    
    //method for color chooser, default to red
    private void ColorMenuItemActionPerformed(java.awt.event.ActionEvent e) {                                              
        
        Color color = JColorChooser.showDialog(this, "select a color", Color.RED);
        textArea.setForeground(color);
        
    } //end choose color method
    
    // item handler for fonts
    private class ItemHandler implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent event) {
            
            //iterate through fonts array and set fonts, default plan 20pt 
            for (int count=0; count<fonts.length; count++) {
                
                if(event.getSource() == fonts[count]) {
                    
                    textArea.setFont(new Font(fonts[count].getText(),
                            Font.PLAIN,20));
                }
            }
            
            repaint(); //redraw the application
            
        } //action performed
        
    } //end item handler
    
    //style handler for bold/italic
    private class StyleHandler implements ItemListener {
        
        @Override
        //item event when user hits check boxes
        public void itemStateChanged (ItemEvent e) {
            
           String name = textArea.getFont().getName(); //get current font
           Font font; //creating a font reference
           
           //iterate through options
           if(styleItems[0].isSelected() && styleItems[1].isSelected()) {
               
               font = new Font(name, Font.BOLD + Font.ITALIC, 20);
               
           }
           
           else if (styleItems[0].isSelected()) {
               
               font = new Font (name, Font.BOLD, 20);
               
           }
           
           else if (styleItems[1].isSelected()) {
               
               font = new Font (name, Font.ITALIC, 20);
               
           }
           
           else {
               
               font = new Font (name, Font.PLAIN, 20);
               
           }
           
           textArea.setFont(font);
           repaint();
           
        }
        
    } //end style handler
    
    //method for opening webpage
    public static void openWebpage (String urlString) {
        try {
        Desktop.getDesktop().browse(new
        URL(urlString).toURI());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
} //end class
