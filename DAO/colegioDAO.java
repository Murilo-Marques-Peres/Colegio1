package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DTO.colegioDTO;
import java.sql.*;

public class colegioDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<colegioDTO> lista = new ArrayList<colegioDTO>();
    public void funcaoInserirAlunoBD(colegioDTO objcolegioDTO){
        String sql = "insert into aluno()";
        conn = new conexaoDAO().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,objcolegioDTO.getNome());
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"colegioDAO" + erro);
        }
    }
    public ArrayList<colegioDTO> pesquisarColegio(){
        String sql ="select * from aluno";
        conn = new conexaoDAO().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                colegioDTO objColegioDTO = new colegioDTO();
                objColegioDTO.setId(rs.getInt("idaluno"));
                objColegioDTO.setNome(rs.getString("nome"));
                objColegioDTO.setNota1(rs.getFloat("nota1"));
                objColegioDTO.setNota2(rs.getFloat("nota2"));
                objColegioDTO.setNota3(rs.getFloat("nota3"));
                lista.add(objColegioDTO);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Colegio Pesquisar: " + erro);
        }
        return lista;
    }

    public ResultSet autenticacaoLogin(colegioDTO objColegioDTO){
        String sql = "select * from login where usuario = ? and senha = ?";
        conn = new conexaoDAO().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, objColegioDTO.getUsuario());
            pstm.setString(2, objColegioDTO.getSenha());
            System.out.print("Usuario = ");
            System.out.println(objColegioDTO.getUsuario());
            rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na Autenticação: " + erro);
            return null;
        }
        
    }
    //public void entradaLogin(colegioDTO objColegioDTO){
        //conn = new conexaoDAO().conectaBD();
    //}






    /*public void funcaoEdicao(colegioDTO objcolegioDTO){
        String sql = "insert into aluno (nome, nota1, nota2, nota3, fk_idturma) values (?,?,?,?,?)";
        conn = new conexaoDAO().conectaBD();
        try {
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1,objcolegioDTO.getNome());
            ptsm.setInt(2,objcolegioDTO.getNota1());
            ptsm.setInt(3,objcolegioDTO.getNota2());
            ptsm.setInt(4,objcolegioDTO.getNota3());

            ptsm.execute();
            ptsm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"colegioDAO" + erro);
        }
    }*/
}
