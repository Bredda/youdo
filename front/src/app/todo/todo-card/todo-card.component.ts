import { Component, Input, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { ITodo } from 'src/app/shared/models/todo.model';
import { TodoService } from 'src/app/shared/services/todo.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-todo-card',
  templateUrl: './todo-card.component.html',
  styleUrls: ['./todo-card.component.scss'],
})
export class TodoCardComponent implements OnInit {
  @Input() todo!: ITodo;

  constructor(private _todoService: TodoService) {}

  ngOnInit(): void {}

  completed(): void {
    this._todoService.updateTodo(this.todo);
  }
  remove(): void {
    this._todoService.removeTodo(this.todo);
  }
}
