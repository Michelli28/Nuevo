/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

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
 * @author CHELLI BONITA
 */
@Entity
@Table(name = "detallefactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallefactura.findAll", query = "SELECT d FROM Detallefactura d")
    , @NamedQuery(name = "Detallefactura.findBySubtotal", query = "SELECT d FROM Detallefactura d WHERE d.subtotal = :subtotal")
    , @NamedQuery(name = "Detallefactura.findByIdDetalleFactura", query = "SELECT d FROM Detallefactura d WHERE d.idDetalleFactura = :idDetalleFactura")})
public class Detallefactura implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Subtotal")
    private Double subtotal;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleFactura")
    private Integer idDetalleFactura;
    @JoinColumn(name = "idDetalleCotizacion", referencedColumnName = "idDetalleCotizacion")
    @ManyToOne
    private CotizacionDetalle idDetalleCotizacion;
    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura")
    @ManyToOne
    private Factura idFactura;

    public Detallefactura() {
    }

    public Detallefactura(Integer idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Integer idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public CotizacionDetalle getIdDetalleCotizacion() {
        return idDetalleCotizacion;
    }

    public void setIdDetalleCotizacion(CotizacionDetalle idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleFactura != null ? idDetalleFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactura)) {
            return false;
        }
        Detallefactura other = (Detallefactura) object;
        if ((this.idDetalleFactura == null && other.idDetalleFactura != null) || (this.idDetalleFactura != null && !this.idDetalleFactura.equals(other.idDetalleFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Detallefactura[ idDetalleFactura=" + idDetalleFactura + " ]";
    }
    
}
