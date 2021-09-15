package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class FacturadorImpl extends Conexion {
    
    
    
    
    
    

    public List<String> autocompleteUbigeo(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "select  CONCAT(CONCAT(CONCAT(u.NOMDIS,', '),CONCAT(u.NOMPRO,', ')),u.NOMDEP) AS UBIGEODESC from UBIGEO u WHERE u.NOMDIS LIKE ? AND ROWNUM <= 5";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("UBIGEODESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteUbigeoDao" + e.getMessage());
        }
        return lista;
    }
    
    public String enviarUbigeo(String ubigeo) throws SQLException, Exception {
        String sql = "select CONCAT(CONCAT(CONCAT(u.NOMDEP,' '),CONCAT(u.NOMPRO,' ')),NOMDIS) AS DISTRITO from UBIGEO u WHERE CODUBI = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1,ubigeo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ubigeo = rs.getString("DISTRITO");
                if(ubigeo.contains("Z")){
                    ubigeo = ubigeo.replaceAll("Z","S");
                }
                rs.close();
            } 
            

        } catch (Exception e) {
            System.out.println("Error en EnviarUbigeoDao " + e.getMessage());
        }
        return ubigeo;
    }
}