import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ITodo } from 'src/app/shared/models/todo.model';
import { TodoService } from 'src/app/shared/services/todo.service';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss'],
})
export class TodoListComponent implements OnInit {
  todos$: Observable<ITodo[]> = of([]);

  constructor(private todoService: TodoService) {
    this.todos$ = this.todoService.getTodos();
  }

  ngOnInit(): void {}
}
