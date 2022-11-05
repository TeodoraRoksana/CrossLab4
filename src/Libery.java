import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Libery {
    private List<Reader> _listReaders;
    private List<Book> _listBooks;
    private List<Admin> _listAdmins;

    Libery(){
        _listBooks = new ArrayList<>();
        _listReaders = new ArrayList<>();
        _listAdmins = new ArrayList<>();
    }

    public void mainFanc() throws FileNotFoundException, ParseException {
        readData();

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\nВиберіть, що зробити\n" +
                    "(від 1 до 6, цифра відповідає пункту, 0 - вийти з програми)");
            int item = scanner.nextInt();

            switch (item){
                case 1:
                    sortBooksByYear();
                    break;
                case 2:
                    searchEmails2Books();
                    break;
                case 3:
                    System.out.println("Введіть автора:");
                    scanner.nextLine();
                    String autor = scanner.nextLine();
                    numberReadersWithAutor(autor);
                    break;
                case 4:
                    numberMaxCountOfBooks();
                    break;
                case 5:
                    spamEmail();
                    break;
                case 6:
                    readersDeadline();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Не правильне число!");
            }
        }
    }

    public void sortBooksByYear(){
        printListBooks(_listBooks.stream()
                .sorted(Comparator.comparing(Book::get_year))
                .collect(Collectors.toList()));
    }

    public void numberReadersWithAutor(String autor){
        List<Book> AutorsBooks = _listBooks.stream()
                .filter(book -> book.get_autor().equals(autor))
                .collect(Collectors.toList());

        List<Integer> readers = new ArrayList<>();
        for (int i = 0; i < _listAdmins.size(); i++) {
            for (int j = 0; j < AutorsBooks.size(); j++) {
                if(_listAdmins.get(i).get_idBook() == AutorsBooks.get(j).get_idBook())
                {
                    if(!readers.contains(_listAdmins.get(i).get_idReader())) {
                        readers.add(_listAdmins.get(i).get_idReader());
                        break;
                    }
                }
            }
        }

        System.out.println(readers.size() + " читачів мають книги автора " + autor);
    }

    public void numberMaxCountOfBooks(){
        System.out.println(_listReaders.stream()
                .max(Comparator.comparing(Reader::get_numberBooks)).orElse(null).get_numberBooks() + " книг");
    }

    public void readersDeadline(){
        List<Admin> admins = _listAdmins.stream()
                .filter(admin -> admin.calcDaysAfterDeadline() > 0)
                .collect(Collectors.toList());

        admins = admins.stream()
                .sorted(Comparator.comparing(Admin::get_idReader))
                .collect(Collectors.toList());

        for (int i = 0; i < admins.size(); i++) {
            if(i == 0 || admins.get(i).get_idReader() != admins.get(i - 1).get_idReader()) {
                System.out.println();
                int idR = admins.get(i).get_idReader();
                System.out.println(_listReaders.stream()
                        .filter(reader -> reader.get_idReader() == idR)
                        .collect(Collectors.toList())
                        .get(0).get_name());

                int idB = admins.get(i).get_idBook();

                Book book = _listBooks.stream()
                        .filter(b -> b.get_idBook() == idB)
                        .collect(Collectors.toList())
                        .get(0);
                System.out.println(book.get_autor() + " " + book.get_name()+ ". " + admins.get(i).calcDaysAfterDeadline() + " днів після дедлайну");
            }
            else{
                int idB = admins.get(i).get_idBook();

                Book book = _listBooks.stream()
                        .filter(b -> b.get_idBook() == idB)
                        .collect(Collectors.toList())
                        .get(0);
                System.out.println(book.get_autor() + " " + book.get_name() + ". " + admins.get(i).calcDaysAfterDeadline()+ " днів після дедлайну");
            }

        }
    }

    public void searchEmails2Books(){
        List<Reader> readers = _listReaders.stream()
                .filter(reader -> reader.get_numberBooks() > 2)
                .collect(Collectors.toList());

        for (int i = 0; i < readers.size(); i++) {
            System.out.println(readers.get(i).get_email());
        }
    }

    public void spamEmail(){
        List<Reader> readers2 = _listReaders.stream()
                .filter(reader -> reader.get_numberBooks() > 2)
                .collect(Collectors.toList());

        List<Reader> readers1 = _listReaders.stream()
                .filter(reader -> reader.get_numberBooks() <= 2)
                .collect(Collectors.toList());

        for (int i = 0; i < readers1.size(); i++) {
            System.out.println(readers1.get(i).get_email() + ": У нас нові книги!");
        }
        for (int i = 0; i < readers2.size(); i++) {
            System.out.println(readers2.get(i).get_email() + ": Не забудьте про дедлайни!");
        }
    }

    public void readData() throws FileNotFoundException, ParseException {
        File fileAdmins = new File("Admins.txt");
        File fileBooks = new File("Books.txt");
        File fileReeaders = new File("Readers.txt");

        Scanner scanner = new Scanner(fileAdmins);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] elements = line.split("-");
            _listAdmins.add(new Admin(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), elements[2]));
        }

        scanner.close();

        scanner = new Scanner(fileBooks);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] elements = line.split("-");
            _listBooks.add(new Book(Integer.parseInt(elements[0]), elements[1], elements[2], Integer.parseInt(elements[3])));
        }

        scanner.close();

        scanner = new Scanner(fileReeaders);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] elements = line.split("-");
            _listReaders.add(new Reader(Integer.parseInt(elements[0]), elements[1], elements[2], Integer.parseInt(elements[3])));
        }

        scanner.close();
    }

    public void printListBooks(List<Book> list){
        System.out.println("Date Book:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get_name() + " "
                    + list.get(i).get_autor() + " "
                    + list.get(i).get_year());
        }
    }
    public void printListReaders(List<Reader> list){
        System.out.println("Date Reader:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get_name() + " "
                    + list.get(i).get_email() + " "
                    +list.get(i).get_numberBooks() + " книг");
        }
    }

}
