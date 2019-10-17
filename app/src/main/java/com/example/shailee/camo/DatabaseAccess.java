package com.example.shailee.camo;


//class to make database access

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;


public class DatabaseAccess extends AppCompatActivity  {
    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c= null;
    Cursor d=null;
    Cursor e=null;
    ImageView img;




    //private constructor creation so that this class wont be accessed or instantiated by any other classses
    private DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);
    }
    //to return the single instance of the database
    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }
    //to open the connection to the database

    public void open(){
        this.db=openHelper.getWritableDatabase();
    }
    //to close the connection

    public void close(){
        if(db!=null){
           this.db.close();
        }

    }
//    public String getData(String commonName) {
//
//        String str;
//        c = db.rawQuery("Select scientific_name, family, genus,benefits from leaf where common_name = ?", new String[]{commonName});
//        StringBuffer buffer = new StringBuffer();
//
//        //d = db.rawQuery("Select images from leaf where common_name=?", new String[]{commonName});
//        while (c.moveToNext()) {
//            String scientificName = c.getString(0);
//            String Family = c.getString(1);
//            String Genus = c.getString(2);
//            String benefits = c.getString(3);
//
//
//
//            buffer.append("Scientific name : "  + scientificName + "\n" + "Family Name : " + Family + "\n" + "Genus : " + Genus + "\n" + "Benefits : "+ benefits);
//        }
////        while(d.moveToNext()){
////            img.findViewById(R.id.leafdisplay);
////            byte[] buff=new byte[1024];
////
////            img.setImageBitmap(bitmap);
////
////
////        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        return buffer.toString();
//
//    }
    ArrayList<ModelItem> modelItemArrayList = new ArrayList<>();

    public ArrayList<ModelItem> getData(String commonName) {
        modelItemArrayList.clear();

        c = db.rawQuery("Select common_name, scientific_name, family, genus,benefits, images from leaf where common_name LIKE ?", new String[]{commonName + "%"});

        while (c.moveToNext()) {
            String common_name = c.getString(c.getColumnIndex("common_name"));
            String scientificName = c.getString(c.getColumnIndex("scientific_name"));
            String Family = c.getString(c.getColumnIndex("family"));
            String Genus = c.getString(c.getColumnIndex("genus"));
            String benefits = c.getString(c.getColumnIndex("benefits"));
            byte[] image = c.getBlob(c.getColumnIndex("images"));
            ModelItem modelItem = new ModelItem(common_name, scientificName, Genus, Family, benefits, image);
            modelItemArrayList.add(modelItem);
            break;
        }

        return modelItemArrayList;
    }
    public byte[] getImage(String commonName) {
        byte[] data = null;
        d = db.rawQuery("SELECT images FROM leaf WHERE common_name = ?", new String[]{commonName});
        d.moveToFirst();
        while (!d.isAfterLast()) {
            data = d.getBlob(0);
            break;  // Assumption: name is unique
        }
        d.close();
        return data;
    }

//    public void getClassify(String commonName){
//        String toplevel0Des = "Select* form leaf where  common_name=  topLables[2];
//
//
//    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DatabaseAccess.this, MainActivity.class);
        startActivity(intent);
    }
    public Leave loadHandler(String commonName){

        String query = String.format("Select * FROM leaf where common_name= '%s' ;", commonName);


        e = db.rawQuery(query,null);
        Log.d("tag", "loadHandler: "+query);
        Leave leave = null;
        while (e.moveToNext()) {
            leave= new Leave(e.getString(1),e.getString(2),e.getString(5),e.getString(4));
        }
        e.close();
        db.close();
        return leave;
    }
    }



