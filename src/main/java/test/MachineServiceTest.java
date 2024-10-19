package test;

import entities.*;
import services.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MachineServiceTest {
    private MachineService machineService;
    private SalleService salleService;
    private Machine machine;
    private Salle salle;

    @Before
    public void setUp() {
        machineService = new MachineService();
        salleService = new SalleService();
        salle = new Salle("A101");
        salleService.create(salle);

        machine = new Machine("MACH-001", new Date());
        machine.setSalle(salle);
        machineService.create(machine);
    }

    @After
    public void tearDown() {
        Machine foundMachine = machineService.findById(machine.getId());
        if (foundMachine != null) {
            machineService.delete(foundMachine);
        }

        Salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("Machine should have been created with an ID", machine.getId());
    }

    @Test
    public void testFindById() {
        Salle foundSalle = salleService.findById(salle.getId());
        assertNotNull("salle should be found", foundSalle);
        assertEquals("Found salle should match", salle.getCode(), foundSalle.getCode());
    }


    @Test
    public void testUpdate() {
        machine.setRef("MACH-002");
        boolean result = machineService.update(machine);
        assertTrue("Machine should have been updated", result);

        Machine UpdatedMachine = machineService.findById(machine.getId());
        assertEquals("Machine reference should have been updated", "MACH-002", UpdatedMachine.getRef());
    }

    @Test
    public void testDelete() {
        boolean result = machineService.delete(machine);
        assertTrue("Machine should have been deleted", result);

        Machine foundMachine = machineService.findById(machine.getId());
        assertNull("Machine should not be found after deletion", foundMachine);
    }

    @Test
    public void findBetweenDate() {
        List<Machine> machines = machineService.findBetweenDate(new Date(System.currentTimeMillis() - 86400000), new Date());
        assertNotNull("Machines list should not be null", machines);
        assertTrue("Machines list should contain at least one machine", machines.size() > 0);
    }
}
