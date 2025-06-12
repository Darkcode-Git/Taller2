package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class PersonaApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();

        System.out.print("¿Cuántas personas desea ingresar?: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nPersona #" + (i + 1));
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Edad: ");
            int edad = sc.nextInt(); sc.nextLine();
            System.out.print("Género (M/F): ");
            String genero = sc.nextLine();
            System.out.print("Sueldo por hora: ");
            double sueldo = sc.nextDouble(); sc.nextLine();
            System.out.print("Cargo: ");
            String cargo = sc.nextLine();

            personas.add(new Persona(nombre, apellido, edad, genero, sueldo, cargo));
        }

        // a. Cantidad
        System.out.println("\nCantidad total de personas: " + personas.size());

        // b. Promedio de edad
        double promedio = personas.stream()
                .mapToInt(Persona::getEdad)
                .average()
                .orElse(0);
        System.out.println("Promedio de edad: " + promedio);

        // c. Mayores de edad
        long mayores = personas.stream()
                .filter(p -> p.getEdad() >= 18)
                .count();
        System.out.println("Cantidad de personas mayores de edad: " + mayores);

        // d. Nombres que empiezan con A
        System.out.println("Personas con nombre que empieza con 'A':");
        personas.stream()
                .filter(p -> p.getNombre().startsWith("A"))
                .forEach(p -> System.out.println("   " + p.getNombre()));

        // e. Apellidos que contienen "M"
        System.out.println("Personas con apellidos que contienen 'M':");
        personas.stream()
                .filter(p -> p.getApellido().toUpperCase().contains("M"))
                .forEach(p -> System.out.println("   " + p.getApellido()));
    }
}

