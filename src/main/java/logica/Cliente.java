package logica;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente {
    @Id
    private int cedula;
    private String nombre;
    private String apellido;
    private  String telefono;

    private String correo;

    private  String direccion;

    @OneToMany(mappedBy="propietario")
    private List<Automovil> automoviles;
    @ManyToOne
    @JoinColumn(name="id_mecanica")
    private Mecanica mecanica;


    public Cliente(int cedula, String nombre, String apellido, String telefono, String correo, String direccion, List<Automovil> automoviles, Mecanica mecanica) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.automoviles = automoviles;
        this.mecanica = mecanica;
    }

    public Cliente() {
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Automovil> getAutomoviles() {
        return automoviles;
    }

    public void setAutomoviles(List<Automovil> automoviles) {
        this.automoviles = automoviles;
    }

    public Mecanica getMecanica() {
        return mecanica;
    }

    public void setMecanica(Mecanica mecanica) {
        this.mecanica = mecanica;
    }
}
