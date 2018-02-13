package mydoctor.code.in.mydoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class DoctorDataBaseAdapter {
    static final String DATABASE_NAME = "doctor.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
// SQL Statement to create a new database.
    static final String DATABASE_CREATE = "CREATE TABLE Doctor(Category text not null,Time integer not null,Doctor text primary key not null);";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper

    private DataBaseHelper dbHelper;


    public DoctorDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DoctorDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String Category, String Time, String Doctor) {
        ContentValues newValues = new ContentValues();
// Assign values for each row.

        newValues.put("CATEGORY", Category);
        newValues.put("TIME", Time);
        newValues.put("DOCTOR", Doctor);


// Insert the row into your table
        db.insert("Doctor", null, newValues);
///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String Doctor) {
//String id=String.valueOf(ID);
        String where = "DOCTOR=?";
        int numberOFEntriesDeleted = db.delete("Patient", where, new String[]{Doctor});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public Cursor getSingleEntry(String Category) throws SQLException {
        //Cursor cursor=db.query("LOGIN", null, " EMAIL=?", new String[]{Email}, null, null, null);
        Cursor cursor = db.rawQuery("SELECT Doctor FROM Doctor WHERE Category=?", new String[]{Category});
        return cursor;
    }

    public Cursor getAllEntry()
    {
        //return db.query("Doctor",new String[] {"CATEGORY","TIME","DOCTOR"},null,null,null,null,null);
      //return String.valueOf(db.query("Doctor",new String[] {"CATEGORY","TIME","DOCTOR"},null,null,null,null,null));
        String selectQuery = "SELECT  * FROM  Doctor";
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return db.query("Doctor",new String[] {})
       /* if(cursor.moveToFirst())
        {
        }*/
      return cursor;
    }

        /*if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;*/

    public void updateEntry(String Doctor) {
// Define the updated row content.
        ContentValues updatedValues = new ContentValues();
// Assign values for each row.
        updatedValues.put("DOCTOR", Doctor);

        String where = "DOCTOR = ?";
        db.update("Doctor", updatedValues, where, new String[]{Doctor});
    }


}
