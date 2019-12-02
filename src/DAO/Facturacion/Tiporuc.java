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
@Table(name = "tiporuc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiporuc.findAll", query = "SELECT t FROM Tiporuc t")
    , @NamedQuery(name = "Tiporuc.findByIdTipoRuc", query = "SELECT t FROM Tiporuc t WHERE t.idTipoRuc = :idTipoRuc")
    , @NamedQuery(name = "Tiporuc.findByNombre", query = "SELECT t FROM Tiporuc t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tiporuc.findByDescripcion", query = "SELECT t FROM Tiporuc t WHERE t.descripcion = :descripcion")})
public class Tiporuc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTipoRuc")
    private Integer idTipoRuc;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoRuc")
    private List<Ruc> rucList;

    public Tiporuc() {
    }

    public Tiporuc(Integer idTipoRuc) {
        this.idTipoRuc = idTipoRuc;
    }

    public Integer getIdTipoRuc() {
        return idTipoRuc;
    }

    public void setIdTipoRuc(Integer idTipoRuc) {
        this.idTipoRuc = idTipoRuc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Ruc> getRucList() {
        return rucList;
    }

    public void setRucList(List<Ruc> rucList) {
        this.rucList = rucList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoRuc != null ? idTipoRuc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiporuc)) {
            return false;
        }
        Tiporuc other = (Tiporuc) object;
        if ((this.idTipoRuc == null && other.idTipoRuc != null) || (this.idTipoRuc != null && !this.idTipoRuc.equals(other.idTipoRuc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Tiporuc[ idTipoRuc=" + idTipoRuc + " ]";
    }
    
}
