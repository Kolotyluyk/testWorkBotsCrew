package service;


import dao.BookRepository;
import entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookService {
  BookRepository bookRepository;

    public void setCommend(String commendLine) {
        String [] commandAndAutorAndBook=commendLine.split(" ");
//find command
        String command = null;
        if (commandAndAutorAndBook[0]!=null)
         command=commandAndAutorAndBook[0];
        else return;
     Book book =new Book();
        switch (command){
            case "add":{
                book.setAutor(commendLine.replace(command,"").split("\"")[0]);
                book.setBook_name(commendLine.split("\"")[1].replace("\"",""));

                bookRepository.save(book);
                break;
            }
               case "remove":{
                   if (commendLine.contains("\""))
                       book.setBook_name(commendLine.split("\"")[1].replace("\"",""));
                   List books = bookRepository.getBooksByName(book.getBook_name());
                   if(!books.isEmpty() && books.size()<1) {
                       System.out.println("We found " + books.size() + " books");
                       books.forEach(System.out::println);
                       System.out.print("please choose which book to delete:");
                       Scanner scanner= new Scanner(System.in);
                       int eletentPosition=scanner.nextInt();
                       book= (Book) books.get(eletentPosition+1);
                   }
                   else
                       book= (Book) books.get(0);
                   bookRepository.delete(book);
                break;
            }
            case "edit":{
                String newbookName = null;
                String newAutor = null;
                if (commendLine.contains("\"")) {
                    book.setBook_name(commendLine.split("\"")[1]);
                    if(commendLine.split("\"")[2]!=" ")
                        newAutor=commendLine.split("\"")[2];
                   newbookName=commendLine.split("\"")[3];
                }
                else break;
                List books = bookRepository.getBooksByName(book.getBook_name());
                if(books.isEmpty()) break;
                if(books.size()>1){
                    System.out.println("We found " + books.size() + " books");
                    books.forEach(System.out::println);
                    System.out.print("please choose which book to update:");
                    Scanner scanner= new Scanner(System.in);
                    int eletentPosition=scanner.nextInt();
                    book= (Book) books.get(eletentPosition-1);
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
                if (commendLine.contains("\""))
                    book.setBook_name(commendLine.split("\"")[1].replace("\"",""));

                List books=bookRepository.getBooksByName(book.getBook_name());
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
