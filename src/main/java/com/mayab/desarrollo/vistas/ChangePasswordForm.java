package com.mayab.desarrollo.vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.mayab.desarrollo.persistence.UserDAO;
import com.mayab.desarrollo.servicios.UsuarioServicio;
public class ChangePasswordForm extends JFrame implements ActionListener{


    JButton b1, b2;
    JPanel newPanel;
    JLabel userLabel, oldPassLabel, newPassLabel;
    final JTextField  textField1, textField2, textField3;

    public ChangePasswordForm() throws HeadlessException {
        //public
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Password");
        setSize(300,100);

        userLabel = new JLabel();
        userLabel.setText("Username");

        textField1 = new JTextField(15);

        //create label for old password
        oldPassLabel = new JLabel();
        oldPassLabel.setText("Old Password");

        textField2 = new JPasswordField(15);

        //create label for new password
        newPassLabel = new JLabel();
        newPassLabel.setText("New Password");

        textField3 = new JPasswordField(15);

        b1 = new JButton("SUBMIT");

        b2 = new JButton("CANCEL");

        newPanel = new JPanel(new GridLayout(4, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(oldPassLabel);
        newPanel.add(textField2);
        newPanel.add(newPassLabel);
        newPanel.add(textField3);

        newPanel.add(b1);
        newPanel.add(b2);

        add(newPanel, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(e -> cancelChanges());
        setTitle("CHANGE PASSWORD FORM");
    }


    public void cancelChanges(){
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String userValue = textField1.getText();
        String oldPassValue = textField2.getText();
        String newPassValue = textField3.getText();

        UserDAO dao = new UserDAO();
        UsuarioServicio servicio = new UsuarioServicio(dao);


        if (servicio.changePassword(userValue, oldPassValue, newPassValue)) {

            NewPage page = new NewPage();

            page.setVisible(true);

            JLabel wel_label = new JLabel("Your password change has been successful: "+userValue);
            page.getContentPane().add(wel_label);
        }
        else{
            System.out.println("Please enter valid username and password");
        }
    }
}
