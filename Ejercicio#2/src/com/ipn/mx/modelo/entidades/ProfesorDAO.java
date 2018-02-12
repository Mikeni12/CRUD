package com.ipn.mx.modelo.entidades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author mikeni
 */
public class ProfesorDAO {
    private static final String SQL_INSERT="insert into Profesor(nombreProfesor, paternoProfesor, maternoProfesor, emailProfesor) values (?,?,?,?)";
    private static final String SQL_UPDATE="update Profesor set nombreProfesor=?, paternoProfesor=?, maternoProfesor=?, emailProfesor=? where idProfesor=?";
    private static final String SQL_DELETE="delete from Profesor where idProfesor=?";
    private static final String SQL_SELECT="select * from Profesor where idProfesor=?";
    private static final String SQL_SELECT_ALL="select * from Profesor";
    private Connection con=null;

    private Connection obtenerConexion(){
        Connection myConn = null;
	try{		
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ej1","root","manolito130");
            if (myConn != null)
                System.out.println("Conexión chida");
	}
	catch (Exception exc) {
            exc.printStackTrace();
	}				
        return myConn;
        //terminar
    }
    
    public void create(Profesor p) throws SQLException{
        
        PreparedStatement crearProfesor = null;
		
		try {
                        
                        con.setAutoCommit(false);
                        
                        crearProfesor = con.prepareStatement(SQL_INSERT);
                        
			Connection conn = obtenerConexion();
			Statement aStmnt = conn.createStatement();
                       // aStmnt.
                        
			//aStmnt.executeUpdate(SQL_INSERT);
			JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO");
			cerrarConexion(null,aStmnt, conn);
			
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			
		}
        
    }
    
    public void update(Profesor p) throws SQLException{
        
    }
    
    public void delete(Profesor p) throws SQLException{
        PreparedStatement ps=null;
        obtenerConexion();
        try{
            ps=con.prepareStatement(SQL_DELETE);
            ps.executeUpdate();
        } finally{
            
        }
    }
    
    public void read(Profesor p) throws SQLException{
        
        Connection conn; 
        PreparedStatement ps=null;
        
        ResultSet rs=null;
        try{
            
            conn  = obtenerConexion();
            

            ps=conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, p.getIdProfesor());
            rs=ps.executeQuery();
            
            while(rs.next()){
                
                p.setNombreProfesor(rs.getString("nombreProfesor"));
                p.setPaternoProfesor(rs.getString("paternoProfesor"));
                p.setMaternoProfesor(rs.getString("maternoProfesor"));
                p.setEmail(rs.getString("emailProfesor"));
                
                
            }

                
        } catch (SQLException e) {
            System.out.println("Error de conexion: "+e);
           
        }
    }

    private List obtenerResultado(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cerrarConexion(Object object, Statement aStmnt, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) throws SQLException {
        ProfesorDAO prof = new ProfesorDAO();
        Profesor mel=new Profesor();
        mel.setIdProfesor(1);
        prof.read(mel);
        
        System.out.println(mel);
    }
}
