import java.util.Scanner;

import console.Console;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Console console = new Console();

        while (!Console.stopApp) {
            String command = scanner.nextLine();
            console.run(command);
        }
    }
}