/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Caglar
 */
@Entity
@Table(name = "ingredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredients.findAll", query = "SELECT i FROM Ingredients i")
    , @NamedQuery(name = "Ingredients.findById", query = "SELECT i FROM Ingredients i WHERE i.id = :id")
    , @NamedQuery(name = "Ingredients.findByIngredientID", query = "SELECT i FROM Ingredients i WHERE i.ingredientID = :ingredientID")
    , @NamedQuery(name = "Ingredients.findByQuantity", query = "SELECT i FROM Ingredients i WHERE i.quantity = :quantity")
    , @NamedQuery(name = "Ingredients.findByUnitID", query = "SELECT i FROM Ingredients i WHERE i.unitID = :unitID")
    , @NamedQuery(name = "Ingredients.findByItemID", query = "SELECT i FROM Ingredients i WHERE i.itemID = :itemID")})
public class Ingredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "IngredientID")
    private int ingredientID;
    @Basic(optional = false)
    @Column(name = "Quantity")
    private String quantity;
    @Column(name = "UnitID")
    private Integer unitID;
    @Basic(optional = false)
    @Column(name = "ItemID")
    private int itemID;

    public Ingredients() {
    }

    public Ingredients(Integer id) {
        this.id = id;
    }

    public Ingredients(Integer id, int ingredientID, String quantity, int itemID) {
        this.id = id;
        this.ingredientID = ingredientID;
        this.quantity = quantity;
        this.itemID = itemID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredients)) {
            return false;
        }
        Ingredients other = (Ingredients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Ingredients[ id=" + id + " ]";
    }
    
}
