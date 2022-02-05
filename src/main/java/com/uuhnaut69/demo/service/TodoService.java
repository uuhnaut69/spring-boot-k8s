package com.uuhnaut69.demo.service;

import com.uuhnaut69.demo.api.dto.TodoRequest;
import com.uuhnaut69.demo.api.exception.NotFoundException;
import com.uuhnaut69.demo.model.Todo;
import com.uuhnaut69.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(NotFoundException::new);
    }

    public Todo create(TodoRequest todoRequest) {
        var todo = new Todo();
        todo.setTask(todoRequest.task());
        return todoRepository.save(todo);
    }

    public Todo update(Long todoId, TodoRequest todoRequest) {
        var todo = findById(todoId);
        todo.setTask(todoRequest.task());
        return todoRepository.save(todo);
    }

    public Long delete(Long todoId) {
        todoRepository.deleteById(todoId);
        return todoId;
    }

    public Todo toggleStatus(Long todoId) {
        var todo = findById(todoId);
        todo.setDone(!todo.isDone());
        return todoRepository.save(todo);
    }
}
