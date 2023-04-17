package com.example.feelingfinder.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "goals")
public class Goal {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String description;
    @NonNull
    public Boolean status;

    public Goal(){
        this.description = "Default Description";
        this.status = false;
    }
    public Goal(@NonNull String desc) {
        this.description = desc;
        this.status = false;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public Boolean getStatus() {
        return status;
    }

    @NonNull
    public String getDescription() {
        return description;
    }
}
