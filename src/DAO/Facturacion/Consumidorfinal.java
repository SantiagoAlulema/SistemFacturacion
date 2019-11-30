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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sanch
 */
@Entity
@Table(name = "consumidorfinal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumidorfinal.findAll", query = "SELECT c FROM Consumidorfinal c")
    , @NamedQuery(name = "Consumidorfinal.findByIdConsumidorFinal", query = "SELECT c FROM Consumidorfinal c WHERE c.idConsumidorFinal = :idConsumidorFinal")})
public class Consumidorfinal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdConsumidorFinal")
    private Integer idConsumidorFinal;
    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura")
    @ManyToOne(optional = false)
    private Documentopago idFactura;

    public Consumidorfinal() {
    }

    public Consumidorfinal(Integer idConsumidorFinal) {
        this.idConsumidorFinal = idConsumidorFinal;
    }

    public Integer getIdConsumidorFinal() {
        return idConsumidorFinal;
    }

    public void setIdConsumidorFinal(Integer idConsumidorFinal) {
        this.idConsumidorFinal = idConsumidorFinal;
    }

    public Documentopago getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Documentopago idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsumidorFinal != null ? idConsumidorFinal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumidorfinal)) {
            return false;
        }
        Consumidorfinal other = (Consumidorfinal) object;
        if ((this.idConsumidorFinal == null && other.idConsumidorFinal != null) || (this.idConsumidorFinal != null && !this.idConsumidorFinal.equals(other.idConsumidorFinal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Consumidorfinal[ idConsumidorFinal=" + idConsumidorFinal + " ]";
    }
    
}
