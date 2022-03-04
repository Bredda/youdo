import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TodoRoutingModule } from './todo-routing.module';
import { TodoCardComponent } from './todo-card/todo-card.component';
import { TodoListComponent } from './todo-list/todo-list.component';
import { TodoFormComponent } from './todo-form/todo-form.component';
import { TodoInfoComponent } from './todo-info/todo-info.component';
import { MaterialModule } from '../shared/material.module';
import { FormsModule } from '@angular/forms';
import { TodoComponent } from './todo.component';
import { TodoListActionsComponent } from './todo-list-actions/todo-list-actions.component';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  declarations: [
    TodoCardComponent,
    TodoListComponent,
    TodoFormComponent,
    TodoInfoComponent,
    TodoListActionsComponent,
    TodoComponent,
    UserMenuComponent,
  ],
  imports: [
    CommonModule,
    TodoRoutingModule,
    MaterialModule,
    FormsModule,
    TranslateModule.forChild(),
  ],
})
export class TodoModule {}
