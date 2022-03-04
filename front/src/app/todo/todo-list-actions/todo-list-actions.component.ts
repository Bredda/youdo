import { Component, OnInit } from '@angular/core';
import { TodoService } from 'src/app/shared/services/todo.service';

@Component({
  selector: 'app-todo-list-actions',
  templateUrl: './todo-list-actions.component.html',
  styleUrls: ['./todo-list-actions.component.scss'],
})
export class TodoListActionsComponent implements OnInit {
  constructor(private todoService: TodoService) {}

  ngOnInit(): void {}

  clearList(): void {
    this.todoService.clearList();
  }
  markAllCompleted(): void {
    this.todoService.markAllCompleted();
  }
  markAllNotCompleted(): void {
    this.todoService.markAllNotCompleted();
  }
}
