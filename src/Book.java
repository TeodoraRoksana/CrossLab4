public class Book {
    private String _name;
    private String _autor;
    private int _idBook;
    private int _year;

    Book(){

    }
    Book(int id, String name, String autor, int year){
        _idBook = id;
        _name = name;
        _autor = autor;
        _year = year;
    }

    public void set_name(String name){ _name = name; }
    public void set_year(int year) { _year = year; }
    public void set_autor(String autor) { _autor = autor; }
    public void set_idBook(int id){ _idBook = id; }

    public String get_name() {
        return _name;
    }

    public String get_autor() {
        return _autor;
    }

    public int get_year() {
        return _year;
    }

    public int get_idBook() {
        return _idBook;
    }
}
