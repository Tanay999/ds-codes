import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5]; // Tracks process states (active/inactive)

    // Bring a process up
    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("Process " + up + " is already up.");
        } else {
            state[up - 1] = true;
            System.out.println("Process " + up + " held an election.");
            for (int i = up; i < 5; ++i) {
                System.out.println("Election message sent from Process " + up + " to Process " + (i + 1));
            }
            for (int i = up + 1; i <= 5; ++i) {
                if (state[i - 1]) {
                    System.out.println("Alive message sent from Process " + i + " to Process " + up);
                    break;
                }
            }
        }
    }

    // Bring a process down
    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("Process " + down + " is already down.");
        } else {
            state[down - 1] = false;
        }
    }

    // Send a message
    public static void mess(int mess) {
        if (state[mess - 1]) {
            if (state[4]) { // Check if the coordinator is active
                System.out.println("OK");
            } else {
                System.out.println("Process " + mess + " initiated an election.");
                for (int i = mess; i < 5; ++i) {
                    System.out.println("Election message sent from Process " + mess + " to Process " + (i + 1));
                }
                for (int i = 5; i >= mess; --i) {
                    if (state[i - 1]) {
                        System.out.println("Coordinator message sent from Process " + i + " to all.");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Process " + mess + " is down.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; ++i) {
            state[i] = true; // Initialize all processes as active
        }

        System.out.println("5 active processes are:");
        System.out.println("Processes up = P1 P2 P3 P4 P5");
        System.out.println("Process 5 is the coordinator.");

        int choice;
        do {
            System.out.println(" .......... ");
            System.out.println("1. Bring a process up.");
            System.out.println("2. Bring a process down.");
            System.out.println("3. Send a message.");
            System.out.println("4. Exit.");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Bring process up:");
                    int up = sc.nextInt();
                    if (up == 5) {
                        System.out.println("Process 5 is the coordinator.");
                        state[4] = true;
                    } else {
                        up(up);
                    }
                    break;
                case 2:
                    System.out.println("Bring a process down:");
                    int down = sc.nextInt();
                    down(down);
                    break;
                case 3:
                    System.out.println("Which process will send a message?");
                    int mess = sc.nextInt();
                    mess(mess);
                    break;
                case 4:
                    System.out.println("Program terminated.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}
