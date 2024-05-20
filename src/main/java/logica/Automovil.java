package logica;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Automovil implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String placa;
    private String marca;
    private String añoFabricacion;
    @ManyToOne
    @JoinColumn(name = "id_propietario")
    private Cliente propietario;
    @OneToMany(mappedBy = "automovil")
    private List<Reparacion> reparaciones;


    public Automovil() {
    }

    public Automovil(String placa, String marca, String añoFabricacion, Cliente propietario, List<Reparacion> reparaciones) {
        this.placa = placa;
        this.marca = marca;
        this.añoFabricacion = añoFabricacion;
        this.propietario = propietario;
        this.reparaciones = reparaciones;
    }


    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAñoFabricacion() {
        return añoFabricacion;
    }

    public void setAñoFabricacion(String añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }

    public List<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }


}