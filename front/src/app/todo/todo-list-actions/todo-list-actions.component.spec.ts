import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoListActionsComponent } from './todo-list-actions.component';

describe('TodoListActionsComponent', () => {
  let component: TodoListActionsComponent;
  let fixture: ComponentFixture<TodoListActionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodoListActionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoListActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
