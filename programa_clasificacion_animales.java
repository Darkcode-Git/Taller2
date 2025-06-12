import java.util.*;
import java.util.stream.Collectors;

// Clase Animal con todos los atributos solicitados
class Animal {
    private String nombre;
    private String tipo;
    private String genero;
    
    // Constructor que asigna todos los atributos
    public Animal(String nombre, String tipo, String genero) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.genero = genero;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    @Override
    public String toString() {
        return nombre + " (" + genero + ")";
    }
}

// Clase Principal con Map y ArrayList
public class Principal {
    // Atributos solicitados usando genéricos
    private Map<String, List<Animal>> clasificacion;
    private List<Animal> animales;
    private Scanner scanner;
    
    // Constructor
    public Principal() {
        this.clasificacion = new TreeMap<>(); // TreeMap para orden alfabético
        this.animales = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        
        // Inicializar las categorías en el mapa
        clasificacion.put("terrestre", new ArrayList<>());
        clasificacion.put("aereo", new ArrayList<>());
        clasificacion.put("acuatico", new ArrayList<>());
    }
    
    // Método para llenar la lista de animales por consola
    public void llenarListaAnimales() {
        System.out.println("=== CLASIFICACIÓN DE ANIMALES ===");
        System.out.println("Tipos válidos: terrestre, aereo, acuatico");
        System.out.println("Géneros válidos: masculino, femenino");
        System.out.println("Escribe 'fin' en el nombre para terminar\n");
        
        while (true) {
            System.out.print("Nombre del animal: ");
            String nombre = scanner.nextLine().trim();
            
            if (nombre.equalsIgnoreCase("fin")) {
                break;
            }
            
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío. Intenta de nuevo.");
                continue;
            }
            
            String tipo = solicitarTipo();
            String genero = solicitarGenero();
            
            // Crear y agregar animal a la lista
            Animal animal = new Animal(nombre, tipo, genero);
            animales.add(animal);
            
            System.out.println("Animal agregado exitosamente!\n");
        }
    }
    
    // Método auxiliar para solicitar tipo válido
    private String solicitarTipo() {
        while (true) {
            System.out.print("Tipo (terrestre/aereo/acuatico): ");
            String tipo = scanner.nextLine().trim().toLowerCase();
            
            if (tipo.equals("terrestre") || tipo.equals("aereo") || tipo.equals("acuatico")) {
                return tipo;
            }
            
            System.out.println("Tipo inválido. Debe ser: terrestre, aereo o acuatico");
        }
    }
    
    // Método auxiliar para solicitar género válido
    private String solicitarGenero() {
        while (true) {
            System.out.print("Género (masculino/femenino): ");
            String genero = scanner.nextLine().trim().toLowerCase();
            
            if (genero.equals("masculino") || genero.equals("femenino")) {
                return genero;
            }
            
            System.out.println("Género inválido. Debe ser: masculino o femenino");
        }
    }
    
    // Método para clasificar animales usando Streams y Lambda
    public void clasificarAnimales() {
        // Limpiar clasificación previa
        clasificacion.values().forEach(List::clear);
        
        // Usar streams para clasificar animales por tipo
        Map<String, List<Animal>> clasificacionTemporal = animales.stream()
            .collect(Collectors.groupingBy(Animal::getTipo));
        
        // Transferir a nuestro mapa principal
        clasificacionTemporal.forEach((tipo, listaAnimales) -> {
            clasificacion.get(tipo).addAll(listaAnimales);
        });
    }
    
    // Método para mostrar animales clasificados
    public void mostrarClasificacion() {
        System.out.println("\n=== CLASIFICACIÓN DE ANIMALES ===\n");
        
        // Mostrar cada categoría usando streams y lambda
        clasificacion.entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty()) // Solo mostrar categorías con animales
            .forEach(entry -> {
                String categoria = capitalize(entry.getKey());
                System.out.println(categoria + "s:");
                
                // Mostrar animales de la categoría usando streams
                entry.getValue().stream()
                    .map(Animal::getNombre)
                    .forEach(nombre -> System.out.println("    " + nombre));
                
                System.out.println();
            });
    }
    
    // Método auxiliar para capitalizar primera letra
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    // Método para mostrar estadísticas usando streams
    public void mostrarEstadisticas() {
        System.out.println("=== ESTADÍSTICAS ===");
        System.out.println("Total de animales: " + animales.size());
        
        // Contar por tipo usando streams
        Map<String, Long> conteoTipos = animales.stream()
            .collect(Collectors.groupingBy(Animal::getTipo, Collectors.counting()));
        
        conteoTipos.forEach((tipo, cantidad) -> 
            System.out.println(capitalize(tipo) + "s: " + cantidad));
        
        // Contar por género usando streams
        System.out.println("\nPor género:");
        Map<String, Long> conteoGeneros = animales.stream()
            .collect(Collectors.groupingBy(Animal::getGenero, Collectors.counting()));
        
        conteoGeneros.forEach((genero, cantidad) -> 
            System.out.println(capitalize(genero) + "s: " + cantidad));
        
        System.out.println();
    }
    
    // Método principal que ejecuta el programa
    public void ejecutar() {
        llenarListaAnimales();
        
        if (animales.isEmpty()) {
            System.out.println("No se ingresaron animales.");
            return;
        }
        
        clasificarAnimales();
        mostrarClasificacion();
        mostrarEstadisticas();
    }
    
    // Método main
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.ejecutar();
    }
}