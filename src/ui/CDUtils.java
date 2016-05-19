package ui;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by Onotole on 17.03.2016.
 */
public class CDUtils {

        public static void open(String drive) {
            try {
                // yuri.vetroff@gmail.com
                File file = File.createTempFile("realhowto",".vbs");
                file.deleteOnExit();
                FileWriter fw = new java.io.FileWriter(file);
                String vbs = "Set wmp = CreateObject(\"WMPlayer.OCX\") \n"
                        + "Set cd = wmp.cdromCollection.getByDriveSpecifier(\""
                        + drive + "\") \n"
                        + "cd.Eject";
                fw.write(vbs);
                fw.close();
                Runtime.getRuntime().exec("wscript " + file.getPath()).waitFor();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }
