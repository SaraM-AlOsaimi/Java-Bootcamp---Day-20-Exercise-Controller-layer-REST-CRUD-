package com.example.tasktrackersystem.TaskController;

import com.example.tasktrackersystem.ApiResponse.ApiResponse;
import com.example.tasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();




    // Display all tasks .
    @GetMapping("/get-tasks")
    public ArrayList<Task> getTodos(){
        return tasks;
    }
//
    // Create a new task (title , description , status)
    @PostMapping("/add-tasks")
    public ApiResponse addTask(@RequestBody Task task){
        tasks.add(task);
        return new ApiResponse("Task added successfully");
    }

    //Update a task
    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index , @RequestBody Task task){
        tasks.set(index, task);
        return new ApiResponse("Task updated successfully");
    }

    //Delete a task
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("Task deleted successfully");
    }

   // Change the task status as done or not done
   @PutMapping("/change/status/{index}")
   public ApiResponse changeStatus(@PathVariable int index, @RequestBody Task task) {
       Task existingTask = tasks.get(index);
       if (existingTask.getStatus().equalsIgnoreCase("Done")) {
           return new ApiResponse("Task is already marked as Done");
       }
       existingTask.setStatus(task.getStatus());
       return new ApiResponse("Task status updated successfully");
   }

    //Search for a task by given title
    @GetMapping("/search/{title}")
    public ApiResponse searchTaskByTitle(@PathVariable String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return new ApiResponse("Task found");
            }
        }
        return new ApiResponse("Task not found");
    }



}
