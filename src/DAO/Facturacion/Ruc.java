/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Facturacion;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Santiago
 */
@Entity
@Table(name = "ruc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ruc.findAll", query = "SELECT r FROM Ruc r")
    , @NamedQuery(name = "Ruc.findByRuc", query = "SELECT r FROM Ruc r WHERE r.ruc = :ruc")})
public class Ruc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Ruc")
    private String ruc;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "idTipoRuc", referencedColumnName = "idTipoRuc")
    @ManyToOne(optional = false)
    private Tiporuc idTipoRuc;

    public Ruc() {
    }

    public Ruc(String ruc) {
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Tiporuc getIdTipoRuc() {
        return idTipoRuc;
    }

    public void setIdTipoRuc(Tiporuc idTipoRuc) {
        this.idTipoRuc = idTipoRuc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruc != null ? ruc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruc)) {
            return false;
        }
        Ruc other = (Ruc) object;
        if ((this.ruc == null && other.ruc != null) || (this.ruc != null && !this.ruc.equals(other.ruc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Ruc[ ruc=" + ruc + " ]";
    }
    
}
