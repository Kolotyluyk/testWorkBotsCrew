package service;


import dao.BookRepository;
import entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookService {
  BookRepository bookRepository;

    public void setCommend(String commendLine) {
        String command = null;
        if(commendLine.equals(null) ||commendLine.replace(" ", "").equals(""))
        {
            System.out.println("please write command and parameter");
            return;
        }
//find command
        String [] commandAndAutorAndBook=commendLine.split(" ");
        for (String s: commandAndAutorAndBook) {
            if (!s.equals("")) {
                command=s;
                break;
            }
        }


        Book book =new Book();
        switch (command){
            case "add":{
                int s=commendLine.split("\"").length;
                if (commendLine.contains("\"") && commendLine.split("\"").length>1 && !commendLine.split("\"")[1].replace(" ", "").equals(""))
                {
                    book.setBook_name(commendLine.split("\"")[1].replace("\"", ""));
                    if (!commendLine.split("\"")[1].replace("\"", "").replace(" ","").replace(command,"").equals("")){
                        book.setAutor(commendLine.split("\"")[1].replace("\"", "").replace(" ","").replace(command,""));
                  bookRepository.save(book);
                    }
                    else
                        System.out.println("you don't wrote book autor");
            }
            else
                    System.out.println("you don't wrote book name");
            break;
            }
               case "remove":{
                   if (commendLine.contains("\""))
                       book.setBook_name(commendLine.split("\"")[1].replace("\"",""));
                   List books = bookRepository.getBooksByName(book.getBook_name());
                   if(!books.isEmpty() && books.size()>1) {
                       System.out.println("We found " + books.size() + " books");
                       books.forEach(System.out::println);
                       System.out.print("please write position book which to delete:");
                       Scanner scanner= new Scanner(System.in);
                       int eletentPosition=scanner.nextInt();
                       book= (Book) books.get(eletentPosition-1);
                   }
                   else
                   if(books.isEmpty())
                   {
                       System.out.println("you write wrong name, please reply");
                       break;
                   }
                       book= (Book) books.get(0);
                   bookRepository.delete(book);
                break;
            }
            case "edit":{
                String newbookName = null;
                String newAutor = null;
                if (commendLine.contains("\"")) {
                    book.setBook_name(commendLine.split("\"")[1]);
                    if(commendLine.split("\"").length>1 && !commendLine.split("\"")[2].replace(" ","").equals(""))
                        newAutor=commendLine.split("\"")[2].replace(" ", "");
                    if(commendLine.split("\"").length>2 && !commendLine.split("\"")[3].replace(" ", "").equals(""))
                        newbookName=commendLine.split("\"")[3];
                        else
                            {
                                System.out.println("you write wrong new name, please reply");
                                break;
                            }
                }
                else break;
                List books = bookRepository.getBooksByName(book.getBook_name());
                if(books.isEmpty())
                {
                    System.out.println("you write wrong old name, please reply");
                    break;
                }
                if(books.size()>1){
                    System.out.println("We found: " + books.size() + " books");
                    books.forEach(System.out::println);
                    System.out.print("please write position book which to edit:");
                    Scanner scanner= new Scanner(System.in);
                    int elementPosition=scanner.nextInt();
                    book= (Book) books.get(elementPosition-1);
                }
                else
                    book= (Book) books.get(0);
                if( newbookName!=""&& newbookName!=null){
                    book.setBook_name(newbookName);
                    if(newAutor!="" &&newAutor!=null )
                    book.setAutor(newAutor);
                 bookRepository.update(book);

                }
                break;
            }
            case "showAll":{
                List books=bookRepository.getBooks();
                break;
            }
            case "show":{
                if (commendLine.contains("\"")&&commendLine.split("\"").length>1 && !commendLine.split("\"")[1].replace(" ", "").equals(""))
                    book.setBook_name(commendLine.split("\"")[1].replace("\"",""));

                List books=bookRepository.getBooksByName(book.getBook_name());
                if(books.isEmpty())
                    System.out.println("Database don't book with this name");
                books.forEach(System.out::println);
                break;
            }
            default:
                System.out.println("You have written the wrong command");
                System.out.println("Please repeat");

        }



    }

    public BookService() {
        bookRepository= new BookRepository();
    }




}
