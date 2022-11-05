public class Reader {
    private String _name;
    private String _email;
    private int _idReader;
    private int _numberBooks;

    Reader(){

    }
    Reader(int id, String name, String email, int numberBooks){
        _idReader = id;
        _name = name;
        _email = email;
        _numberBooks = numberBooks;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
    public void set_name(String _name) {
        this._name = _name;
    }
    public void set_numberBooks(int _numberBooks) {
        this._numberBooks = _numberBooks;
    }
    public void set_idReader(int id){ _idReader = id; }

    public void add_numberBooks(){ _numberBooks++; }
    public void sub_numberBooks(){ _numberBooks--; }

    public String get_email() {
        return _email;
    }
    public int get_numberBooks() {
        return _numberBooks;
    }
    public int get_idReader() {
        return _idReader;
    }
    public String get_name() {
        return _name;
    }

}
