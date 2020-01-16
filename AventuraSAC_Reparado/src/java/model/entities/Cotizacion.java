/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c")
    , @NamedQuery(name = "Cotizacion.findByIdCotizacion", query = "SELECT c FROM Cotizacion c WHERE c.idCotizacion = :idCotizacion")
    , @NamedQuery(name = "Cotizacion.findByFechaEmision", query = "SELECT c FROM Cotizacion c WHERE c.fechaEmision = :fechaEmision")
    , @NamedQuery(name = "Cotizacion.findByImporte", query = "SELECT c FROM Cotizacion c WHERE c.importe = :importe")
    , @NamedQuery(name = "Cotizacion.findByIgv", query = "SELECT c FROM Cotizacion c WHERE c.igv = :igv")
    , @NamedQuery(name = "Cotizacion.findByTotal", query = "SELECT c FROM Cotizacion c WHERE c.total = :total")
    , @NamedQuery(name = "Cotizacion.findByObservacion", query = "SELECT c FROM Cotizacion c WHERE c.observacion = :observacion")})
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCotizacion")
    private Integer idCotizacion;
    @Basic(optional = false)
    @Column(name = "FechaEmision")
    private String fechaEmision;
    @Basic(optional = false)
    @Column(name = "Importe")
    private double importe;
    @Basic(optional = false)
    @Column(name = "Igv")
    private double igv;
    @Basic(optional = false)
    @Column(name = "Total")
    private double total;
    @Column(name = "Observacion")
    private String observacion;
    @OneToMany(mappedBy = "idCotizacion")
    private List<CotizacionDetalle> cotizacionDetalleList;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne
    private Pedido idPedido;

    public Cotizacion() {
        fechaEmision = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public Cotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
        fechaEmision = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public Cotizacion(Integer idCotizacion, String fechaEmision, double importe, double igv, double total) {
        this.idCotizacion = idCotizacion;
        this.fechaEmision = fechaEmision;
        this.importe = importe;
        this.igv = igv;
        this.total = total;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    public List<CotizacionDetalle> getCotizacionDetalleList() {
        return cotizacionDetalleList;
    }

    public void setCotizacionDetalleList(List<CotizacionDetalle> cotizacionDetalleList) {
        this.cotizacionDetalleList = cotizacionDetalleList;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCotizacion != null ? idCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.idCotizacion == null && other.idCotizacion != null) || (this.idCotizacion != null && !this.idCotizacion.equals(other.idCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Cotizacion[ idCotizacion=" + idCotizacion + " ]";
    }
    
}
