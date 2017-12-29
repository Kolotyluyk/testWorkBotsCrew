import service.BookService;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BookService bookService=new BookService();


       while (true){
        printOnDisplay("Instruction/n +" +
        "add book"+
        "remove book"+
        "edit book");

        printOnDisplay("U:");
        String command=readFromDisplayLine();
         bookService.setCommend(command);

    }
    }

    private static void printOnDisplay(String text){
        System.out.println(text);
    }

    private static String readFromDisplayLine(){
    return scanner.nextLine();
    }

}
