package test;

import entities.*;
import services.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalleServiceTest {
    private SalleService salleService;
    private Salle salle;

    @Before
    public void setUp() {
        salleService = new SalleService();
        salle = new Salle();
        salle.setCode("A101");
        salleService.create(salle);
    }

    @After
    public void tearDown() {
        Salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("salle should have been created with an ID", salle.getId());
    }

    @Test
    public void testFindById() {
        Salle foundSalle = salleService.findById(salle.getId());
        assertNotNull("salle should be found", foundSalle);
        assertEquals("Found salle should match", salle.getCode(), foundSalle.getCode());
    }

    @Test
    public void testUpdate() {
        salle.setCode("B202");
        boolean result = salleService.update(salle);
        assertTrue("salle should have been updated", result);

        Salle Updatedsalle = salleService.findById(salle.getId());
        assertEquals("salle reference should have been updated", "B202", Updatedsalle.getCode());
    }

    @Test
    public void testDelete() {
        boolean result = salleService.delete(salle);
        assertTrue("salle should have been deleted", result);

        Salle foundSalle = salleService.findById(salle.getId());
        assertNull("salle should not be found after deletion", foundSalle);
    }

    @Test
    public void testFindAll() {
        List<Salle> salles = salleService.findAll();
        assertNotNull("salles should not be null", salles);
        assertTrue("salles list should contain at least one salle", salles.size() > 0);
    }
}
