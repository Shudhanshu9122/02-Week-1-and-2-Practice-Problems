import java.util.*;
public class DNSCache {
static class DNSEntry {
String domain;
String ipAddress;
DNSEntry next;
DNSEntry(String domain, String ipAddress) {
this.domain = domain;
this.ipAddress = ipAddress;
}
}
private DNSEntry[] table;
private int size;
public DNSCache(int size) {
this.size = size;
this.table = new DNSEntry[size];
}
private int hash(String domain) {
return Math.abs(domain.hashCode()) % size;
}
public void addEntry(String domain, String ip) {
int index = hash(domain);
DNSEntry newEntry = new DNSEntry(domain, ip);
if (table[index] == null) {
table[index] = newEntry;
System.out.println("Added " + domain + " to bucket " + index);
} else {
// Collision: chain it
System.out.println("COLLISION at bucket " + index + " - chaining");
newEntry.next = table[index];
table[index] = newEntry;
}
}
public String lookup(String domain) {
int index = hash(domain);
DNSEntry current = table[index];
while (current != null) {
if (current.domain.equals(domain)) {
return current.ipAddress;
}
current = current.next;
}
return null;
}
public static void main(String[] args) {
DNSCache cache = new DNSCache(5);
cache.addEntry("google.com", "172.217.14.206");
cache.addEntry("github.com", "140.82.121.4");
cache.addEntry("stackoverflow.com", "151.101.1.69");
System.out.println("\nLookup google.com: " + cache.lookup("google.com"));
System.out.println("Lookup github.com: " + cache.lookup("github.com"));
}
}
