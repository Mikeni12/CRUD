package com.ipn.mx.modelo.entidades;
import java.io.Serializable;
/**
 *
 * @author mikeni
 */
public class Profesor implements Serializable{
    private int idProfesor;
    private String nombreProfesor;
    private String paternoProfesor;
    private String maternoProfesor;
    private String email;
    
    public Profesor(){}

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getPaternoProfesor() {
        return paternoProfesor;
    }

    public void setPaternoProfesor(String paternoProfesor) {
        this.paternoProfesor = paternoProfesor;
    }

    public String getMaternoProfesor() {
        return maternoProfesor;
    }

    public void setMaternoProfesor(String maternoProfesor) {
        this.maternoProfesor = maternoProfesor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("id").append(getIdProfesor()).append("\n");
        sb.append("nombre").append(getNombreProfesor()).append("\n");
        sb.append("paterno").append(getPaternoProfesor()).append("\n");
        sb.append("materno").append(getMaternoProfesor()).append("\n");
        sb.append("email").append(getEmail()).append("\n");
        return sb.toString();
    }
}
