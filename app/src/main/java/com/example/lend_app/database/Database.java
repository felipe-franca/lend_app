package com.example.lend_app.database;

import androidx.room.RoomDatabase;

import com.example.lend_app.database.dao.RoomUserDao;
import com.example.lend_app.model.User;

@androidx.room.Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
  public abstract RoomUserDao getRoomUserDao();
}
