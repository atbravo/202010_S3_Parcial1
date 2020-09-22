/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andres Bravo
 */
@Entity
public class PokemonEntity implements Serializable{
    private String nombre;
    private double altura;
    private PokemonType tipo;
    private PokemonType debilidad;
    private double peso;
    @Id
    private Long id;
    private String descripcion;
    @PodamExclude
    @OneToMany(mappedBy = "pokemon", fetch = FetchType.LAZY)
    private List<AtaqueEntity> ataques;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * @return the tipo
     */
    public PokemonType getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(PokemonType tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the debilidad
     */
    public PokemonType getDebilidad() {
        return debilidad;
    }

    /**
     * @param debilidad the debilidad to set
     */
    public void setDebilidad(PokemonType debilidad) {
        this.debilidad = debilidad;
    }

    /**
     * @return the ataques
     */
    public List<AtaqueEntity> getAtaques() {
        return ataques;
    }

    /**
     * @param ataques the ataques to set
     */
    public void setAtaques(List<AtaqueEntity> ataques) {
        this.ataques = ataques;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
