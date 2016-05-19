package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by anatol on 17.03.16.
 */
public class Form extends JFrame {
    private JComboBox comboBox1;
    private JButton ejectButton = new JButton();
    private JTextPane textPane;
    private ActionSlot actionSlot = new ActionSlot();
    private JButton okButton = new JButton();
    private JButton reloadButton = new JButton();
    Disk disk = new Disk();

    public Form() {
        super("hello");
        setMinimumSize(new Dimension(320, 240));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        comboBox1 = new JComboBox();
        add(comboBox1, BorderLayout.NORTH);
        add(okButton, BorderLayout.EAST);
        add(textPane, BorderLayout.CENTER);
        add(ejectButton, BorderLayout.WEST);
        add(reloadButton, BorderLayout.SOUTH);
        setComboBox();
        reloadButton.setText("Reload");
        okButton.setText("Select");
        ejectButton.setText("Eject");
        okButton.addActionListener(e -> {
            disk = actionSlot.getInfo((File) comboBox1.getSelectedItem());
            setInfo(disk);
            if (disk.getType().contains("Локальный") || disk.getType().contains("Local")) {
                ejectButton.setEnabled(false);
            }
            else ejectButton.setEnabled(true);
        });
        ejectButton.addActionListener(e -> {
            try {
                eject(disk);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        reloadButton.addActionListener(e -> setComboBox());
    }


    public void setComboBox() {
        comboBox1.removeAllItems();
        int diskCount = actionSlot.getFile().length;
        for (int i = 0; i < diskCount; i++) {
            comboBox1.addItem(actionSlot.getFile()[i]);
        }
    }

    public void setInfo(Disk disk) {
        textPane.setText("Name: " + disk.getName() + ("\n Type: " + disk.getType()) +
                ("\n Total Space: " + disk.getTotalSpace()) + "Mb" + ("\n Free Space: " + disk.getFreeSpace()) + "Mb" +
                ("\n Available: " + disk.getUsableSpace()) + "Mb");
        textPane.setBackground(Color.pink);
    }

    public void eject(Disk disk) throws IOException {
        if (disk.getType().substring(0,2).equals("CD")) {
            CDUtils.open(disk.getName());
        } else {
            Runtime r = Runtime.getRuntime();
            String[] str = {"EjectUSB.exe", disk.getName()};
            r.exec(str);
            setComboBox();
        }

    }
}

