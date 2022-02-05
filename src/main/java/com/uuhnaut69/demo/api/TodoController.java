package com.uuhnaut69.demo.api;

import com.uuhnaut69.demo.api.dto.TodoRequest;
import com.uuhnaut69.demo.api.dto.TodoResponse;
import com.uuhnaut69.demo.model.Todo;
import com.uuhnaut69.demo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoResponse> findAll() {
        return todoService.findAll().stream().map(this::toResponse).collect(toList());
    }

    @GetMapping("/{todoId}")
    public TodoResponse findById(@PathVariable Long todoId) {
        var todo = todoService.findById(todoId);
        return toResponse(todo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody @Valid TodoRequest todoRequest) {
        var todo = todoService.create(todoRequest);
        return toResponse(todo);
    }

    @PutMapping("/{todoId}")
    public TodoResponse update(
            @PathVariable Long todoId, @RequestBody @Valid TodoRequest todoRequest) {
        var todo = todoService.update(todoId, todoRequest);
        return toResponse(todo);
    }

    @DeleteMapping("/{todoId}")
    public Long delete(@PathVariable Long todoId) {
        return todoService.delete(todoId);
    }

    @PatchMapping("/{todoId}")
    public TodoResponse toggleStatus(@PathVariable Long todoId) {
        var todo = todoService.toggleStatus(todoId);
        return toResponse(todo);
    }

    private TodoResponse toResponse(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTask(), todo.isDone(), todo.getCreatedDate());
    }
}
