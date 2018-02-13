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
import java.util.Scanner;
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
    private Connection con;
    private Scanner entrada;
    
    
    public ProfesorDAO(){
        
        entrada = new Scanner(System.in);
        con = null;
    }

    private Connection obtenerConexion(){
        Connection myConn = null;
	try{		
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ej1","root","manolito130");
            if (myConn != null)
                System.out.println("Conexión establecida\n");
	}
	catch (Exception exc) {
            exc.printStackTrace();
	}				
        return myConn;
        //terminar
    }
    
    public void create(Profesor p) throws SQLException{
        
        PreparedStatement crearProfesor = null;
        Connection conn;
		
		try {
                        conn = obtenerConexion();
                        
                        
                        
                        crearProfesor = conn.prepareStatement(SQL_INSERT);
                        crearProfesor.setString(1, p.getNombreProfesor());
                        crearProfesor.setString(2, p.getPaternoProfesor());
                        crearProfesor.setString(3, p.getMaternoProfesor());
                        crearProfesor.setString(4, p.getEmail());
			
                        
                        
                        crearProfesor.executeUpdate();
			
                        System.out.println("REGISTRO EXITOAMENTE AGREGADO");
                            
			
			
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			
		}
        
    }
    
    public void update(Profesor p) throws SQLException{
        
        Connection conn;
        PreparedStatement ps=null;
        
        try{
            
            conn = obtenerConexion();
            ps=conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombreProfesor());
            ps.setString(2, p.getPaternoProfesor());
            ps.setString(3, p.getMaternoProfesor());
            ps.setString(4, p.getEmail());
            ps.setInt(5, p.getIdProfesor());
            ps.executeUpdate();
            
            System.out.println("REGISTRO EXITOSAMENTE ACTUALIZADO");
            
        } catch(SQLException e ){
            
            System.out.println("Error de conexion: "+e);
            
        }
        
    }
    
    public void delete(Profesor p) throws SQLException{
        
        Connection conn;
        PreparedStatement ps=null;
        
        try{
            
            conn = obtenerConexion();
            ps=conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, p.getIdProfesor());
            ps.executeUpdate();
            
            System.out.println("REGISTRO EXITOSAMENTE ELIMINADO");
            
        } catch(SQLException e ){
            
            System.out.println("Error de conexion: "+e);
            
        }
        finally{
            
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
            if(profesores!=null){
            
                for(Profesor unProfe  : profesores){
                
                System.out.println(unProfe);
                
                
                }
            }else
                System.out.println("EL PROFESOR CON ID "+p.getIdProfesor()+" NO HA SIDO REGISTRADO");
            


                
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
                
                p = new Profesor();
                
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
    

    
    
    public int menu() throws SQLException{
        
        String datoProfesor = null;
        Profesor unProfesor;
        int opcion = 0; 
        System.out.println("BIENVENIDO AL CRUD DE PROFESORES POR FAVOR INDIQUE "
                + "ALGUNA DE LAS SIGUIENTES OPCIONES \n"
                + "1.- CREAR PROFESOR\n"
                + "2.- BUSCAR PROFESOR POR ID\n"
                + "3.- ENLISTAR A LOS PROFESORES REGISTRADOS\n"
                + "4.- ACTUALIZAR PROFESOR\n"
                + "5.- ELIMINAR PROFESOR\n");
        opcion = entrada.nextInt();
        
        switch(opcion){
            
           case 1: {
               
               unProfesor = new Profesor();
               
               System.out.println("INGRESE EL NOMBRE DEL PROFESOR\n");
               entrada.nextLine();
                       
               datoProfesor = entrada.nextLine();
               unProfesor.setNombreProfesor(datoProfesor);
               
               System.out.println("INGRESE EL APELLIDO PATERNO DEL PROFESOR\n");
               datoProfesor = entrada.nextLine();
               unProfesor.setPaternoProfesor(datoProfesor);
               
               System.out.println("INGRESE EL APELLIDO MATERNO DEL PROFESOR\n");
               datoProfesor = entrada.nextLine();
               unProfesor.setMaternoProfesor(datoProfesor);
               
               System.out.println("INGRESE EL E-MAIL DEL PROFESOR\n");
               datoProfesor = entrada.nextLine();
               unProfesor.setEmail(datoProfesor);
               
               create(unProfesor);
               break;
               
           }
           case 2:{
               
               unProfesor = new Profesor();
               
               System.out.println("INGRESE EL ID DEL PROFESOR\n");
               entrada.nextLine();
               unProfesor.setIdProfesor(entrada.nextInt());
               System.out.println("RESULTADOS\n");
               read(unProfesor);
               break;

           }
           
           case 3:{
               
               System.out.println("RESULTADOS\n");
               read();
               break;
           }
            
           case 4:{
               
                              
               unProfesor = new Profesor();


               System.out.println("INGRESE EL ID DEL PROFESOR\n");
               entrada.nextLine();
               unProfesor.setIdProfesor(entrada.nextInt());
               
               read(unProfesor);
               
               System.out.println("INGRESE EL NOMBRE DEL PROFESOR\n");
               entrada.nextLine();
               datoProfesor = entrada.nextLine();
               unProfesor.setNombreProfesor(datoProfesor);
               
               System.out.println("INGRESE EL APELLIDO PATERNO DEL PROFESOR\n");
               datoProfesor = entrada.nextLine();
               unProfesor.setPaternoProfesor(datoProfesor);
               
               System.out.println("INGRESE EL APELLIDO MATERNO DEL PROFESOR\n");
               datoProfesor = entrada.nextLine();
               unProfesor.setMaternoProfesor(datoProfesor);
               
               System.out.println("INGRESE EL E-MAIL DEL PROFESOR\n");
               datoProfesor = entrada.nextLine();
               unProfesor.setEmail(datoProfesor);
               
               
               
               update(unProfesor);
               break;
               
               
           }
           
           case 5:{
               
               unProfesor = new Profesor();
               
               System.out.println("INGRESE EL ID DEL PROFESOR");
               unProfesor.setIdProfesor(entrada.nextInt());
               delete(unProfesor);
               break;
               
           }
           
           default:{
               
               System.out.println("INGRESE UNA OPCION VALIDA");
           }
           
        
        }
        
        System.out.println("DESEA REALIZAR OTRA OPERACION?\n"
                + "1.- SI\n"
                + "2.- NO\n");
        
        opcion = entrada.nextInt();
        
        return opcion;
    }
}
