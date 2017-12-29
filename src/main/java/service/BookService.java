package service;


import dao.BookRepository;
import entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookService {
  BookRepository bookRepository;



    public void setCommend(String commendLine) {


        String [] commandAndAutorAndBook=commendLine.split(" ");

        String command = null;
        if (commandAndAutorAndBook[0]!=null)
         command=commandAndAutorAndBook[0];

        Book book =new Book();
        System.out.println(commandAndAutorAndBook.length);

        if (commandAndAutorAndBook.length>=2 &&
                commandAndAutorAndBook[1]!="") {
            book.setAutor(commandAndAutorAndBook[1]);
        }
        if (commandAndAutorAndBook.length>=3 &&
        commandAndAutorAndBook[1]!="" && commandAndAutorAndBook[2]!="")
        {
            book.setAutor(commandAndAutorAndBook[1]);
             book.setBook_name(commandAndAutorAndBook[2]);
        }
        switch (command){
            case "add":{
                    bookRepository.save(book);
                break;
            }
               case "remove":{
                   List books = bookRepository.getBooksByName(book.getAutor());
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
                bookRepository.update(book);
                break;
            }
            case "showAll":{
                bookRepository.getBooks();
                break;
            }
            case "show":{
                bookRepository.getBooksByName(book.getAutor());

                break;
            }

        }



    }

    public BookService() {
        bookRepository= new BookRepository();
    }


}
