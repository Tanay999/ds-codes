import mpi.MPI;

public class ScatterGather {
    public static void main(String args[]) {
        // Initialize MPI execution environment
        MPI.Init(args);

        // Get the ID of the process
        int rank = MPI.COMM_WORLD.Rank();

        // Total number of processes
        int size = MPI.COMM_WORLD.Size();

        int root = 0; // Root process ID
        int sendbuf[] = null; // Data array to be scattered

        // Ensure sendbuf is initialized for all processes
        if (rank != root) {
            sendbuf = new int[size];
        }

        // Root process initializes the data
        if (rank == root) {
            sendbuf = new int[size]; // Initialize the array with size equal to the number of processes
            for (int i = 0; i < size; i++) {
                sendbuf[i] = (i + 1) * 10; // Populate the array with sample data: 10, 20, 30, ...
            }
            System.out.print("Processor " + rank + " has data: ");
            for (int i = 0; i < size; i++) {
                System.out.print(sendbuf[i] + " ");
            }
            System.out.println();
        }

        // Each process receives one element
        int recvbuf[] = new int[1];

        // Scatter the data
        MPI.COMM_WORLD.Scatter(sendbuf, 0, 1, MPI.INT, recvbuf, 0, 1, MPI.INT, root);
        System.out.println("Processor " + rank + " has data: " + recvbuf[0]);

        // Each process modifies its received data
        recvbuf[0] *= 2;
        System.out.println("Processor " + rank + " is doubling the data to: " + recvbuf[0]);

        // Gather the modified data back to the root process
        MPI.COMM_WORLD.Gather(recvbuf, 0, 1, MPI.INT, sendbuf, 0, 1, MPI.INT, root);

        // Root process displays the gathered data
        if (rank == root) {
            System.out.print("Process " + rank + " has data: ");
            for (int i = 0; i < size; i++) {
                System.out.print(sendbuf[i] + " ");
            }
            System.out.println();
        }

        // Finalize MPI execution environment
        MPI.Finalize();
    }
}
