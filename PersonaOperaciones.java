package org.example;

import java.util.*;
import java.util.stream.*;

public class PersonaOperaciones {
    public static void main(String[] args) {
        List<Persona> personas = List.of(
                new Persona("Ana", "Morales", 30, "F", 60000, "Desarrollador"),
                new Persona("Luis", "Pérez", 40, "M", 80000, "Director"),
                new Persona("Carlos", "Gómez", 45, "M", 56000, "Director"),
                new Persona("Luisa", "Mendoza", 25, "F", 50000, "Diseñador"),
                new Persona("Andrea", "Marín", 28, "F", 75000, "Desarrollador")
        );

        // a. Sueldo 8 horas - Directores Masculinos
        personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("Director") && p.getGenero().equalsIgnoreCase("M"))
                .peek(p -> System.out.print("Nombre: " + p.getNombre() + " " + p.getApellido() + " - "))
                .map(p -> p.getSueldoHora() * 8)
                .forEach(sueldo -> System.out.println("Sueldo por 8 horas: $" + sueldo));

        // b. Primera desarrolladora mujer
        Optional<Persona> primeraDesarrolladora = personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("Desarrollador") && p.getGenero().equalsIgnoreCase("F"))
                .findFirst();

        System.out.println("\nPrimera desarrolladora mujer:");
        primeraDesarrolladora.ifPresent(p -> System.out.println(p.getNombre() + " " + p.getApellido()));

        // c. Desarrollador que más gana por hora
        Optional<Persona> masGana = personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("Desarrollador"))
                .max(Comparator.comparingDouble(Persona::getSueldoHora));

        System.out.println("\nDesarrollador que más gana por hora:");
        if (masGana.isPresent()) {
            Persona p = masGana.get();
            System.out.println(p.getNombre() + " " + p.getApellido() + " - $" + p.getSueldoHora() + "/hora");
        }

        // d. Mujeres ordenadas por nombre
        System.out.println("\nMujeres ordenadas por nombre:");
        personas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase("F"))
                .sorted(Comparator.comparing(Persona::getNombre))
                .forEach(p -> System.out.println("   " + p.getNombre() + " " + p.getApellido()));
    }
}
