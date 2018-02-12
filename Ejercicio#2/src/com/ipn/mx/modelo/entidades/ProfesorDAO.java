package com.ipn.mx.modelo.entidades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    public void read(){

        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        
        profesores = obtenerResultado();
        for(Profesor unProfe  : profesores){
            
            System.out.println(unProfe);
            
        }        
    }
    
    public void read(Profesor p) throws SQLException{
        
        
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        
        try{
            
            profesores = obtenerResultado(p.getIdProfesor());
            
            for(Profesor unProfe  : profesores){
                
                    System.out.println(unProfe);
                
                
            }

                
        } catch (SQLException e) {
            System.out.println("Error de conexion: "+e);
           
        }
    }

    private ArrayList<Profesor> obtenerResultado(){
        
        Connection conn; 
        PreparedStatement ps=null;
        ResultSet rs=null;
        Profesor p = new Profesor();
        
        ArrayList<Profesor> resultados = new ArrayList<Profesor> ();
        
        try{
            
            conn  = obtenerConexion();
            ps=conn.prepareStatement(SQL_SELECT_ALL);
            
            rs=ps.executeQuery();
            
            while(rs.next()){
                
                p.setIdProfesor(rs.getInt("idProfesor"));
                p.setNombreProfesor(rs.getString("nombreProfesor"));
                p.setPaternoProfesor(rs.getString("paternoProfesor"));
                p.setMaternoProfesor(rs.getString("maternoProfesor"));
                p.setEmail(rs.getString("emailProfesor"));
                
                resultados.add(p);
            
            }
            
            
            return resultados;
            
        } catch(SQLException e ){
            
            System.out.println("Error de conexion: "+e);
            return null;
        }
        
        
        
    }
    private ArrayList<Profesor> obtenerResultado(int id) throws SQLException {
        
        Connection conn; 
        PreparedStatement ps=null;
        ResultSet rs=null;
        Profesor p = new Profesor();
        
        ArrayList<Profesor> resultados = new ArrayList<Profesor> ();
        
        try{
            
            conn  = obtenerConexion();
            ps=conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            
            while(rs.next()){
                
                p.setIdProfesor(rs.getInt("idProfesor"));
                p.setNombreProfesor(rs.getString("nombreProfesor"));
                p.setPaternoProfesor(rs.getString("paternoProfesor"));
                p.setMaternoProfesor(rs.getString("maternoProfesor"));
                p.setEmail(rs.getString("emailProfesor"));
                
                resultados.add(p);
            
            }
            
            
            return resultados;
            
        } catch(SQLException e ){
            
            System.out.println("Error de conexion: "+e);
            return null;
        }
        

        
    }

    private void cerrarConexion(Object object, Statement aStmnt, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) throws SQLException {
        ProfesorDAO prof = new ProfesorDAO();
        Profesor mel=new Profesor();
        mel.setIdProfesor(1);
        prof.read(mel);
        
        prof.read();
        
        
    }
}
