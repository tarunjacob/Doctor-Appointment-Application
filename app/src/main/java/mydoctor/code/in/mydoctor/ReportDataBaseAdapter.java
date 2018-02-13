package mydoctor.code.in.mydoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ReportDataBaseAdapter {
    static final String DATABASE_NAME = "report.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
// SQL Statement to create a new database.
    static final String DATABASE_CREATE = "CREATE TABLE Reports(ImageName text primary key not null,ImageData blob not null);";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public ReportDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ReportDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String ImageName, byte[] ImageData) {
        ContentValues newValues = new ContentValues();
// Assign values for each row.
        newValues.put("IMAGE NAME", ImageName);
        newValues.put("IMAGE DATA", ImageData);

// Insert the row into your table
        db.insert("Report", null, newValues);
///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String ImageName) {
//String id=String.valueOf(ID);
        String where = " ImageName=?";
        int numberOFEntriesDeleted = db.delete("Report", where, new String[]{ImageName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public boolean getSingleEntry(String ImageName) throws SQLException {
        //Cursor cursor=db.query("LOGIN", null, " EMAIL=?", new String[]{Email}, null, null, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Report WHERE ImageName=?", new String[]{ImageName});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
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

    public void updateEntry(String ImageName) {
// Define the updated row content.
        ContentValues updatedValues = new ContentValues();
// Assign values for each row.
        updatedValues.put("IMAGE NAME", ImageName);

        String where = "ImageName = ?";
        db.update("Report", updatedValues, where, new String[]{ImageName});
    }
}

