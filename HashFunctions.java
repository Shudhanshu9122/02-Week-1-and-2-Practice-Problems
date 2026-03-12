public class HashFunctions {
// Integer hashing - simple and fast
public static int hashInteger(int key, int tableSize) {
return Math.abs(key) % tableSize;
}
// String hashing - polynomial rolling hash
public static int hashString(String key, int tableSize) {
int hash = 0;
int prime = 31; // Good prime for string hashing
for (int i = 0; i < key.length(); i++) {
hash = hash * prime + key.charAt(i);
}
return Math.abs(hash) % tableSize;
}
// Poor hash function (demonstrates bad distribution)
public static int poorHash(String key, int tableSize) {
// Only uses first character - many collisions!
return key.charAt(0) % tableSize;
}
// Java's built-in String hashCode (good quality)
public static void demonstrateJavaHash() {
String[] emails = {
"user@gmail.com",
"admin@company.com",
"support@service.org"
};
System.out.println("Java's String hashCode:");
for (String email : emails) {
System.out.println(email + " -> " + email.hashCode());
}
}
public static void main(String[] args) {
int tableSize = 10;
// Test distribution
String[] usernames = {"alice", "bob", "charlie", "david", "eve"};
System.out.println("Custom String Hash:");
for (String name : usernames) {
int hash = hashString(name, tableSize);
System.out.println(name + " -> bucket " + hash);
}
System.out.println("\nPoor Hash Function:");
for (String name : usernames) {
int hash = poorHash(name, tableSize);
System.out.println(name + " -> bucket " + hash + " (COLLISION LIKELY)");
}
demonstrateJavaHash();
}
}
