import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatInput } from '@angular/material/input';
import { TodoService } from 'src/app/shared/services/todo.service';

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.scss'],
})
export class TodoFormComponent {
  @ViewChild('todoInput') todoInput!: MatInput;
  value: string = '';

  constructor(private todoService: TodoService) {}

  onSubmit() {
    this.todoService.addTodo({ text: this.value, completed: false });
    this.value = '';
  }
}
