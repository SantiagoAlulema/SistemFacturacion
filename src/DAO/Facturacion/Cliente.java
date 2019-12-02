/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Facturacion;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Santiago
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByCedula", query = "SELECT c FROM Cliente c WHERE c.cedula = :cedula")
    , @NamedQuery(name = "Cliente.findByPrimerNombre", query = "SELECT c FROM Cliente c WHERE c.primerNombre = :primerNombre")
    , @NamedQuery(name = "Cliente.findBySegudoNombre", query = "SELECT c FROM Cliente c WHERE c.segudoNombre = :segudoNombre")
    , @NamedQuery(name = "Cliente.findByPrimerApellido", query = "SELECT c FROM Cliente c WHERE c.primerApellido = :primerApellido")
    , @NamedQuery(name = "Cliente.findBySegudoApellido", query = "SELECT c FROM Cliente c WHERE c.segudoApellido = :segudoApellido")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
    , @NamedQuery(name = "Cliente.findByEstado", query = "SELECT c FROM Cliente c WHERE c.estado = :estado")
    , @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Cliente.findByFechaIngreso", query = "SELECT c FROM Cliente c WHERE c.fechaIngreso = :fechaIngreso")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Cedula")
    private String cedula;
    @Column(name = "Primer Nombre")
    private String primerNombre;
    @Column(name = "SegudoNombre")
    private String segudoNombre;
    @Column(name = "PrimerApellido")
    private String primerApellido;
    @Column(name = "SegudoApellido")
    private String segudoApellido;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Email")
    private String email;
    @Column(name = "Estado")
    private String estado;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "FechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedula")
    private List<Documentopago> documentopagoList;

    public Cliente() {
    }

    public Cliente(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegudoNombre() {
        return segudoNombre;
    }

    public void setSegudoNombre(String segudoNombre) {
        this.segudoNombre = segudoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegudoApellido() {
        return segudoApellido;
    }

    public void setSegudoApellido(String segudoApellido) {
        this.segudoApellido = segudoApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @XmlTransient
    public List<Documentopago> getDocumentopagoList() {
        return documentopagoList;
    }

    public void setDocumentopagoList(List<Documentopago> documentopagoList) {
        this.documentopagoList = documentopagoList;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Cliente[ cedula=" + cedula + " ]";
    }
    
}
