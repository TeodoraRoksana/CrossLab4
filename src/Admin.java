import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Admin {
    private int _idReader;
    private int _idBook;
    private Calendar _dataStart;
    private Calendar _dataPlanFinish;
    private Calendar _dataRealFinish;
    private SimpleDateFormat _simpleDateFormat;

     Admin(){
         _simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
         _dataStart = Calendar.getInstance();
         _dataPlanFinish = Calendar.getInstance();
         _dataRealFinish = Calendar.getInstance();
     }
     Admin(int idReader, int idBook, String dataStart) throws ParseException {
         _simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
         _dataStart = Calendar.getInstance();
         _dataPlanFinish = Calendar.getInstance();
         _dataRealFinish = Calendar.getInstance();

         _idReader = idReader;
         _idBook = idBook;
         _dataStart.setTime(_simpleDateFormat.parse(dataStart));
         calc_dataPlanFinish();
     }


     public void calc_dataPlanFinish(){
         _dataPlanFinish = _dataStart;
         _dataPlanFinish.add(Calendar.WEEK_OF_MONTH, 3);
     }

     public int calcDaysAfterDeadline(){
         Calendar now = Calendar.getInstance();
         int days = (int)ChronoUnit.DAYS.between(_dataPlanFinish.getTime().toInstant(), now.getTime().toInstant());

         return days;
     }

    public void set_idBook(int _idBook) { this._idBook = _idBook; }
    public void set_dataStart(String date) throws ParseException {
        _dataStart.setTime(_simpleDateFormat.parse(date));
    }
    public void set_idReader(int _idReader) { this._idReader = _idReader; }

    public int get_idReader() {
        return _idReader;
    }
    public int get_idBook() {
        return _idBook;
    }
    public String get_dataStart() {
         String date = _simpleDateFormat.format(_dataStart.getTime());
         return date;
    }
    public String get_dataPlanFinish() {
        String date = _simpleDateFormat.format(_dataPlanFinish.getTime());
        return date;
    }
    public String get_dataRealFinish() {
        String date = _simpleDateFormat.format(_dataRealFinish.getTime());
        return date;
    }
}
