package test;

import entities.*;
import services.*;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        Salle salle1 = new Salle("A1");
        Salle salle2 = new Salle("B2");
        salleService.create(salle1);
        salleService.create(salle2);
        Machine machine1 = new Machine("M123", new Date(), salleService.findById(1));
        Machine machine2 = new Machine("M124", new Date(), salleService.findById(1));
        Machine machine3 = new Machine("M125", new Date(), salleService.findById(2));
        machineService.create(machine3);
        machineService.create(machine2);

        for(Salle salle : salleService.findAll()) {
            System.out.println("Salle: " + salle.getCode());
            for(Machine machine : salle.getMachines()) {
                System.out.println("\t Machine :" + machine.getRef());
            }
        }

        Date d1 = new Date(110, 0, 1);
        Date d2 = new Date();
        System.out.println("Machines achetées entre " + d1 + " et " + d2 + ":");
        for(Machine machine : machineService.findBetweenDate(d1, d2)) {
            System.out.println(machine.getRef() + " achetée le " + machine.getDateAchat());
        }
    }
}
