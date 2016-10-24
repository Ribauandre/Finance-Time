/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalpasswordapp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPasswordField;

/**
 *
 * @author bryaningram
 */
public class Registration extends JFrame {
    
    private JLabel label, message;
    private JTextField inputName;
    private JPasswordField inputPass;
    private JButton register, cancel;
    public String initUserName;
    private String initPassword;
    
    public Registration()
    {
        super("Registration");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        label = new JLabel("Please enter Username and Password.");
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 0;
        add(label, c);
        
        message = new JLabel("Username: ");
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(message, c);
        inputName = new JTextField();
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        add(inputName, c);
        message = new JLabel("Password: ");
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(message, c);
        inputPass = new JPasswordField();
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        add(inputPass, c);
        
        cancel = new JButton("Cancel");
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        add(cancel, c);
        cancel.addActionListener((ActionEvent event) -> {
            inputName.setText("");
            inputPass.setText("");
            inputName.setEditable(true);
            inputPass.setEditable(true);
        });
        
        register = new JButton("Register");
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 3;
        add(register, c);
        register.addActionListener((ActionEvent event) -> {
            initUserName = inputName.getText();
            initPassword = inputPass.getText();
        });

        Handler handler = new Handler();
        register.addActionListener(handler);
        
        register.addActionListener(new ActionListener()
         {
             @Override
             public void actionPerformed(ActionEvent event)
             {
                 try 
                 {
                     BufferedWriter bw = new BufferedWriter(new FileWriter(new File("register.text"), true));
                     bw.write(inputName.getText());
                     bw.newLine();
                     bw.write(inputPass.getText());
                     bw.newLine();
                     bw.close();
                 } catch (IOException e) 
                   {
                        System.out.println("Error is " + e);
                   }
             }
         });
    }
    
    class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (inputName.getText().equals("") || inputPass.getText().equals(""))
            {
                register.setEnabled(false);
                System.out.println("Enter Username and Password");
                register.setEnabled(true);
            }
            else
            {
                register.setEnabled(true);
                if (initUserName == initUserName && initPassword == initPassword)
                {
                    label.setText("Registration Successfully Completed");
                    inputName.setEditable(false);
                    inputPass.setEditable(false);
                    inputName.setBackground(Color.LIGHT_GRAY);
                    inputPass.setBackground(Color.LIGHT_GRAY);
                }
            }
            
        }
    }
    
    public String getUserName()
    {
        initUserName = "Username";
        return initUserName;
    }
    
    public String getPassword()
    {
        initPassword = "Password";
        return initPassword;
    }
    

}
