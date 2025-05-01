import java.util.Scanner;

public class Ring {
    public static void main(String[] args) {
        int temp, i, j;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int num = in.nextInt();

        Rr[] proc = new Rr[num];
        for (i = 0; i < num; i++) {
            proc[i] = new Rr();
            proc[i].index = i;
            System.out.println("Enter the ID of Process " + (i + 1) + ":");
            proc[i].id = in.nextInt();
            proc[i].state = "active";
            proc[i].f = 0;
        }

        // Sort processes by ID
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - 1; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        System.out.println("Sorted processes:");
        for (i = 0; i < num; i++) {
            System.out.println("[" + i + "] ID: " + proc[i].id);
        }

        proc[num - 1].state = "inactive"; // Last process is the coordinator
        System.out.println("Process " + proc[num - 1].id + " is the coordinator.");

        while (true) {
            System.out.println("1. Start Election");
            System.out.println("2. Quit");
            int choice = in.nextInt();

            if (choice == 1) {
                System.out.println("Enter the process number to initiate election:");
                int init = in.nextInt() - 1;
                int temp1 = init + 1;
                int temp2 = init;
                int[] arr = new int[num];
                int count = 0;

                while (temp2 != temp1) {
                    if ("active".equals(proc[temp1].state) && proc[temp1].f == 0) {
                        System.out.println("Process " + proc[init].id + " sends message to " + proc[temp1].id);
                        proc[temp1].f = 1;
                        arr[count++] = proc[temp1].id;
                        init = temp1;
                    }
                    temp1 = (temp1 + 1) % num;
                }

                arr[count++] = proc[temp1].id;
                int max = -1;
                for (j = 0; j < count; j++) {
                    max = Math.max(max, arr[j]);
                }

                System.out.println("Process " + max + " is the new coordinator.");
                for (i = 0; i < num; i++) {
                    if (proc[i].id == max) {
                        proc[i].state = "inactive";
                    }
                }
            } else if (choice == 2) {
                System.out.println("Program terminated.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}

class Rr {
    public int index;
    public int id;
    public int f;
    public String state;
}
