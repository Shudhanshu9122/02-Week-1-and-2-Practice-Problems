import java.util.*;
public class PerformanceBenchmark {
static class Product {
String sku;
String name;
double price;
Product(String sku, String name, double price) {
this.sku = sku;
this.name = name;
this.price = price;
}
}
public static void benchmarkArraySearch(Product[] products, String[] searchSKUs) {
long startTime = System.nanoTime();
for (String sku : searchSKUs) {
// Linear search: O(n) per search
for (Product p : products) {
if (p.sku.equals(sku)) {
break;
}
}
}
long endTime = System.nanoTime();
double timeMs = (endTime - startTime) / 1000000.0;
System.out.println("Array Search: " + timeMs + "ms");
}
public static void benchmarkHashMapSearch(HashMap<String, Product> productMap,
String[] searchSKUs) {
long startTime = System.nanoTime();
for (String sku : searchSKUs) {
// Hash lookup: O(1) per search
productMap.get(sku);
}
long endTime = System.nanoTime();
double timeMs = (endTime - startTime) / 1000000.0;
System.out.println("HashMap Search: " + timeMs + "ms");
}
public static void main(String[] args) {
int productCount = 100000;
int searchCount = 10000;
// Create test data
Product[] products = new Product[productCount];
HashMap<String, Product> productMap = new HashMap<>();
System.out.println("Creating " + productCount + " products...");
for (int i = 0; i < productCount; i++) {
String sku = "SKU-" + String.format("%06d", i);
Product p = new Product(sku, "Product " + i, 9.99 + i);
products[i] = p;
productMap.put(sku, p);
}
// Random SKUs to search
Random rand = new Random(42);
String[] searchSKUs = new String[searchCount];
for (int i = 0; i < searchCount; i++) {
int randomIndex = rand.nextInt(productCount);
searchSKUs[i] = "SKU-" + String.format("%06d", randomIndex);
}
System.out.println("\nPerforming " + searchCount + " searches...\n");
// Benchmark
benchmarkArraySearch(products, searchSKUs);
benchmarkHashMapSearch(productMap, searchSKUs);
// Collision analysis
System.out.println("\n=== Collision Analysis ===");
Set<Integer> hashCodes = new HashSet<>();
int collisions = 0;
for (Product p : products) {
int hash = p.sku.hashCode();
if (hashCodes.contains(hash)) {
collisions++;
}
hashCodes.add(hash);
}
System.out.println("Total products: " + productCount);
System.out.println("Unique hash codes: " + hashCodes.size());
System.out.println("Collisions: " + collisions);
System.out.println("Collision rate: " + (collisions * 100.0 / productCount) + "%");
}
}
