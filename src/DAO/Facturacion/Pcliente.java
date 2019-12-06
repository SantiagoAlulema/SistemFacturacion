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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "pcliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pcliente.findAll", query = "SELECT p FROM Pcliente p")
    , @NamedQuery(name = "Pcliente.findByIdPCliente", query = "SELECT p FROM Pcliente p WHERE p.idPCliente = :idPCliente")
    , @NamedQuery(name = "Pcliente.findByPrimerNombre", query = "SELECT p FROM Pcliente p WHERE p.primerNombre = :primerNombre")
    , @NamedQuery(name = "Pcliente.findBySegundoNombre", query = "SELECT p FROM Pcliente p WHERE p.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "Pcliente.findByPrimerApellido", query = "SELECT p FROM Pcliente p WHERE p.primerApellido = :primerApellido")
    , @NamedQuery(name = "Pcliente.findBySegundoApellido", query = "SELECT p FROM Pcliente p WHERE p.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Pcliente.findByTelefono", query = "SELECT p FROM Pcliente p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Pcliente.findByDireccion", query = "SELECT p FROM Pcliente p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Pcliente.findByEstado", query = "SELECT p FROM Pcliente p WHERE p.estado = :estado")
    , @NamedQuery(name = "Pcliente.findByFechaIngreso", query = "SELECT p FROM Pcliente p WHERE p.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Pcliente.findByEmail", query = "SELECT p FROM Pcliente p WHERE p.email = :email")
    , @NamedQuery(name = "Pcliente.Apellidos", query = "SELECT p FROM Pcliente p where p.primerApellido LIKE :apellido")})
public class Pcliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPCliente")
    private Integer idPCliente;
    @Column(name = "PrimerNombre")
    private String primerNombre;
    @Column(name = "SegundoNombre")
    private String segundoNombre;
    @Column(name = "PrimerApellido")
    private String primerApellido;
    @Column(name = "SegundoApellido")
    private String segundoApellido;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Estado")
    private String estado;
    @Column(name = "FechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "Email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPCliente")
    private List<Ruc> rucList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPCliente")
    private List<Cedula> cedulaList;

    public Pcliente() {
    }

    public Pcliente(Integer idPCliente) {
        this.idPCliente = idPCliente;
    }

    public Integer getIdPCliente() {
        return idPCliente;
    }

    public void setIdPCliente(Integer idPCliente) {
        this.idPCliente = idPCliente;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Ruc> getRucList() {
        return rucList;
    }

    public void setRucList(List<Ruc> rucList) {
        this.rucList = rucList;
    }

    @XmlTransient
    public List<Cedula> getCedulaList() {
        return cedulaList;
    }

    public void setCedulaList(List<Cedula> cedulaList) {
        this.cedulaList = cedulaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPCliente != null ? idPCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pcliente)) {
            return false;
        }
        Pcliente other = (Pcliente) object;
        if ((this.idPCliente == null && other.idPCliente != null) || (this.idPCliente != null && !this.idPCliente.equals(other.idPCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.Pcliente[ idPCliente=" + idPCliente + " ]";
    }

    public Pcliente(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String telefono, String direccion, String estado, Date fechaIngreso, String email) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.email = email;
    }
    
    
    
}
