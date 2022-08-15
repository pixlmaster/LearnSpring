package com.spring.spring.Services.ToDoService;

import com.spring.spring.ToDoBeans.ToDo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ToDoService {
    private static List<ToDo> todos = new ArrayList<ToDo>();
    private static  int todoCount=1;
    static {
        todos.add(new ToDo(todoCount++,
                "akash",
                "this ia a todo",
                LocalDate.now().plusYears(1),
                false));
        todos.add(new ToDo(todoCount++,
                "akas",
                "this ia a todo",
                LocalDate.now().plusYears(1),
                false));
        todos.add(new ToDo(todoCount++,
                "aka",
                "this ia a todo",
                LocalDate.now().plusYears(1),
                false));
    }

    public List<ToDo> findByUsername(String username){
        return todos;
    }

    public void addTodo(String username,
                        String description,
                        LocalDate date,
                        boolean done){
        todos.add(new ToDo(todoCount++,
                username,
                description,
                date,
                done));
    }

    public void addTodo(int id, String username,
                        String description,
                        LocalDate date,
                        boolean done){
        todos.add(new ToDo(id,
                username,
                description,
                date,
                done));
    }
    public boolean modifyTodo(ToDo todo) {
        boolean isdeleted = delTodo(todo.getId());
        addTodo(todo.getId(),
                todo.getUsername(),
                todo.getDescription(),
                todo.getTargetDate(),
                todo.isDone());
        return isdeleted;

    }
    public boolean delTodo(Integer id) {

        return todos.removeIf(todo->(todo.getId()==id));

    }

    public ToDo findById(int id) {
        try{
            return todos.stream().filter(todo->(todo.getId()==id)).findFirst().get();
        }
        catch (NoSuchElementException e){
            return null;
        }

    }


}
