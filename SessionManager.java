public class SessionManager {
private String[] sessions;
private int capacity;
public SessionManager(int capacity) {
this.capacity = capacity;
this.sessions = new String[capacity];
}
private int hash(String sessionId) {
return Math.abs(sessionId.hashCode()) % capacity;
}
// Quadratic probing: try index + 1^2, index + 2^2, index + 3^2...
public boolean addSession(String sessionId) {
int index = hash(sessionId);
int i = 0;
while (sessions[index] != null && i < capacity) {
i++;
index = (hash(sessionId) + i * i) % capacity; // Quadratic probing
}
if (i == capacity) {
System.out.println("No space for session " + sessionId);
return false;
}
sessions[index] = sessionId;
System.out.println("Session " + sessionId + " stored at index " + index +
" (probe count: " + i + ")");
return true;
}
public static void main(String[] args) {
SessionManager manager = new SessionManager(7);
manager.addSession("session_001");
manager.addSession("session_002");
manager.addSession("session_003");
}
}
