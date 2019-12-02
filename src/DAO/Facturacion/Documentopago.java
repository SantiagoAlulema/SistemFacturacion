/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Facturacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Santiago
 */
@Entity
@Table(name = "documentopago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentopago.findAll", query = "SELECT d FROM Documentopago d")
    , @NamedQuery(name = "Documentopago.findByIdDocumento", query = "SELECT d FROM Documentopago d WHERE d.idDocumento = :idDocumento")
    , @NamedQuery(name = "Documentopago.findByFecha", query = "SELECT d FROM Documentopago d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Documentopago.findBySubtotal", query = "SELECT d FROM Documentopago d WHERE d.subtotal = :subtotal")
    , @NamedQuery(name = "Documentopago.findByTotal", query = "SELECT d FROM Documentopago d WHERE d.total = :total")
    , @NamedQuery(name = "Documentopago.findByIva", query = "SELECT d FROM Documentopago d WHERE d.iva = :iva")
    , @NamedQuery(name = "Documentopago.findByEstado", query = "SELECT d FROM Documentopago d WHERE d.estado = :estado")})
public class Documentopago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocumento")
    private Integer idDocumento;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Subtotal")
    private BigDecimal subtotal;
    @Column(name = "Total")
    private BigDecimal total;
    @Column(name = "iva")
    private BigDecimal iva;
    @Column(name = "Estado")
    private Integer estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactura")
    private List<Devolucion> devolucionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento")
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento")
    private List<Consumidorfinal> consumidorfinalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentopago")
    private List<Detallefactura> detallefacturaList;
    @JoinColumn(name = "Cedula", referencedColumnName = "Cedula")
    @ManyToOne(optional = false)
    private Cliente cedula;
    @JoinColumn(name = "UsuarioCedula", referencedColumnName = "Cedula")
    @ManyToOne(optional = false)
    private Usuario usuarioCedula;

    public Documentopago() {
    }

    public Documentopago(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Devolucion> getDevolucionList() {
        return devolucionList;
    }

    public void setDevolucionList(List<Devolucion> devolucionList) {
        this.devolucionList = devolucionList;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<Consumidorfinal> getConsumidorfinalList() {
        return consumidorfinalList;
    }

    public void setConsumidorfinalList(List<Consumidorfinal> consumidorfinalList) {
        this.consumidorfinalList = consumidorfinalList;
    }

    @XmlTransient
    public List<Detallefactura> getDetallefacturaList() {
        return detallefacturaList;
    }

    public void setDetallefacturaList(List<Detallefactura> detallefacturaList) {
        this.detallefacturaList = detallefacturaList;
    }

    public Cliente getCedula() {
        return cedula;
    }

    public void setCedula(Cliente cedula) {
        this.cedula = cedula;
    }

    public Usuario getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(Usuario usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentopago)) {
            return false;
        }
        Documentopago other = (Documentopago) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Documentopago[ idDocumento=" + idDocumento + " ]";
    }
    
}
