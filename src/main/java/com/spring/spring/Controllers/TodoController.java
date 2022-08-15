package com.spring.spring.Controllers;

import com.spring.spring.Services.ToDoService.ToDoService;
import com.spring.spring.ToDoBeans.ToDo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    private ToDoService toDoService;

    public  TodoController(ToDoService toDoService){
        super();
        this.toDoService = toDoService;
    }


    @RequestMapping("/list-todos")
    public  String listAllTodos(ModelMap model){
        List<ToDo> todos = toDoService.findByUsername("akash");
        model.addAttribute("todos",todos);
        return "listTodos";
    }
    @RequestMapping(value = "/add-todo",method = RequestMethod.GET)
    public  String getaddTodo(ModelMap model){
        String username = (String)model.get("name");
        ToDo todo = new ToDo(0,
                username,
                "",
                LocalDate.now().plusYears(1),
                false);
        model.put("todo", todo);
        return "todo";
    }


    @RequestMapping(value = "/add-todo",method = RequestMethod.POST)
    public  String POSTaddTodo(ModelMap model,
                               @Valid ToDo todo,
                               BindingResult result){
        model.put("todo", todo);
        if(result.hasErrors()){
            return "todo";
        }

        String username = (String) model.get("name");

        toDoService.addTodo(username,
                todo.getDescription(),
                todo.getTargetDate(),
                false);


        return "redirect:list-todos";
    }

    @RequestMapping(value = "/delete-todo",method = RequestMethod.GET)
    public  String GetdeleteTodo(@RequestParam int id){

        boolean isDeleted = toDoService.delTodo(id);

        return "redirect:list-todos";
    }

    @RequestMapping(value = "/update-todo",method = RequestMethod.GET)
    public  String GetModifyTodo(@RequestParam int id, ModelMap model){

        ToDo todo = toDoService.findById(id);

        if(todo==null){
            return "redirect:list-todos";
        }

        model.addAttribute("todo",todo);

        return "todo";

    }

    @RequestMapping(value = "/update-todo",method = RequestMethod.POST)
    public  String PostModifyTodo(
            ModelMap model,
            @Valid ToDo todo,
            BindingResult result
    ){

        model.put("todo", todo);
//        if(result.hasErrors()){
//            System.out.println("error");
//            return "todo";
//        }

        String username = (String) model.get("name");
        todo.setUsername(username);

        System.out.println(toDoService.modifyTodo(todo));;


        return "redirect:list-todos";

    }

}
