package juniafirdaus.com.dicodingkamus.English;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import juniafirdaus.com.dicodingkamus.Kamus;


/**
 * Created by Juna on 2/5/2018.
 */

public class DatabaseHelperEnglish extends SQLiteAssetHelper {

    private static final String DB_NAME = "english";
    private static final int DB_VER = 1;

    private static final String TB_DATA = "english";
    private static final String COL_ID = "_id";
    private static final String COL_ISTILAH = "nama";
    private static final String COL_ARTI = "arti";

    @SuppressLint("StaticFieldLeak")
    private static DatabaseHelperEnglish dbInstance;
    private static SQLiteDatabase db;

    private DatabaseHelperEnglish(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    static DatabaseHelperEnglish getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new DatabaseHelperEnglish(context);
            db = dbInstance.getWritableDatabase();
        }
        return dbInstance;
    }

    @Override
    public synchronized void close() {
        super.close();
        if (dbInstance != null) {
            dbInstance.close();
        }
    }

    List<Kamus> getAllKamus() {
        List<Kamus> lisKamus = new ArrayList<>();

        @SuppressLint("Recycle")
        Cursor cursor = db.query(TB_DATA, new String[]{COL_ID, COL_ID,
                COL_ARTI, COL_ISTILAH}, null, null, null, null, COL_ISTILAH);
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();

            do {
                Kamus kamus = new Kamus();
                kamus.setArti(cursor.getString(cursor
                        .getColumnIndexOrThrow(COL_ARTI)));
                kamus.setIstilah(cursor.getString(cursor
                        .getColumnIndexOrThrow(COL_ISTILAH)));
                lisKamus.add(kamus);

            } while (cursor.moveToNext());
        }
        return lisKamus;

    }

    public Cursor getBukuByJudul(String query) {
        Cursor cursor;

        if (TextUtils.isEmpty(query)) {
            cursor = db.query(TB_DATA, new String[]{COL_ID, COL_ARTI,
                    COL_ISTILAH}, null, null, null, null, null, "10");
        } else {
            cursor = db.query(TB_DATA, new String[]{COL_ID, COL_ARTI,
                            COL_ISTILAH}, COL_ISTILAH + " like '" + query + "%'",
                    null, null, null, null);
        }
        return cursor;
    }
}
