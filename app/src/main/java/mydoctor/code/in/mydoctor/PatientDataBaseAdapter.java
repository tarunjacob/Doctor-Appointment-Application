package mydoctor.code.in.mydoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PatientDataBaseAdapter
{
    static final String DATABASE_NAME = "patient.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
// SQL Statement to create a new database.
    static final String DATABASE_CREATE = "CREATE TABLE Patients(PatientName text primary key not null,Age text not null,Weight text not null,BloodGroup text not null,Gender text not null,Date text not null,Time text not null,Category text not null,Reason text not null);";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  PatientDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  PatientDataBaseAdapter open() throws SQLException
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

    public void insertEntry(String Name,String Age,String Weight,String BGroup,String Gender,String Date,String Time)
    {
        ContentValues newValues = new ContentValues();
// Assign values for each row.
        newValues.put("PATIENT NAME",Name);
        newValues.put("AGE",Age);
        newValues.put("WEIGHT",Weight);
        newValues.put("BLOOD GROUP",BGroup);
        newValues.put("GENDER",Gender);
        newValues.put("DATE",Date);
        newValues.put("TIME",Time);

// Insert the row into your table
        db.insert("Patients", null, newValues);
///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public void insertEntry1(String Category,String Reason)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("CATEGORY",Category);
        newValues.put("REASON",Reason);

        db.insert("Patients", null, newValues);
    }

    public int deleteEntry(String UserName)
    {
//String id=String.valueOf(ID);
        String where="PATIENT NAME=?";
        int numberOFEntriesDeleted= db.delete("Patients", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public boolean getSingleEntry(String Name) throws SQLException
    {
        //Cursor cursor=db.query("LOGIN", null, " EMAIL=?", new String[]{Email}, null, null, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Patients WHERE Name=?", new String[]{Name});
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

    public void  updateEntry(String Name)
    {
// Define the updated row content.
        ContentValues updatedValues = new ContentValues();
// Assign values for each row.
        updatedValues.put("NAME", Name);

        String where="NAME = ?";
        db.update("Patients",updatedValues, where, new String[]{Name});
    }
}