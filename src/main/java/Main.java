import service.BookService;
import util.HibernateUtil;

import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        BookService bookService=new BookService();

           System.out.println("Instruction:\n" +
                   "Command: \n"+
        "add book { autor \"book name\"}                   example: add Martin Luther King \"Strength to love\"\n"+
        "remove book {\"book name\"}                       example: remove \"Strength to love\"\n"+
        "edit \"old book name\" \" new book name\"           example: edit \"Strength to love\" \"I have a dream\"\n"+
        "edit \"old book name\" new autor \" new book name\" example: edit \"Strength to love\" Bill Young \"I have a dream\"\n"+
        "show \"book name\"                                example: show \"Strength to love\"\n"+
        "showAll                                         example: showAll");
        while (true){ System.out.print("U:");
        String command=readFromDisplayLine();
         bookService.setCommend(command);

    }

    }



    private static String readFromDisplayLine(){
    return scanner.nextLine();
    }

}
