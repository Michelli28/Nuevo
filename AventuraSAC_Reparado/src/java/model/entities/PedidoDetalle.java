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
@Table(name = "pedido_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoDetalle.findAll", query = "SELECT p FROM PedidoDetalle p")
    , @NamedQuery(name = "PedidoDetalle.findByIdDetallePedido", query = "SELECT p FROM PedidoDetalle p WHERE p.idDetallePedido = :idDetallePedido")})
public class PedidoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetallePedido")
    private Integer idDetallePedido;
    @OneToMany(mappedBy = "idDetallePedido")
    private List<CotizacionDetalle> cotizacionDetalleList;
    @JoinColumn(name = "idFicha", referencedColumnName = "idFicha")
    @ManyToOne
    private Fichatecnica idFicha;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne
    private Pedido idPedido;

    public PedidoDetalle() {
    }

    public PedidoDetalle(Integer idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Integer getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(Integer idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    @XmlTransient
    public List<CotizacionDetalle> getCotizacionDetalleList() {
        return cotizacionDetalleList;
    }

    public void setCotizacionDetalleList(List<CotizacionDetalle> cotizacionDetalleList) {
        this.cotizacionDetalleList = cotizacionDetalleList;
    }

    public Fichatecnica getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Fichatecnica idFicha) {
        this.idFicha = idFicha;
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
        hash += (idDetallePedido != null ? idDetallePedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoDetalle)) {
            return false;
        }
        PedidoDetalle other = (PedidoDetalle) object;
        if ((this.idDetallePedido == null && other.idDetallePedido != null) || (this.idDetallePedido != null && !this.idDetallePedido.equals(other.idDetallePedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.PedidoDetalle[ idDetallePedido=" + idDetallePedido + " ]";
    }
    
}
