package mydoctor.code.in.mydoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
// SQL Statement to create a new database.
    static final String DATABASE_CREATE = "CREATE TABLE Contacts(Name text primary key not null,Email text not null,Password text not null,Phone integer not null,Address text);";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String Name,String Email,String Password,String Phone,String Address)
    {
        ContentValues newValues = new ContentValues();
// Assign values for each row.
        newValues.put("NAME",Name);
        newValues.put("EMAIL", Email);
        newValues.put("PASSWORD",Password);
        newValues.put("PHONE",Phone);
        newValues.put("ADDRESS",Address);


// Insert the row into your table
        db.insert("Contacts", null, newValues);
///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String UserName)
    {
//String id=String.valueOf(ID);
        String where="EMAIL=?";
        int numberOFEntriesDeleted= db.delete("Contacts", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public boolean getSingleEntry(String Email,String Password) throws SQLException
    {
        //Cursor cursor=db.query("LOGIN", null, " EMAIL=?", new String[]{Email}, null, null, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Contacts WHERE Email=? AND Password=?", new String[]{Email,Password});
        if (cursor != null) {
            if(cursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
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

    public void  updateEntry(String Email,String Password)
    {
// Define the updated row content.
        ContentValues updatedValues = new ContentValues();
// Assign values for each row.
        updatedValues.put("EMAIL", Email);
        updatedValues.put("PASSWORD",Password);

        String where="EMAIL = ?";
        db.update("Contacts",updatedValues, where, new String[]{Email});
    }
}