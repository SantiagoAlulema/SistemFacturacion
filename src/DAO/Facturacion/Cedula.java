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
@Table(name = "cedula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cedula.findAll", query = "SELECT c FROM Cedula c")
    , @NamedQuery(name = "Cedula.findByCedula", query = "SELECT c FROM Cedula c WHERE c.cedula = :cedula")})
public class Cedula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Cedula")
    private String cedula;
    @JoinColumn(name = "idPCliente", referencedColumnName = "idPCliente")
    @ManyToOne(optional = false)
    private Pcliente idPCliente;

    public Cedula() {
    }

    public Cedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Pcliente getIdPCliente() {
        return idPCliente;
    }

    public void setIdPCliente(Pcliente idPCliente) {
        this.idPCliente = idPCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cedula)) {
            return false;
        }
        Cedula other = (Cedula) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Cedula[ cedula=" + cedula + " ]";
    }
    
}
