import java.util.*;
public class EmployeeSearch {
// Array-based search: O(n)
static class ArrayApproach {
static class Employee {
int id;
String name;
Employee(int id, String name) {
this.id = id;
this.name = name;
}
}
public static void main(String[] args) {
Employee[] employees = {
new Employee(1001, "Alice"),
new Employee(1002, "Bob"),
new Employee(1003, "Charlie")
};
// Find employee 1003 - must scan array
long start = System.nanoTime();
for (Employee emp : employees) {
if (emp.id == 1003) {
System.out.println("Found: " + emp.name);
break;
}
}
long end = System.nanoTime();
System.out.println("Array search time: " + (end - start) + "ns");
}
}
// Hash table approach: O(1)
static class HashApproach {
public static void main(String[] args) {
HashMap<Integer, String> employees = new HashMap<>();
employees.put(1001, "Alice");
employees.put(1002, "Bob");
employees.put(1003, "Charlie");
// Direct lookup via hash
long start = System.nanoTime();
String name = employees.get(1003);
long end = System.nanoTime();
System.out.println("Found: " + name);
System.out.println("Hash search time: " + (end - start) + "ns");
}
}
}
