/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.dtos;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import co.edu.uniandes.csw.pokemon.entities.AtaqueEntity;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;

/**
 *
 * @author estudiante
 */
public class PokemonDTO implements Serializable {

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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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

    private double peso;
    private double altura;
    private String nombre;
    private PokemonType tipo;
    private PokemonType debilidad;
    private String descripcion;
    @Id
    private Long id;
    private List<AtaqueEntity> ataques;

    public PokemonDTO() {

    }

    public PokemonEntity toEntity() {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(this.getId());
        pokemonEntity.setAltura(this.getAltura());
        pokemonEntity.setDebilidad(this.getDebilidad());
        pokemonEntity.setTipo(this.getTipo());
        pokemonEntity.setPeso(this.getPeso());
        pokemonEntity.setDescripcion(this.getDescripcion());
        if (this.getAtaques() != null && !this.ataques.isEmpty()) {
            pokemonEntity.setAtaques(this.getAtaques());
        }
        return pokemonEntity;
    }

    public PokemonDTO(PokemonEntity pokemonEntity) {
        if (pokemonEntity != null) {
            this.id = pokemonEntity.getId();
            this.altura = pokemonEntity.getAltura();
            this.peso = pokemonEntity.getPeso();
            this.nombre = pokemonEntity.getNombre();
            this.descripcion = pokemonEntity.getDescripcion();
            this.debilidad = pokemonEntity.getDebilidad();
            this.tipo = pokemonEntity.getTipo();
            if (pokemonEntity.getAtaques() != null || !pokemonEntity.getAtaques().isEmpty()) {
                this.ataques = pokemonEntity.getAtaques();
            } else {
                this.ataques = null;
            }

        }
    }
}
