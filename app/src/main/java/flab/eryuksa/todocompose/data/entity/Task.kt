package flab.eryuksa.todocompose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "task")
data class Task(
    val title: String,
    val details: String,
    val isDone: Boolean,
    @PrimaryKey val id: String = UUID.randomUUID().toString()
)
