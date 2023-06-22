package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.colegioDAO;
import DTO.colegioDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaginaLogin implements ActionListener{
    JFrame frameLogin = new JFrame();
    JPanel painelLogin = new JPanel();
    JTextField campoUsuario = new JTextField();
    JPasswordField campoSenha = new JPasswordField();
    JLabel labelUsuario = new JLabel("Usuário:");
    JLabel labelSenha = new JLabel("Senha:");
    JLabel labelLogin = new JLabel("Login");
    JButton botaoLogin = new JButton("Entrar");
    Container c;

    public void backgroundFrame(){
        frameLogin.setSize(1000,1000);
        frameLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameLogin.setLayout(null);
        frameLogin.setVisible(true);
    }
    public void backgroundFrame2(){
        painelLogin.setBackground(Color.LIGHT_GRAY);
        painelLogin.setBounds(450, 200, 450, 300);

        frameLogin.getContentPane().setLayout(null);//over ride default
        c = frameLogin.getContentPane();
    }
        public void metodoComponentes(){
            campoUsuario.setBounds(570, 300, 200, 33);
            campoSenha.setBounds(570, 345, 200, 33);;
            labelLogin.setBounds(435, 240, 450, 33);
            labelLogin.setFont(new Font("Comic Sans",Font.BOLD,18));
            labelLogin.setHorizontalAlignment(JLabel.CENTER);
            labelUsuario.setBounds(500,300,70,33);
            labelUsuario.setFont(new Font("Comic Sans",Font.BOLD,14));
            labelSenha.setBounds(510, 345, 70, 33);
            labelSenha.setFont(new Font("Comic Sans",Font.BOLD,14));
            botaoLogin.setBounds(615, 420, 90, 40);
            botaoLogin.setFont(new Font("Comic Sans",Font.BOLD,14));
            botaoLogin.addActionListener(this);
        }
        public void metodoLogin(){
            try {
                String senhaString = String.valueOf(campoSenha.getPassword());
                colegioDTO objColegioDTO = new colegioDTO();
                objColegioDTO.setUsuario(campoUsuario.getText());
                objColegioDTO.setSenha(senhaString);
    
                colegioDAO objColegioDAO = new colegioDAO();
                ResultSet rsColegioDAO = objColegioDAO.autenticacaoLogin(objColegioDTO);
    
                if(rsColegioDAO.next()){
                    Pagina2Orientador pagina2Orientador = new Pagina2Orientador();
                    frameLogin.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario ou senha inválidos");
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "View:" + erro);

            }
        }
    PaginaLogin(){
        backgroundFrame2();

        metodoComponentes();
        

        c.add(campoUsuario);
        c.add(labelLogin);
        c.add(campoSenha);
        c.add(labelUsuario);
        c.add(labelSenha);
        c.add(botaoLogin);

        frameLogin.add(painelLogin);
        

        backgroundFrame();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botaoLogin){
            metodoLogin();
        }
    }
    
}






