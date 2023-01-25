package Java24;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.io.IOException;

public class FreeSpacePanel extends JPanel{
    
    JLabel spaceLabel = new JLabel("Disk space: ");
    JLabel space = new JLabel();
    
    
    public FreeSpacePanel(){
        super();
        add(spaceLabel);
        add(space);
        try{
            setValue();
        } catch(IOException ioe){
            space.setText("Error");
        }      
}
    
    private final void setValue() throws IOException{
        //poluchavane na tekush rezervoar za syhranenie na failove
        Path current = Paths.get("");
        FileStore store = Files.getFileStore(current);
        //otkrivane na praznoto prostranstvo za syhranenie
        long totalSpace = store.getTotalSpace();
        long freeSpace = store.getUsableSpace();
        // poluchavane na stoinost v procent
        double percent = (double)freeSpace / (double)totalSpace * 100;
        percent = (int)(percent*100) / (double)100;
        //zadavane na teksta na etiketa
        space.setText(freeSpace + " free out of " + totalSpace + " (" + percent + "%)");
    }
}