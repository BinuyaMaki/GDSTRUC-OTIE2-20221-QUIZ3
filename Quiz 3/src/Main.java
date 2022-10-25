import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // initialize
        ArrayQueue matchmakingQueue = new ArrayQueue(8);
        ArrayQueue game = new ArrayQueue(5);
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // variables
        int x = 0;
        int id = 0;
        int games = 0;
        int gameCap = 5;

        // matchmaking loop
        while (games != 10) {

            System.out.println("\n" +"Matchmaking initiated.");
            System.out.println("Games created: " + games);
            System.out.println("Creating more games...");

            while (matchmakingQueue.size() < gameCap) {
                // add players to waiting room
                x = random.nextInt(8);

                for (int i = 0; i < x; i++) {
                    id = random.nextInt(100);
                    matchmakingQueue.enqueue(new Player(id));
                }

                System.out.println(matchmakingQueue.size() + " Player/s are queueing for a game.");

                if (matchmakingQueue.size() < gameCap) {
                    System.out.println("Looking for more players...");
                }

                System.out.println("Input any character to continue:");
                scanner.nextLine();
            }

            // remove players from matchmakingQueue and move to game.
            Player removedPlayer;

            for (int i = 0; i < gameCap; i++) {
                    removedPlayer = matchmakingQueue.dequeue();
                    game.enqueue(removedPlayer);
            }

            System.out.println("The following players have successfully entered a game:");
            game.printQueue();

            // clear game queue and increment games created
            for (int i = 0; i < gameCap; i++) {
                game.dequeue();
            }

            System.out.println(matchmakingQueue.size() + " Player/s are still queueing for a game.");
            games++;
        }

        // matchmaking end
        System.out.println("\n" + games + " Games have successfully been created, queueing has stopped!" );
    }
}