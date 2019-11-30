/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Facturacion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sanch
 */
@Entity
@Table(name = "devolucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devolucion.findAll", query = "SELECT d FROM Devolucion d")
    , @NamedQuery(name = "Devolucion.findByIdDevolucion", query = "SELECT d FROM Devolucion d WHERE d.idDevolucion = :idDevolucion")
    , @NamedQuery(name = "Devolucion.findByMotivo", query = "SELECT d FROM Devolucion d WHERE d.motivo = :motivo")
    , @NamedQuery(name = "Devolucion.findByFechaDevolucion", query = "SELECT d FROM Devolucion d WHERE d.fechaDevolucion = :fechaDevolucion")
    , @NamedQuery(name = "Devolucion.findByCantidad", query = "SELECT d FROM Devolucion d WHERE d.cantidad = :cantidad")})
public class Devolucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDevolucion")
    private Integer idDevolucion;
    @Column(name = "Motivo")
    private String motivo;
    @Column(name = "FechaDevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @JoinColumn(name = "Factura_idFactura", referencedColumnName = "idFactura")
    @ManyToOne(optional = false)
    private Documentopago facturaidFactura;

    public Devolucion() {
    }

    public Devolucion(Integer idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Integer getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(Integer idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Documentopago getFacturaidFactura() {
        return facturaidFactura;
    }

    public void setFacturaidFactura(Documentopago facturaidFactura) {
        this.facturaidFactura = facturaidFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDevolucion != null ? idDevolucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devolucion)) {
            return false;
        }
        Devolucion other = (Devolucion) object;
        if ((this.idDevolucion == null && other.idDevolucion != null) || (this.idDevolucion != null && !this.idDevolucion.equals(other.idDevolucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Devolucion[ idDevolucion=" + idDevolucion + " ]";
    }
    
}
