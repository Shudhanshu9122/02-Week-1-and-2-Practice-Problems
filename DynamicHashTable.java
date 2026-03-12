import java.util.*;
public class DynamicHashTable {
static class Entry {
String key;
int value;
Entry next;
Entry(String key, int value) {
this.key = key;
this.value = value;
}
}
private Entry[] table;
private int size; // Number of entries
private int capacity; // Number of buckets
private final double LOAD_FACTOR = 0.75;
public DynamicHashTable(int initialCapacity) {
this.capacity = initialCapacity;
this.table = new Entry[capacity];
this.size = 0;
}
private int hash(String key) {
return Math.abs(key.hashCode()) % capacity;
}
public void put(String key, int value) {
// Check if resize needed
if ((double) size / capacity >= LOAD_FACTOR) {
resize();
}
int index = hash(key);
Entry newEntry = new Entry(key, value);
if (table[index] == null) {
table[index] = newEntry;
} else {
// Chain
newEntry.next = table[index];
table[index] = newEntry;
}
size++;
}
private void resize() {
System.out.println("\n=== RESIZING ===");
System.out.println("Old capacity: " + capacity + ", Size: " + size);
System.out.println("Load factor: " + ((double) size / capacity));
Entry[] oldTable = table;
capacity *= 2; // Double capacity
table = new Entry[capacity];
size = 0; // Reset size
System.out.println("New capacity: " + capacity);
// Rehash all entries
int rehashCount = 0;
for (Entry entry : oldTable) {
while (entry != null) {
put(entry.key, entry.value);
rehashCount++;
entry = entry.next;
}
}
System.out.println("Rehashed " + rehashCount + " entries");
System.out.println("=================\n");
}
public void displayStats() {
System.out.println("Capacity: " + capacity);
System.out.println("Size: " + size);
System.out.println("Load Factor: " + ((double) size / capacity));
// Show distribution
int[] chainLengths = new int[capacity];
for (int i = 0; i < capacity; i++) {
Entry current = table[i];
int length = 0;
while (current != null) {
length++;
current = current.next;
}
if (length > 0) {
chainLengths[i] = length;
}
}
System.out.println("Non-empty buckets: ");
for (int i = 0; i < capacity; i++) {
if (chainLengths[i] > 0) {
System.out.println(" Bucket " + i + ": " + chainLengths[i] + " entries");
}
}
}
public static void main(String[] args) {
// E-commerce shopping cart
DynamicHashTable cart = new DynamicHashTable(4);
System.out.println("Adding items to cart...\n");
cart.put("laptop", 1);
cart.displayStats();
System.out.println();
cart.put("mouse", 2);
cart.displayStats();
System.out.println();
cart.put("keyboard", 1);
cart.displayStats();
System.out.println();
cart.put("monitor", 1); // This triggers resize (load factor = 1.0)
cart.displayStats();
System.out.println();
cart.put("webcam", 1);
cart.displayStats();
}
}
