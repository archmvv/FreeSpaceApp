package Java24;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;

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
        
        String freeSp = String.valueOf(freeSpace);
        String iGotStr = "";
        char comma = ',';
        ArrayList<Character> iGot = new ArrayList<>();
        for(int i = 0; i<freeSp.length();i++){
        iGot.add(freeSp.charAt(i));
        }
        for(int i = freeSp.length(); i>3;i=i-3){
        iGot.add(i-3,comma);
        }
        for(int i = 0;i<iGot.size();i++){
        iGotStr = iGotStr + iGot.get(i);
        }
        
        String totalSp = String.valueOf(totalSpace);
        String iGotStr2 = "";
        ArrayList<Character> iGot2 = new ArrayList<>();
        for(int i = 0; i<totalSp.length();i++){
        iGot2.add(totalSp.charAt(i));
        }
        for(int i = totalSp.length(); i>3;i=i-3){
        iGot2.add(i-3,comma);
        }
        for(int i = 0;i<iGot2.size();i++){
        iGotStr2 = iGotStr2 + iGot2.get(i);
        }
        
        
        space.setText(iGotStr + " free out of " + iGotStr2 + " (" + percent + "%)");
    }
}