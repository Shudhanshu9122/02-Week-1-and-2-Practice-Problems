import java.util.*;
public class WebAnalytics {
// Count most visited pages
public static void analyzeTraffic(String[] pageViews) {
HashMap<String, Integer> viewCounts = new HashMap<>();
// Count frequencies: O(n)
for (String page : pageViews) {
viewCounts.put(page, viewCounts.getOrDefault(page, 0) + 1);
}
// Find most popular page
String mostVisited = "";
int maxViews = 0;
for (Map.Entry<String, Integer> entry : viewCounts.entrySet()) {
if (entry.getValue() > maxViews) {
maxViews = entry.getValue();
mostVisited = entry.getKey();
}
}
System.out.println("Traffic Analysis:");
viewCounts.forEach((page, count) ->
System.out.println(page + ": " + count + " views"));
System.out.println("\nMost visited: " + mostVisited + " (" + maxViews + " views)");
}
public static void main(String[] args) {
String[] logs = {
"/home", "/products", "/home", "/about",
"/products", "/home", "/contact", "/products", "/home"
};
analyzeTraffic(logs);
}
}
