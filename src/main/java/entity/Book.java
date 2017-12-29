package entity;


import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book", catalog = "library_managing")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Long id;

    private  String book_name;
    private  String autor;
}
