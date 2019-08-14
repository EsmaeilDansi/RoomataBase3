package com.example.mypc.roobdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyDao
{
    @Update
    void update(Information information);

   @Insert
    void insert(Information information);

   @Delete
   void delete(Information information);

   @Query("SELECT  * FROM  information")
   LiveData<List<Information>> getinformation();

}


