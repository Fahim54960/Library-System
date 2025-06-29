package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class LibraryFrame extends JFrame {
    private JPanel formPanel;
    private BufferedImage backgroundImage;

    public LibraryFrame() {
        super("Library System");
        setBounds(600, 200, 500, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("Resources/modern-library-interior.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Background panel with image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(null);

        // Transparent form panel
        formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setOpaque(false);

        Font labelFont = new Font("Cambria", Font.BOLD, 16);
        int labelX = 30;
        int fieldX = 160;
        int width = 200;
        int height = 25;
        int spacing = 40;

        JLabel lblUserType = new JLabel("User Type:");
        lblUserType.setBounds(labelX, 20, 100, height);
        lblUserType.setFont(labelFont);
        lblUserType.setForeground(Color.WHITE);
        formPanel.add(lblUserType);

        String[] userTypes = { "Student", "Faculty" };
        JComboBox<String> cbUserType = new JComboBox<>(userTypes);
        cbUserType.setBounds(fieldX, 20, width, height);
        formPanel.add(cbUserType);

        JLabel lblUserName = new JLabel("User Name:");
        lblUserName.setBounds(labelX, 20 + spacing, 100, height);
        lblUserName.setFont(labelFont);
        lblUserName.setForeground(Color.WHITE);
        formPanel.add(lblUserName);

        JTextField tfUserName = new JTextField();
        tfUserName.setBounds(fieldX, 20 + spacing, width, height);
        formPanel.add(tfUserName);

        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setBounds(labelX, 20 + spacing * 2, 100, height);
        lblUserId.setFont(labelFont);
        lblUserId.setForeground(Color.WHITE);
        formPanel.add(lblUserId);

        JTextField tfUserId = new JTextField();
        tfUserId.setBounds(fieldX, 20 + spacing * 2, width, height);
        formPanel.add(tfUserId);

        JLabel lblBookTitle = new JLabel("Book Title:");
        lblBookTitle.setBounds(labelX, 20 + spacing * 3, 100, height);
        lblBookTitle.setFont(labelFont);
        lblBookTitle.setForeground(Color.WHITE);
        formPanel.add(lblBookTitle);

        JTextField tfBookTitle = new JTextField();
        tfBookTitle.setBounds(fieldX, 20 + spacing * 3, width, height);
        formPanel.add(tfBookTitle);

        JLabel lblAuthorName = new JLabel("Author's Name:");
        lblAuthorName.setBounds(labelX, 20 + spacing * 4, 130, height);
        lblAuthorName.setFont(labelFont);
        lblAuthorName.setForeground(Color.WHITE);
        formPanel.add(lblAuthorName);

        JTextField tfAuthorName = new JTextField();
        tfAuthorName.setBounds(fieldX, 20 + spacing * 4, width, height);
        formPanel.add(tfAuthorName);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(labelX, 20 + spacing * 5, 100, height);
        lblPassword.setFont(labelFont);
        lblPassword.setForeground(Color.WHITE);
        formPanel.add(lblPassword);

        JPasswordField pfPassword = new JPasswordField();
        pfPassword.setBounds(fieldX, 20 + spacing * 5, width, height);
        formPanel.add(pfPassword);

        JCheckBox chkShow = new JCheckBox("Show Password");
        chkShow.setBounds(fieldX, 20 + spacing * 6, 150, height);
        chkShow.setBackground(new Color(0, 0, 0, 0));
        chkShow.setOpaque(false);
        chkShow.setForeground(Color.WHITE); // ✅ Make it white
        chkShow.addActionListener(e -> {
            pfPassword.setEchoChar(chkShow.isSelected() ? (char) 0 : '•');
        });
        formPanel.add(chkShow);

        // Save button
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(fieldX, 20 + spacing * 7, 100, 30);
        formPanel.add(btnSave);

        // Save action
        btnSave.addActionListener(e -> {
            String userType = (String) cbUserType.getSelectedItem();
            String userName = tfUserName.getText();
            String userId = tfUserId.getText();
            String bookTitle = tfBookTitle.getText();
            String authorName = tfAuthorName.getText();
            String password = new String(pfPassword.getPassword());

            if (userName.isEmpty() || userId.isEmpty() || bookTitle.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!");
                return;
            }

            File dataFolder = new File("Data");
            if (!dataFolder.exists()) dataFolder.mkdir();

            File dataFile = new File(dataFolder, "borrowers.txt");

            try (FileWriter fw = new FileWriter(dataFile, true)) {
                fw.write(userType + "," + userName + "," + userId + "," + bookTitle + "," + authorName + "," + password + "\n");
                JOptionPane.showMessageDialog(this, "Record saved!");

                tfUserName.setText("");
                tfUserId.setText("");
                tfBookTitle.setText("");
                tfAuthorName.setText("");
                pfPassword.setText("");
                cbUserType.setSelectedIndex(0);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving record: " + ex.getMessage());
            }
        });

        backgroundPanel.add(formPanel);
        formPanel.setBounds(0, 0, 500, 480);

        add(backgroundPanel);
    }
}
