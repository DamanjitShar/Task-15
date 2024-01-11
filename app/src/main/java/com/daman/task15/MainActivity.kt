// MainActivity.kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daman.task15.databinding.ActivityMainBinding

class MainActivity<ActivityMainBinding> : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val taskRepository = TaskRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example usage
        val task = TaskModel(taskName = "Sample Task", taskDescription = "This is a sample task.")

        // Add Task
        taskRepository.addTask(task) { isSuccessful ->
            if (isSuccessful) {

            } else {

            }
        }

        // Update Task
        task.taskName = "Updated Task"
        taskRepository.updateTask(task) { isSuccessful ->
            if (isSuccessful) {
                // Task updated successfully
            } else {
                // Error updating task
            }
        }

        // Delete Task
        val taskIdToDelete = "your_task_id"
        taskRepository.deleteTask(taskIdToDelete) { isSuccessful ->
            if (isSuccessful) {
                // Task deleted successfully
            } else {
                // Error deleting task
            }
        }

        // Get Tasks
        taskRepository.getTasks { tasks ->
            // Use the list of tasks
            for (t in tasks) {
                println("Task: ${t.taskName}, Description: ${t.taskDescription}")
            }
        }
    }
}
