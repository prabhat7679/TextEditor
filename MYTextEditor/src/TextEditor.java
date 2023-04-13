import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // create window
    JFrame frame;
    // set menuBar
    JMenuBar menuBar;
    // create textarea
    JTextArea area;
    //
    JMenu File,Edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    TextEditor(){
        frame = new JFrame("MY Window");
        // menubar
        menuBar = new JMenuBar();
        // text area
        area= new JTextArea();
        area.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        // j menu
        File = new JMenu("File");
        Edit= new JMenu("Edit");
        //add File menuItem to menu
        newFile = new JMenuItem("New");
        openFile= new JMenuItem("Open");
        saveFile= new JMenuItem("Save");
        // add action listener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        File.add(newFile);
        File.add(openFile);
        File.add(saveFile);

        // add menuItem to Edit jMenu
        cut = new JMenuItem("cut");
        copy= new JMenuItem("copy");
        paste= new JMenuItem("paste");
        selectAll =new JMenuItem("selectAll");
        close=new JMenuItem("close");
        // add action listener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(selectAll);
        Edit.add(close);


      // add menu to menuBar
         menuBar.add(File);
         menuBar.add(Edit);
        // add menu bar to frame
        frame.setJMenuBar(menuBar);
        //
       // frame.add(area);
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to the panel
        panel.add(area,BorderLayout.CENTER);
        // create scroll panel
        JScrollPane scrollPane= new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);   // add scrollPane to panel
        // add panel to frame
        frame.add(panel);

        // set window or frame
        frame.setVisible(true);
        frame.setBounds(100,100,600, 400);
       // frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if a new file chosen
        if(e.getSource()== newFile){
         TextEditor NewtextEditor= new TextEditor();
        }
        // if a saved file wants to open
        if(e.getSource()== openFile){
            JFileChooser fileChooser= new JFileChooser("c");
            int action = fileChooser.showOpenDialog(null);
            if(action== JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();

                String filePath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader= new BufferedReader(fileReader);
                    String intermediate="",output="";
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    area.setText(output);
                }
                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }

            }
        }
        // if want to save file
        if(e.getSource()== saveFile){
            JFileChooser fileChooser= new JFileChooser("c");
            int action = fileChooser.showSaveDialog(null);
            if(action== JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter= new FileWriter(file);
                    BufferedWriter bufferedWriter=  new BufferedWriter(fileWriter);
                    area.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                     ioException.printStackTrace();
                }
            }
        }

        if(e.getSource()== copy){
              area.copy();
          }
        if(e.getSource()== cut){
            area.cut();
        }
        if(e.getSource()== paste){
             area.paste();
        }
        if(e.getSource()== selectAll){
            area.selectAll();
        }
        if(e.getSource()== close){
             System.exit(0);
        }
    }

    public static void main(String[] args) {
        TextEditor text= new TextEditor();
    }
}