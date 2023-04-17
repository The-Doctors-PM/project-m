package com.example.feelingfinder.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GoalsDAO {

    @Insert // Default: (onConflict = OnConflictStrategy.ABORT)
    void addGoal(Goal goal);

    @Update
    void changeGoal(Goal goal);

    @Delete
    void deleteGoal(Goal goal);

    @Query("SELECT * FROM goals")
    List<Goal> getAll();

    // In SQLite boolean are coded to integer, so 1 == true.
    @Query("SELECT *, 'rowid' FROM goals WHERE status = 1")
    List<Goal> getAllCompleted();

    @Query("SELECT *, 'rowid' FROM goals WHERE status = 0")
    List<Goal> getAllUncompleted();

}
