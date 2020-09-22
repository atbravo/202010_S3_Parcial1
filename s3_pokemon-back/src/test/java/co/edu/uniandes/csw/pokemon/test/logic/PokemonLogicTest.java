/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.test.logic;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import co.edu.uniandes.csw.pokemon.ejb.PokemonLogic;
import co.edu.uniandes.csw.pokemon.entities.AtaqueEntity;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class PokemonLogicTest {

    @PersistenceContext
    private EntityManager em;
    /**
     * Manejador para la transaccion
     */
    @Inject
    UserTransaction utx;

    @Inject
    private PokemonLogic pokemonLogic;

    @Inject
    private AtaqueEntity ataque;

    private List<PokemonEntity> data = new ArrayList<>();

    private PodamFactory factory = new PodamFactoryImpl();

    @Deployment
    public static JavaArchive createDeployement() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PokemonEntity.class.getPackage())
                .addPackage(PokemonPersistence.class.getPackage())
                .addPackage(PokemonLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            PokemonEntity entity = factory.manufacturePojo(PokemonEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        ataque = factory.manufacturePojo(AtaqueEntity.class);

        em.persist(ataque);

    }

    @Test
    public void createCalificacionTest() throws BusinessLogicException {

        PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
        List<AtaqueEntity> ataques = new ArrayList<>();
        ataque.setDaño(50);
        ataques.add(ataque);
        if(newEntity.getDebilidad().equals(PokemonType.FIRE))
        {
            newEntity.setTipo(PokemonType.WATER);
        }
        PokemonEntity result = pokemonLogic.createCalificacion(newEntity, ataques);
        Assert.assertNotNull(result);
        PokemonEntity entity = em.find(PokemonEntity.class, result.getId());
    }

    @Test(expected = BusinessLogicException.class)
    public void createCalificacionMayorCinco() throws BusinessLogicException {
        PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
        List<AtaqueEntity> ataques = new ArrayList<>();
        ataque.setDaño(500);
        ataques.add(ataque);
        PokemonEntity result = pokemonLogic.createCalificacion(newEntity, ataques);
        Assert.assertNotNull(result);
        PokemonEntity entity = em.find(PokemonEntity.class, result.getId());
    }
}
