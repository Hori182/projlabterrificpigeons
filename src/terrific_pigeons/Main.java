package terrific_pigeons;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        String commandparam[] = command.split(" ");
        while(command != "exit")
        {
                System.out.println(commandparam[0] + ":" + commandparam[1] + ":" + commandparam[2]);
                break;
        }

    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
