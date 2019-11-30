/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Facturacion;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author sanch
 */
@Embeddable
public class DetallefacturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Factura_idFactura")
    private int facturaidFactura;
    @Basic(optional = false)
    @Column(name = "Producto_idProducto")
    private int productoidProducto;
    @Basic(optional = false)
    @Column(name = "Producto_Cod_Producto")
    private String productoCodProducto;

    public DetallefacturaPK() {
    }

    public DetallefacturaPK(int facturaidFactura, int productoidProducto, String productoCodProducto) {
        this.facturaidFactura = facturaidFactura;
        this.productoidProducto = productoidProducto;
        this.productoCodProducto = productoCodProducto;
    }

    public int getFacturaidFactura() {
        return facturaidFactura;
    }

    public void setFacturaidFactura(int facturaidFactura) {
        this.facturaidFactura = facturaidFactura;
    }

    public int getProductoidProducto() {
        return productoidProducto;
    }

    public void setProductoidProducto(int productoidProducto) {
        this.productoidProducto = productoidProducto;
    }

    public String getProductoCodProducto() {
        return productoCodProducto;
    }

    public void setProductoCodProducto(String productoCodProducto) {
        this.productoCodProducto = productoCodProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) facturaidFactura;
        hash += (int) productoidProducto;
        hash += (productoCodProducto != null ? productoCodProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallefacturaPK)) {
            return false;
        }
        DetallefacturaPK other = (DetallefacturaPK) object;
        if (this.facturaidFactura != other.facturaidFactura) {
            return false;
        }
        if (this.productoidProducto != other.productoidProducto) {
            return false;
        }
        if ((this.productoCodProducto == null && other.productoCodProducto != null) || (this.productoCodProducto != null && !this.productoCodProducto.equals(other.productoCodProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Facturacion.DetallefacturaPK[ facturaidFactura=" + facturaidFactura + ", productoidProducto=" + productoidProducto + ", productoCodProducto=" + productoCodProducto + " ]";
    }
    
}
