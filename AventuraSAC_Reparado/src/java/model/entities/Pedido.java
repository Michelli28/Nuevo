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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido")
    , @NamedQuery(name = "Pedido.findByFechaRegistro", query = "SELECT p FROM Pedido p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Pedido.findByFechaEntrega", query = "SELECT p FROM Pedido p WHERE p.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "Pedido.findBySaldo", query = "SELECT p FROM Pedido p WHERE p.saldo = :saldo")
    , @NamedQuery(name = "Pedido.findByAcumulado", query = "SELECT p FROM Pedido p WHERE p.acumulado = :acumulado")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Basic(optional = false)
    @Column(name = "FechaRegistro")
    private String fechaRegistro;
    @Basic(optional = false)
    @Column(name = "FechaEntrega")
    private String fechaEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo")
    private Double saldo;
    @Column(name = "acumulado")
    private Double acumulado;
    @OneToMany(mappedBy = "idPedido")
    private List<PedidoDetalle> pedidoDetalleList;
    @OneToMany(mappedBy = "idPedido")
    private List<Factura> facturaList;
    @OneToMany(mappedBy = "idPedido")
    private List<Ordencompra> ordencompraList;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(optional = false)
    private Estadopedido idEstado;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne
    private Cliente idCliente;
    @OneToMany(mappedBy = "idPedido")
    private List<GuiaremisionDetalle> guiaremisionDetalleList;
    @OneToMany(mappedBy = "idPedido")
    private List<Pagos> pagosList;
    @OneToMany(mappedBy = "idPedido")
    private List<Cotizacion> cotizacionList;

    public Pedido() {
        fechaRegistro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
        fechaRegistro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public Pedido(Integer idPedido, String fechaRegistro, String fechaEntrega) {
        this.idPedido = idPedido;
        this.fechaRegistro = fechaRegistro;
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(Double acumulado) {
        this.acumulado = acumulado;
    }

    @XmlTransient
    public List<PedidoDetalle> getPedidoDetalleList() {
        return pedidoDetalleList;
    }

    public void setPedidoDetalleList(List<PedidoDetalle> pedidoDetalleList) {
        this.pedidoDetalleList = pedidoDetalleList;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<Ordencompra> getOrdencompraList() {
        return ordencompraList;
    }

    public void setOrdencompraList(List<Ordencompra> ordencompraList) {
        this.ordencompraList = ordencompraList;
    }

    public Estadopedido getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estadopedido idEstado) {
        this.idEstado = idEstado;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public List<GuiaremisionDetalle> getGuiaremisionDetalleList() {
        return guiaremisionDetalleList;
    }

    public void setGuiaremisionDetalleList(List<GuiaremisionDetalle> guiaremisionDetalleList) {
        this.guiaremisionDetalleList = guiaremisionDetalleList;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @XmlTransient
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Pedido[ idPedido=" + idPedido + " ]";
    }
    
}
