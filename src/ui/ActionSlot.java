package ui;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Created by anatol on 17.03.16.
 */
public class ActionSlot {
    File[] file;

    public File[] getFile() {
        file = File.listRoots();
        return file;

    }

    public Disk getInfo(File aDrive) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        Disk disk = new Disk();
        disk.setName(fsv.getSystemDisplayName(aDrive));   //getPath().substring(0,2));
        disk.setType(fsv.getSystemTypeDescription(aDrive));
        disk.setTotalSpace(aDrive.getTotalSpace());
        disk.setFreeSpace(aDrive.getFreeSpace());
        disk.setUsableSpace(aDrive.getUsableSpace());

       return disk;
    }
}


