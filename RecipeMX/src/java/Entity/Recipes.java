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
@Table(name = "recipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipes.findAll", query = "SELECT r FROM Recipes r")
    , @NamedQuery(name = "Recipes.findById", query = "SELECT r FROM Recipes r WHERE r.id = :id")
    , @NamedQuery(name = "Recipes.findByTitle", query = "SELECT r FROM Recipes r WHERE r.title = :title")
    , @NamedQuery(name = "Recipes.findByIngredientID", query = "SELECT r FROM Recipes r WHERE r.ingredientID = :ingredientID")
    , @NamedQuery(name = "Recipes.findByDirectionID", query = "SELECT r FROM Recipes r WHERE r.directionID = :directionID")
    , @NamedQuery(name = "Recipes.findByYield", query = "SELECT r FROM Recipes r WHERE r.yield = :yield")})
public class Recipes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @Column(name = "IngredientID")
    private int ingredientID;
    @Basic(optional = false)
    @Column(name = "DirectionID")
    private int directionID;
    @Basic(optional = false)
    @Column(name = "Yield")
    private int yield;

    public Recipes() {
    }

    public Recipes(Integer id) {
        this.id = id;
    }

    public Recipes(Integer id, String title, int ingredientID, int directionID, int yield) {
        this.id = id;
        this.title = title;
        this.ingredientID = ingredientID;
        this.directionID = directionID;
        this.yield = yield;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public int getDirectionID() {
        return directionID;
    }

    public void setDirectionID(int directionID) {
        this.directionID = directionID;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
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
        if (!(object instanceof Recipes)) {
            return false;
        }
        Recipes other = (Recipes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Recipes[ id=" + id + " ]";
    }
    
}
