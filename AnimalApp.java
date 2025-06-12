package org.example;

import java.util.*;

public class AnimalApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<Animal>> clasificacion = new HashMap<>();
        List<Animal> animales = new ArrayList<>();

        System.out.print("¿Cuántos animales desea ingresar?: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nAnimal #" + (i + 1));
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Tipo (terrestre, aéreo, acuático): ");
            String tipo = sc.nextLine().toLowerCase();
            System.out.print("Género (masculino, femenino): ");
            String genero = sc.nextLine();

            Animal animal = new Animal(nombre, tipo, genero);
            animales.add(animal);

            // Clasificar en el mapa
            clasificacion.putIfAbsent(tipo, new ArrayList<>());
            clasificacion.get(tipo).add(animal);
        }

        System.out.println("\n--- Clasificación de animales ---");
        for (Map.Entry<String, List<Animal>> entry : clasificacion.entrySet()) {
            System.out.println(entry.getKey().toUpperCase() + ":");
            entry.getValue().forEach(a -> System.out.println("   " + a.getNombre()));
        }
    }
}
