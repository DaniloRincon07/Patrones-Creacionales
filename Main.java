import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

// Clase para representar la categoría de un producto
class Category {
    private String name;
    private String description;
    private Map<String, String> attributes;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.attributes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addAttribute(String key, String value) {
        this.attributes.put(key, value);
    }
}

// Clase para representar un producto. Implementa el patrón Prototype.
class Product implements Cloneable {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private Category category;
    private Map<String, String> specificAttributes;

    public Product(String name, double price, int quantity, Category category) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.specificAttributes = new HashMap<>();
    }

    // Constructor para el método clone
    private Product(Product product) {
        this.id = UUID.randomUUID().toString(); // El clon tiene un nuevo ID
        this.name = product.name;
        this.price = product.price;
        this.quantity = product.quantity;
        this.category = product.category;
        this.specificAttributes = new HashMap<>(product.specificAttributes); // Copia los atributos
    }

    @Override
    public Product clone() {
        return new Product(this);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Métodos para actualizar el nombre y el precio
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addSpecificAttribute(String key, String value) {
        this.specificAttributes.put(key, value);
    }

    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', price=" + price + ", quantity=" + quantity + ", category=" + category.getName() + "}";
    }
}

// Interfaz que define el método de fábrica
interface ProductFactory {
    Product createProduct(String name, double price, int quantity, Category category, Map<String, String> attributes);
}

// Clase que implementa la fábrica para crear productos
class ConcreteProductFactory implements ProductFactory {
    @Override
    public Product createProduct(String name, double price, int quantity, Category category, Map<String, String> attributes) {
        Product product = new Product(name, price, quantity, category);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            product.addSpecificAttribute(entry.getKey(), entry.getValue());
        }
        return product;
    }
}

// Clase que gestiona el inventario. Implementa el patrón Singleton.
class InventoryManager {
    private static InventoryManager instance;
    private Map<String, Product> inventory;

    private InventoryManager() {
        this.inventory = new HashMap<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        inventory.put(product.getId(), product);
    }

    public Product getProduct(String id) {
        return inventory.get(id);
    }

    public void updateStock(String productId, int newQuantity) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            System.out.println("Inventario de '" + product.getName() + "' actualizado. Nuevo stock: " + product.getQuantity());
        }
    }

    public void listInventory() {
        System.out.println("\n--- Inventario Actual ---");
        inventory.values().forEach(System.out::println);
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        // Obtenemos la única instancia del gestor de inventario
        InventoryManager inventoryManager = InventoryManager.getInstance();

        // Creamos una fábrica de productos
        ProductFactory productFactory = new ConcreteProductFactory();

        // Creamos categorías
        Category electronics = new Category("Electronics", "Dispositivos electrónicos");
        electronics.addAttribute("Marca", "TechCorp");
        electronics.addAttribute("Modelo", "XPS-15");

        // --- Creando el producto original usando la fábrica ---
        System.out.println("--- Creando el producto original usando la fábrica ---");
        Map<String, String> laptopAttributes = new HashMap<>();
        laptopAttributes.put("Marca", "TechCorp");
        laptopAttributes.put("Modelo", "XPS-15");
        Product laptopPro = productFactory.createProduct("Laptop Pro", 1200.0, 5, electronics, laptopAttributes);
        inventoryManager.addProduct(laptopPro);
        System.out.println("Producto '" + laptopPro.getName() + "' añadido al inventario.");

        // --- Clonando el producto para crear uno nuevo (Patrón Prototype) ---
        System.out.println("\n--- Clonando el producto para crear uno nuevo ---");
        Product laptopLite = laptopPro.clone();
        laptopLite.setQuantity(10);
        laptopLite.setName("Laptop Lite"); // Renombramos el producto clonado
        laptopLite.setPrice(800.0); // Cambiamos el precio
        inventoryManager.addProduct(laptopLite);
        System.out.println("Producto '" + laptopLite.getName() + "' añadido al inventario.");

        // Listamos el inventario inicial
        inventoryManager.listInventory();

        // Actualizamos el stock (Patrón Singleton)
        inventoryManager.updateStock(laptopPro.getId(), 3);
        inventoryManager.updateStock(laptopLite.getId(), 15);

        // Listamos el inventario actualizado
        inventoryManager.listInventory();
        
        // --- Prueba de Singleton ---
        System.out.println("\n--- Prueba de Singleton ---");
        InventoryManager manager1 = InventoryManager.getInstance();
        InventoryManager manager2 = InventoryManager.getInstance();
        System.out.println("¿Ambos gestores son la misma instancia? " + (manager1 == manager2));

        // --- Prueba de Prototype ---
        System.out.println("\n--- Prueba de Prototype ---");
        Product original = new Product("Original", 100.0, 1, new Category("Temp", "Temp"));
        Product clon = original.clone();
        System.out.println("ID del original: " + original.getId());
        System.out.println("ID del clon: " + clon.getId());
        System.out.println("¿Son el mismo objeto? " + (original == clon));
    }
}
