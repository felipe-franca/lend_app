package com.example.lend_app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lend_app.model.User;

@Dao
public interface RoomUserDao {
  @Insert
  void save(User user);

  @Query("SELECT * FROM User LIMIT 1")
  User get();

  @Query("DELETE FROM User WHERE 1=1")
  void remove();
}
