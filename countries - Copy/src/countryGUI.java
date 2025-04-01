//Javier Colon

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.*;
import java.util.*;


public class countryGUI {

    private JPanel panel1;
    private JLabel infoJL;
    private JLabel imageJL;
    private JButton nextJB;
    private JTextArea answerJTA;
    private JButton checkJB;
    private JLabel scoreJL;

    private ArrayList<country> countryList = new ArrayList<>();
    private int currentCountry = -1;
    private int score = 0;

    HashMap<String, country> countryMap = new HashMap<>();



    public countryGUI() throws IOException {
        ImageIcon img = new ImageIcon("Resources/worldmap.jpg");
        imageJL.setIcon(img);
        infoJL.setText("Countries!");
        loadFile();


        nextJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCountry == 9)
                {
                    System.exit(0);
                } else {

                    currentCountry = (currentCountry + 1) % countryList.size();
                    answerJTA.setVisible(true);
                    checkJB.setVisible(true);
                    scoreJL.setVisible(true);
                    updateGUI();
                }
            }
        });
        checkJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answerJTA.getText().equalsIgnoreCase(countryList.get(currentCountry).getCapital())) {
                    score += 1;
                    scoreJL.setText("<html>" + "Correct!" + "<br>"+
                            "Score: " + score + "/" + countryList.size());
                } else {
                    scoreJL.setText("<html>" + "Incorrect!" + "<br>"+
                            "Score: " + score + "/" + countryList.size());
                }
            }
        });
    }

    private void loadFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("Resources/countries-data.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] data = lines.get(i).split(",");
            countryList.add(new country(data[0], data[1], data[2], data[3]));
        }
        for (country c : countryList) {
        countryMap.put(c.getName(), c);
        }
    }

    private void updateGUI(){
        country current = countryList.get(currentCountry);
        infoJL.setText(current.getName() + "'s capital is ...?");
        ImageIcon img = new ImageIcon("Resources/" + current.getImage());
        imageJL.setIcon(img);

    }


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Countries");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new countryGUI().panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(500, 300

        );
    }
}
