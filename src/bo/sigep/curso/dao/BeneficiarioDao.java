package bo.sigep.curso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.sigep.curso.vo.BeneficiariosVo;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BeneficiarioDao {

    public List<BeneficiariosVo> getBeneficiarios() {
        List<BeneficiariosVo> resultado = new ArrayList<BeneficiariosVo>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT id_beneficiario, nombre,apellido from beneficiarios where rownum<300";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BeneficiariosVo beneficiario = new BeneficiariosVo();
                beneficiario.setIdBeneficiario(rs.getInt("id_beneficiario"));
                beneficiario.setNombre(rs.getString("nombre"));
                beneficiario.setApellido(rs.getString("apellido"));
                resultado.add(beneficiario);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando DataSource connDS"
                    + e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return resultado;
    }

    public int getCantidadBeneficiarioPorApellidoPaterno(String apellido) {
        int resultado = 0;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT count(*) FROM ( SELECT  ROWNUM  AS registro, id_beneficiario, nombre,apellido  "
                    + "FROM beneficiarios where apellido like ?) ";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, apellido);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando DataSource connDS"
                    + e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return resultado;
    }

    public List<BeneficiariosVo> getBeneficiarioPorApellidoPaterno(
            String apellido, int nroPagina, int nroRegistros) {
        List<BeneficiariosVo> resultado = new ArrayList<BeneficiariosVo>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM ( SELECT  ROWNUM  AS registro, id_beneficiario, nombre,apellido  "
                    + "FROM beneficiarios where apellido like ?) "
                    + "WHERE registro " + "BETWEEN (? - 1) * ? + 1 AND  ? * ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, apellido);
            pstmt.setInt(2, nroPagina);
            pstmt.setInt(3, nroRegistros);
            pstmt.setInt(4, nroPagina);
            pstmt.setInt(5, nroRegistros);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BeneficiariosVo beneficiario = new BeneficiariosVo();
                beneficiario.setRegistro(rs.getInt("registro"));
                beneficiario.setIdBeneficiario(rs.getInt("id_beneficiario"));
                beneficiario.setNombre(rs.getString("nombre"));
                beneficiario.setApellido(rs.getString("apellido"));
                resultado.add(beneficiario);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando DataSource connDS"
                    + e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return resultado;
    }
/*
    private DataSource getDataSource() {
        DataSource ds = null;
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("connDS");
        } catch (NamingException e) {
            throw new RuntimeException("Error buscando DataSource connDS"
                    + e.getMessage(), e);
        }
        return ds;
    }*/

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("NO SE ENCUENTRA EL CONTROLADOR");
        }
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "hr",
                    "hr");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return connection;
    }

}
