package entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book", catalog = "library_managing")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  long id;

    private  String book_name;
    private  String autor;
}
