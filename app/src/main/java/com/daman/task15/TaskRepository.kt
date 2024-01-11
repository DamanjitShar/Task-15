// TaskRepository.kt

import com.google.firebase.database.*

class TaskRepository {
    private val databaseReference = FirebaseConfig.getDatabaseReference().child("tasks")

    fun addTask(task: TaskModel, callback: (Boolean) -> Unit) {
        val taskId = databaseReference.push().key
        task.taskId = taskId
        databaseReference.child(taskId!!).setValue(task).addOnCompleteListener {
            callback(it.isSuccessful)
        }
    }

    fun updateTask(task: TaskModel, callback: (Boolean) -> Unit) {
        databaseReference.child(task.taskId!!).setValue(task).addOnCompleteListener {
            callback(it.isSuccessful)
        }
    }

    fun deleteTask(taskId: String, callback: (Boolean) -> Unit) {
        databaseReference.child(taskId).removeValue().addOnCompleteListener {
            callback(it.isSuccessful)
        }
    }

    fun getTasks(callback: (List<TaskModel>) -> Unit) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tasks = mutableListOf<TaskModel>()
                for (taskSnapshot in snapshot.children) {
                    val task = taskSnapshot.getValue(TaskModel::class.java)
                    task?.let { tasks.add(it) }
                }
                callback(tasks)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(emptyList())
            }
        })
    }
}
