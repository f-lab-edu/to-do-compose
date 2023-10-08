package flab.eryuksa.todocompose.data.model

import java.util.UUID

data class Task(
    val title: String,
    val details: String,
    val isDone: Boolean,
    val id: String = UUID.randomUUID().toString()
)
