/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author andres bravo
 */
@Entity
public class AtaqueEntity implements Serializable{
    @Id
    private String name;
    private int daño;
    @PodamExclude
    @ManyToOne
    private PokemonEntity pokemon;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the daño
     */
    public int getDaño() {
        return daño;
    }

    /**
     * @param daño the daño to set
     */
    public void setDaño(int daño) {
        this.daño = daño;
    }

    /**
     * @return the pokemon
     */
    public PokemonEntity getPokemon() {
        return pokemon;
    }

    /**
     * @param pokemon the pokemon to set
     */
    public void setPokemon(PokemonEntity pokemon) {
        this.pokemon = pokemon;
    }
}
