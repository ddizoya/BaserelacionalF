/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionald;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author oracle
 */
public class BaserelacionalF {

    String usuario = "hr";
    String password = "hr";
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    ResultSetMetaData rsmd;
    Statement st;
    ResultSet rs;
    CallableStatement cs;

    public BaserelacionalF() {
        try {
            DriverManager.deregisterDriver(new OracleDriver());
            System.err.println("*Se ha registrado el Driver de Oracle. ");
        } catch (SQLException ex) {
            Logger.getLogger(BaserelacionalF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection conectarse() throws SQLException {
        Connection conn = DriverManager.getConnection(url, usuario, password);
        return conn;
    }

    public void ejecutarProcedure(int valor1, int valor2) throws SQLException{
        cs = conectarse().prepareCall("{call pjavaprocoracle(? , ?)}");
        cs.setInt(1, valor1); //Damos valores. 
        cs.setInt(2, valor2);
        cs.registerOutParameter(2, java.sql.Types.INTEGER); //Hacemos que sea el segundo parámetro el que se imprima.
        cs.execute();
        System.out.println("Resultado: " + cs.getInt(2));  //Imprimimos el valor en qué quedó la segunda variable.  
        
    }

    public static void main(String[] args) throws SQLException {
        new BaserelacionalF().ejecutarProcedure(7, 3);
    }

}
