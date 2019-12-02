/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Facturacion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Santiago
 */
@Entity
@Table(name = "provedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provedor.findAll", query = "SELECT p FROM Provedor p")
    , @NamedQuery(name = "Provedor.findByIdProvedor", query = "SELECT p FROM Provedor p WHERE p.idProvedor = :idProvedor")
    , @NamedQuery(name = "Provedor.findByNombre", query = "SELECT p FROM Provedor p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Provedor.findByUbicacion", query = "SELECT p FROM Provedor p WHERE p.ubicacion = :ubicacion")
    , @NamedQuery(name = "Provedor.findByTelefono", query = "SELECT p FROM Provedor p WHERE p.telefono = :telefono")})
public class Provedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idProvedor")
    private Integer idProvedor;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Ubicacion")
    private String ubicacion;
    @Column(name = "Telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvedor")
    private List<Producto> productoList;

    public Provedor() {
    }

    public Provedor(Integer idProvedor) {
        this.idProvedor = idProvedor;
    }

    public Integer getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(Integer idProvedor) {
        this.idProvedor = idProvedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvedor != null ? idProvedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provedor)) {
            return false;
        }
        Provedor other = (Provedor) object;
        if ((this.idProvedor == null && other.idProvedor != null) || (this.idProvedor != null && !this.idProvedor.equals(other.idProvedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Provedor[ idProvedor=" + idProvedor + " ]";
    }
    
}
