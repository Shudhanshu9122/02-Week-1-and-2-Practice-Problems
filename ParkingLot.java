public class ParkingLot {
static class ParkingSpot {
String licensePlate;
boolean isOccupied;
ParkingSpot() {
this.isOccupied = false;
}
}
private ParkingSpot[] spots;
private int capacity;
public ParkingLot(int capacity) {
this.capacity = capacity;
this.spots = new ParkingSpot[capacity];
for (int i = 0; i < capacity; i++) {
spots[i] = new ParkingSpot();
}
}
private int hash(String licensePlate) {
return Math.abs(licensePlate.hashCode()) % capacity;
}
// Linear probing: if spot taken, try next spot
public boolean parkCar(String licensePlate) {
int index = hash(licensePlate);
int originalIndex = index;
int probes = 0;
// Linear probe until we find empty spot
while (spots[index].isOccupied) {
probes++;
index = (index + 1) % capacity; // Linear probing
// Checked all spots
if (index == originalIndex) {
System.out.println("Parking lot FULL!");
return false;
}
}
spots[index].licensePlate = licensePlate;
spots[index].isOccupied = true;
System.out.println("Parked " + licensePlate + " at spot " + index +
" (probes: " + probes + ")");
return true;
}
public boolean findCar(String licensePlate) {
int index = hash(licensePlate);
int originalIndex = index;
while (spots[index].isOccupied) {
if (spots[index].licensePlate.equals(licensePlate)) {
System.out.println("Found " + licensePlate + " at spot " + index);
return true;
}
index = (index + 1) % capacity;
if (index == originalIndex) break;
}
System.out.println(licensePlate + " not found");
return false;
}
public static void main(String[] args) {
ParkingLot lot = new ParkingLot(5);
lot.parkCar("ABC123");
lot.parkCar("XYZ789");
lot.parkCar("DEF456"); // May collide and probe
lot.findCar("XYZ789");
lot.findCar("NOTHERE");
}
}
