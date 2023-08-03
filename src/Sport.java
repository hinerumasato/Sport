import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Sport extends JFrame implements ActionListener {

    private JPanel cardPanel                = new JPanel(new CardLayout());
    private JTextField nameTextField        = new JTextField(30);
    private JCheckBox cricketCheckBox       = new JCheckBox("Cricket");
    private JCheckBox badmintonCheckBox     = new JCheckBox("Badminton");
    private JCheckBox golfCheckBox          = new JCheckBox("Golf");
    private JRadioButton maleRadioButton    = new JRadioButton("Male");
    private JRadioButton femaleRadioButton  = new JRadioButton("Female");
    private JTextArea commentArea           = new JTextArea();
    private JTextArea resultArea            = new JTextArea();

    public Sport() {
        setTitle("Khao sat so thich the thao");
        setSize(500, 500);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem sportMenuItem = new JMenuItem("Trac Nghiem So Thich");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        menu.add(sportMenuItem);
        menu.add(exitMenuItem);
        menuBar.add(menu);
        sportMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(menuBar);
        
        JLabel nameLabel = new JLabel("Name (*)");
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        
        JPanel favoriteSportsPanel = new JPanel();
        favoriteSportsPanel.setBorder(BorderFactory.createTitledBorder("Favorite Sports (*)"));
        favoriteSportsPanel.add(cricketCheckBox);
        favoriteSportsPanel.add(badmintonCheckBox);
        favoriteSportsPanel.add(golfCheckBox);

        JLabel genderLabel = new JLabel("Gender (*):");
        ButtonGroup group = new ButtonGroup();
        group.add(maleRadioButton);
        group.add(femaleRadioButton);
        JPanel genderPanel = new JPanel();
        genderPanel.add(genderLabel);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.setBorder(BorderFactory.createTitledBorder("Comments (tuy y):"));
        commentPanel.add(commentArea, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        submitButton.addActionListener(this);
        resetButton.addActionListener(this);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Hien thi ket qua"));
        resultPanel.add(resultArea, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(namePanel);
        centerPanel.add(favoriteSportsPanel);
        centerPanel.add(genderPanel);
        centerPanel.add(commentPanel);
        centerPanel.add(buttonPanel);
        centerPanel.add(resultPanel);

        JPanel sportPanel = new JPanel(new BorderLayout());
        sportPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel blankPanel = new JPanel();

        cardPanel.add(blankPanel);
        cardPanel.add(sportPanel);


        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);


        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);    

    }

    public boolean canSubmit() {
        if(nameTextField.getText().trim().equals(""))
            return false;
        if(!cricketCheckBox.isSelected() && !badmintonCheckBox.isSelected() && !golfCheckBox.isSelected())
            return false;
        if(!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
            return false;
        return true;
    }

    public String getResultText() {
        String result = "";
        if(canSubmit()) {
            String selectedSports = "";
            String gender = "";

            if(cricketCheckBox.isSelected())
                selectedSports += "Cricket\t";
            if(badmintonCheckBox.isSelected())
                selectedSports += "Badminton\t";
            if(golfCheckBox.isSelected())
                selectedSports += "Golf\t";

            if(maleRadioButton.isSelected())
                gender += "Male";
            else gender += "Female";

            result += nameTextField.getText() + "\t" + selectedSports + gender + '\n'; 
            result += commentArea.getText();
        }
        else result = "Dien du thong tin vao truong dau (*)";
        return result;
    }

    public void resetComponents() {
        nameTextField.setText("");
        commentArea.setText("");

        cricketCheckBox.setSelected(false);
        badmintonCheckBox.setSelected(false);
        golfCheckBox.setSelected(false);

        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
    }

    public static void main(String[] args) {
        new Sport();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Exit")) {
            System.exit(0);
        }

        else if(command.equals("Trac Nghiem So Thich")) {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.next(cardPanel);
        }

        else if (command.equals("Submit")) {
            resultArea.setText(getResultText());
        }

        else if(command.equals("Reset")) {
            resetComponents();
        }
    }
}
