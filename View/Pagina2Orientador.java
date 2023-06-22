package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DAO.colegioDAO;
import DAO.conexaoDAO;
import DTO.colegioDTO;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Pagina2Orientador implements ActionListener{
    Connection conn;
    PreparedStatement pstm;
    DefaultTableModel model;
    JFrame frame1 = new JFrame();
    JTable table1 = new JTable();
    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();
    JTextField campoSerie = new JTextField();
    JTextField campoInserirAluno = new JTextField();
    
    JTextField campoTroca = new JTextField();
    JComboBox<String> caixaTurma;
    JComboBox<String> caixaEnsino;
    JComboBox<String> caixaOpcInsEdi;
    JComboBox<String> caixaOpcInsEdi2;
    JLabel labelSerie = new JLabel();
    JLabel labelTurma = new JLabel();
    JLabel labelGrau = new JLabel();
    JLabel labelAno = new JLabel();
    JLabel labelEnsino = new JLabel();
    JLabel labelTroca = new JLabel();
    JButton botaoPesquisar = new JButton("Pesquisar");
    JButton botaoInserirAluno = new JButton("Inserir Aluno");
    JButton botaoEditar = new JButton("Editar");
    JButton botaoConfirmarInserirAluno = new JButton("Confirmar Nome");
    JButton botaoConfirmarEditar = new JButton("Confirmar Edição");
    JButton botaoConfirmarInserirNota = new JButton("Confirmar Nota");
    String[] exemplos = {"A","B"};
    String[] ensinos = {"Fundamental", "Médio"};
    String[] opcoesInsEdi = {"Nota 1","Nota 2","Nota 3"};
    String[] opcoesInsEdi2 = {"Nome", "Nota 1","Nota 2","Nota 3"};
    String nome;
    boolean confirmacaoRepeticao = false;
    int referenciaListaAnterior;
    String pulledId;
    int pulledIdInt;
    int indexCaixa2;

    public void frameBackground1(){
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel1.setBackground(Color.lightGray);
        painel1.setBounds(700,0,700,900);
        painel2.setBackground(Color.lightGray);
        painel2.setBounds(0,0,600,150);
        painel3.setBounds(0,230,600,560);
        painel3.setBackground(Color.lightGray);
    }
    public void frameBackground2(){
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setSize(1500,1000);
        frame1.setLayout(null);
        frame1.setVisible(true);
    }
    public void tableMethod(){
        String columnNames[] = {"ID", "Nome", "Nota1","Nota2","Nota3", "Media", "condicao"};
        String[][] data = {{"ID", "Nome", "Nota 1","Nota 2","Nota 3", "Média", "Condição"}};
        model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);

        table1.setRowHeight(25);
        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(60);
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(5).setPreferredWidth(60);

        columnModel.getColumn(0).setCellRenderer(rightRenderer);
        columnModel.getColumn(1).setCellRenderer(rightRenderer);
        columnModel.getColumn(2).setCellRenderer(rightRenderer);
        columnModel.getColumn(3).setCellRenderer(rightRenderer);
        columnModel.getColumn(4).setCellRenderer(rightRenderer);
        columnModel.getColumn(5).setCellRenderer(rightRenderer);
        table1.setEnabled(false);
    }
    public void positionMethod(){
        frame1.getContentPane().setLayout(null);//over ride default
        Container c = frame1.getContentPane();
        c.add(campoSerie);
        c.add(campoInserirAluno);
        c.add(caixaTurma);
        c.add(caixaEnsino);
        c.add(caixaOpcInsEdi);
        c.add(caixaOpcInsEdi2);
        c.add(labelSerie);
        c.add(labelTurma);
        c.add(labelGrau);
        c.add(labelAno);
        c.add(labelEnsino);
        c.add(labelTroca);
        c.add(botaoPesquisar);
        c.add(botaoInserirAluno);
        c.add(botaoEditar);
        c.add(botaoConfirmarInserirAluno);
        c.add(botaoConfirmarEditar);
        c.add(botaoConfirmarInserirNota);
        c.add(campoTroca);
        
        campoInserirAluno.setVisible(false);
        caixaOpcInsEdi.setVisible(false);
        caixaOpcInsEdi2.setVisible(false);
        botaoConfirmarInserirAluno.setVisible(false);
        botaoConfirmarEditar.setVisible(false);
        botaoConfirmarInserirNota.setVisible(false);
        campoTroca.setVisible(false);
        labelTroca.setVisible(false);
    }
    public void componentsMethod(){
        campoSerie.setHorizontalAlignment(JTextField.CENTER);
        campoSerie.setFont(new Font("Comic Sans", Font.BOLD, 14));
        campoSerie.setBounds(70,60,30,30);
        campoInserirAluno.setBounds(70,500,250,30);
        campoTroca.setBounds(240,450,50,30);
        labelSerie.setText("Série");
        labelSerie.setBounds(70,30,60,30);
        labelTurma.setText("Turma");
        labelTurma.setBounds(177,30,60,30);
        labelGrau.setText("o");
        labelGrau.setBounds(110,42,10,30);
        labelAno.setText("Ano");
        labelAno.setBounds(120,60,40,30);
        labelAno.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelEnsino.setText("Ensino");
        labelEnsino.setBounds(310,30,60,30);
        labelTroca.setText("Mudar para: (ID)");
        labelTroca.setBounds(120,450,100,30);
        caixaTurma = new JComboBox<String>(exemplos);
        caixaTurma.setBounds(176,60,50,30);
        caixaEnsino = new JComboBox<String>(ensinos);
        caixaEnsino.setBounds(260,60,150,30);
        caixaOpcInsEdi = new JComboBox<>(opcoesInsEdi);
        caixaOpcInsEdi.setBounds(350,500,100,30);
        caixaOpcInsEdi2 = new JComboBox<>(opcoesInsEdi2);
        caixaOpcInsEdi2.setBounds(350,500,100,30);
        botaoPesquisar.setBounds(450,60,120,30);
        botaoPesquisar.addActionListener(this);
        botaoInserirAluno.setBounds(0,250,180,30);
        botaoInserirAluno.addActionListener(this);
        botaoEditar.setBounds(0,290,100,30);
        botaoEditar.addActionListener(this);
        botaoConfirmarInserirAluno.setBounds(470,500,130,30);
        botaoConfirmarInserirAluno.addActionListener(this);
        botaoConfirmarEditar.setBounds(460,500,140,30);
        botaoConfirmarEditar.addActionListener(this);
        botaoConfirmarInserirNota.setBounds(470,500,130,30);
        botaoConfirmarInserirNota.addActionListener(this);
    }
    public void funcaoEditar(){
        pulledId = campoTroca.getText();
        pulledIdInt = Integer.parseInt(pulledId);
        indexCaixa2 = caixaOpcInsEdi2.getSelectedIndex();
        String sql = "update aluno set nome where idauluno = ?";
        conn = new conexaoDAO().conectaBD();

        if(indexCaixa2 == 0){
            String nomeString = campoInserirAluno.getText();
            String stringId = campoTroca.getText();
            int intId = Integer.parseInt(stringId);

            sql = "update aluno set nome = ? where idaluno = ?";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, nomeString);
                pstm.setInt(2, intId);

                pstm.execute();
                pstm.close();
                
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Update: " + erro);
            }
        }

        if(indexCaixa2 == 1){
            String stringNota1 = campoInserirAluno.getText();
            float nota1Int = Float.parseFloat(stringNota1);
            String stringId = campoTroca.getText();
            int intId = Integer.parseInt(stringId);

            sql = "update aluno set nota1 = ? where idaluno = ?";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setFloat(1, nota1Int);
                pstm.setInt(2, intId);

                pstm.execute();
                pstm.close();
                
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Update: " + erro);
            }
        }
        if(indexCaixa2 == 2){
            String stringNota2 = campoInserirAluno.getText();
            float nota2Float = Float.parseFloat(stringNota2);
            String stringId = campoTroca.getText();
            int intId = Integer.parseInt(stringId);

            sql = "update aluno set nota2 = ? where idaluno = ?";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setFloat(1, nota2Float);
                pstm.setInt(2, intId);

                pstm.execute();
                pstm.close();
                
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Update: " + erro);
            }
        }
        if(indexCaixa2 == 3){
            String stringNota3 = campoInserirAluno.getText();
            float nota2Float = Float.parseFloat(stringNota3);
            String stringId = campoTroca.getText();
            int intId = Integer.parseInt(stringId);

            sql = "update aluno set nota3 = ? where idaluno = ?";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setFloat(1, nota2Float);
                pstm.setInt(2, intId);

                pstm.execute();
                pstm.close();
                
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Update: " + erro);
            }
        }
        
    }
    public void atualizarTable(){
        try {
            colegioDAO objColegioDAO = new colegioDAO();
            ArrayList<colegioDTO> lista = objColegioDAO.pesquisarColegio();
            if(confirmacaoRepeticao = true){
                for(int y=0; y<referenciaListaAnterior;y++){
                    model.removeRow(1);
                }
            }
            for(int x=0; x<lista.size(); x++){
                model.addRow(new Object[]{
                    lista.get(x).getId(),
                    lista.get(x).getNome(),
                    lista.get(x).getNota1(),
                    lista.get(x).getNota2(),
                    lista.get(x).getNota3(),
                });
            }
            confirmacaoRepeticao = true;
            referenciaListaAnterior = lista.size();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Listar valores: " + erro);
        }
    }
    Pagina2Orientador(){
        frameBackground1();
        componentsMethod();
        tableMethod();
        positionMethod();

        painel1.add(table1);

        frame1.add(painel1);
        frame1.add(painel2);
        frame1.add(painel3);
        frameBackground2();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botaoPesquisar){
            atualizarTable();
            String confirmacaoSerie = campoSerie.getText();
            if(confirmacaoSerie != ""){
                
            }
        }

        if(e.getSource()==botaoInserirAluno){
            botaoConfirmarInserirNota.setVisible(false);
            botaoConfirmarEditar.setVisible(false);
            caixaOpcInsEdi.setVisible(false);
            caixaOpcInsEdi2.setVisible(false);
            campoTroca.setVisible(false);
            labelTroca.setVisible(false);

            campoInserirAluno.setVisible(true);
            botaoConfirmarInserirAluno.setVisible(true);
        }
        if(e.getSource()==botaoEditar){
            campoInserirAluno.setVisible(false);
            botaoConfirmarInserirAluno.setVisible(false);
            caixaOpcInsEdi.setVisible(false);

            labelTroca.setVisible(true);
            caixaOpcInsEdi2.setVisible(true);
            campoInserirAluno.setVisible(true);
            botaoConfirmarEditar.setVisible(true);
            campoTroca.setVisible(true);
            
        }
        if(e.getSource()==botaoConfirmarInserirAluno){
            nome = campoInserirAluno.getText();

            colegioDTO objcolegioDTO = new colegioDTO();
            objcolegioDTO.setNome(nome);

            colegioDAO objColegioDAO = new colegioDAO();
            objColegioDAO.funcaoInserirAlunoBD(objcolegioDTO);
            campoInserirAluno.setText("");
            atualizarTable();
        }
        if(e.getSource()==botaoConfirmarEditar){
            funcaoEditar();
            atualizarTable();
        }
    }
}
