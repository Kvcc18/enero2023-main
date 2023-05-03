package com.mayab.desarrollo.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mayab.desarrollo.persistence.UserDAO;
import com.mayab.desarrollo.servicios.UsuarioServicio;

public class LoginForm extends JFrame implements ActionListener
{
    JButton b1, b2;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField  textField1, textField2;

    public LoginForm()
    {

        userLabel = new JLabel();
        userLabel.setText("Username");
        textField1 = new JTextField(15);

        passLabel = new JLabel();
        passLabel.setText("Password");

        textField2 = new JPasswordField(15);
        b1 = new JButton("SUBMIT");

        b2 = new JButton("CHANGE PASSWORD");

        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        newPanel.add(b2);

        add(newPanel, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(e -> selectionButtonPressed());
        setTitle("LOGIN FORM");
    }

    private void selectionButtonPressed() {
        ChangePasswordForm form = new ChangePasswordForm();
        form.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String userValue = textField1.getText();
        String passValue = textField2.getText();

        UserDAO dao = new UserDAO();
        UsuarioServicio servicio = new UsuarioServicio(dao);


        if (servicio.login(userValue, passValue)) {
            NewPage page = new NewPage();
            page.setVisible(true);
            JLabel wel_label = new JLabel("Welcome: "+userValue);
            page.getContentPane().add(wel_label);
        }
        else{
            System.out.println("Please enter valid username and password");
        }
    }

}
