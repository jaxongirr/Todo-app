package com.example.todoapp.controller;

import com.example.todoapp.domain.ToDo;
import com.example.todoapp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToDoController {

    @Autowired
    ToDoRepository toDoRepository;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/todos")
    public String getAll(Model model) {
        model.addAttribute("todos", toDoRepository.findAll());
        return "todos";
    }

    @PostMapping("/create")
    public String create(@RequestParam String todoItem,
                         @RequestParam String status, Model model) {
        ToDo toDo = new ToDo();
        toDo.setTodoItem(todoItem);
        toDo.setCompleted(status);
        toDoRepository.save(toDo);
        model.addAttribute("todos", toDoRepository.findAll());
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        toDoRepository.deleteById(id);
        model.addAttribute("todos", toDoRepository.findAll());
        return "redirect:/todos";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        ToDo toDo = toDoRepository.findById(id).get();
        if("Yes".equals(toDo.getCompleted())) {
            toDo.setCompleted("No");
        }
        else {
            toDo.setCompleted("Yes");
        }
        toDoRepository.save(toDo);
        model.addAttribute("todos", toDoRepository.findAll());
        return "redirect:/todos";
    }
}
