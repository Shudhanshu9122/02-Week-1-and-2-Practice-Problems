import java.util.HashMap;
public class ComplexityAnalysis {
public static void analyzeOperations() {
HashMap<Integer, String> map = new HashMap<>();
// Time Complexity:
// - put(key, value): O(1) average, O(n) worst case (all collisions)
// - get(key): O(1) average, O(n) worst case
// - remove(key): O(1) average, O(n) worst case
// - containsKey(key): O(1) average, O(n) worst case
// - Resize/Rehash: O(n) - must rehash all entries
long startTime, endTime;
// Insertion: O(1) average
startTime = System.nanoTime();
for (int i = 0; i < 10000; i++) {
map.put(i, "Value" + i);
}
endTime = System.nanoTime();
System.out.println("10,000 insertions: " + (endTime - startTime) / 1000000.0 + "ms");
// Lookup: O(1) average
startTime = System.nanoTime();
for (int i = 0; i < 10000; i++) {
map.get(i);
}
endTime = System.nanoTime();
System.out.println("10,000 lookups: " + (endTime - startTime) / 1000000.0 + "ms");
// Space Complexity: O(n) where n is number of entries
// Actual space = n entries + capacity buckets
System.out.println("\nSpace Analysis:");
System.out.println("Entries: " + map.size());
System.out.println("Approximate space: O(" + map.size() + ")");
}
public static void main(String[] args) {
analyzeOperations();
}
}
