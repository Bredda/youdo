import { Component, OnInit } from '@angular/core';
import { TodoService } from 'src/app/shared/services/todo.service';

@Component({
  selector: 'app-todo-info',
  templateUrl: './todo-info.component.html',
  styleUrls: ['./todo-info.component.scss'],
})
export class TodoInfoComponent {
  constructor(public todoService: TodoService) {}
}
