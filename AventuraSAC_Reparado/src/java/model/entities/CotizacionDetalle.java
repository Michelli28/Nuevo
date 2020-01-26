/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CHELLI BONITA
 */
@Entity
@Table(name = "cotizacion_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CotizacionDetalle.findAll", query = "SELECT c FROM CotizacionDetalle c")
    , @NamedQuery(name = "CotizacionDetalle.findByIdDetalleCotizacion", query = "SELECT c FROM CotizacionDetalle c WHERE c.idDetalleCotizacion = :idDetalleCotizacion")
    , @NamedQuery(name = "CotizacionDetalle.findBySubTotal", query = "SELECT c FROM CotizacionDetalle c WHERE c.subTotal = :subTotal")})
public class CotizacionDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleCotizacion")
    private Integer idDetalleCotizacion;
    @Basic(optional = false)
    @Column(name = "SubTotal")
    private double subTotal;
    @JoinColumn(name = "idDetallePedido", referencedColumnName = "idDetallePedido")
    @ManyToOne
    private PedidoDetalle idDetallePedido;
    @JoinColumn(name = "idCotizacion", referencedColumnName = "idCotizacion")
    @ManyToOne
    private Cotizacion idCotizacion;
    @OneToMany(mappedBy = "idDetalleCotizacion")
    private List<Detallefactura> detallefacturaList;

    public CotizacionDetalle() {
    }

    public CotizacionDetalle(Integer idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public CotizacionDetalle(Integer idDetalleCotizacion, double subTotal) {
        this.idDetalleCotizacion = idDetalleCotizacion;
        this.subTotal = subTotal;
    }

    public Integer getIdDetalleCotizacion() {
        return idDetalleCotizacion;
    }

    public void setIdDetalleCotizacion(Integer idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public PedidoDetalle getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(PedidoDetalle idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Cotizacion getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Cotizacion idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    @XmlTransient
    public List<Detallefactura> getDetallefacturaList() {
        return detallefacturaList;
    }

    public void setDetallefacturaList(List<Detallefactura> detallefacturaList) {
        this.detallefacturaList = detallefacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleCotizacion != null ? idDetalleCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CotizacionDetalle)) {
            return false;
        }
        CotizacionDetalle other = (CotizacionDetalle) object;
        if ((this.idDetalleCotizacion == null && other.idDetalleCotizacion != null) || (this.idDetalleCotizacion != null && !this.idDetalleCotizacion.equals(other.idDetalleCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.CotizacionDetalle[ idDetalleCotizacion=" + idDetalleCotizacion + " ]";
    }
    
}
