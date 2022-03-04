import { Injectable } from '@angular/core';
import { BehaviorSubject, combineLatest, map, Observable } from 'rxjs';
import { ITodo } from '../models/todo.model';
import * as _ from 'lodash';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private _todos = new BehaviorSubject<ITodo[]>([]);

  constructor(private http: HttpClient) {
    this.http
      .get<ITodo[]>(`${environment.api}/todos`)
      .subscribe((todos) => this._todos.next(todos));
  }

  public getTodos(): Observable<ITodo[]> {
    return this._todos.asObservable();
  }

  public addTodo(todo: ITodo): void {
    this.http
      .post<ITodo[]>(`${environment.api}/todos`, todo)
      .subscribe((todos) => this._todos.next(todos));
  }

  public removeTodo(deletedTodo: ITodo): void {
    this.http
      .delete<ITodo[]>(`${environment.api}/todos/${deletedTodo.id}`)
      .subscribe((todos) => this._todos.next(todos));
  }

  public updateTodo(updatedTodo: ITodo): void {
    this.http
      .put<ITodo[]>(`${environment.api}/todos`, updatedTodo)
      .subscribe((todos) => this._todos.next(todos));
  }

  public clearList(): void {
    this.http
      .delete<ITodo[]>(`${environment.api}/todos/all`)
      .subscribe((todos) => this._todos.next(todos));
  }

  public markAllCompleted(): void {
    this.http
      .put<ITodo[]>(`${environment.api}/todos/allCompleted`, null)
      .subscribe((todos) => this._todos.next(todos));
  }

  public markAllNotCompleted(): void {
    this.http
      .put<ITodo[]>(`${environment.api}/todos/allNotCompleted`, null)
      .subscribe((todos) => this._todos.next(todos));
  }

  public getNumberOfCompleted(): Observable<number> {
    return this._todos.pipe(
      map((todos) => todos.filter((t) => t.completed).length)
    );
  }

  public getNumberOfNotCompleted(): Observable<number> {
    return this._todos.pipe(
      map((todos) => todos.filter((t) => !t.completed).length)
    );
  }

  public getCompletionRatio(): Observable<number> {
    return combineLatest([
      this.getNumberOfTodo(),
      this.getNumberOfCompleted(),
    ]).pipe(
      map(([nbTodo, nbCompleted]: number[]) => (nbCompleted / nbTodo) * 100)
    );
  }

  public getNumberOfTodo(): Observable<number> {
    return this._todos.pipe(map((todos) => todos.length));
  }

  public hasNothingTodo(): Observable<boolean> {
    return this.getNumberOfTodo().pipe(map((nbTodo) => nbTodo === 0));
  }
  public hasEverythingTodo(): Observable<boolean> {
    return combineLatest([
      this.getNumberOfTodo(),
      this.getNumberOfCompleted(),
    ]).pipe(
      map(
        ([nbTodo, nbCompleted]: number[]) => nbTodo !== 0 && nbCompleted === 0
      )
    );
  }
  public hasDoneEverything(): Observable<boolean> {
    return combineLatest([
      this.getNumberOfTodo(),
      this.getNumberOfCompleted(),
    ]).pipe(
      map(
        ([nbTodo, nbCompleted]: number[]) =>
          nbTodo !== 0 && nbCompleted === nbTodo
      )
    );
  }
  public hasSomethingTodo(): Observable<boolean> {
    return combineLatest([
      this.getNumberOfTodo(),
      this.getNumberOfNotCompleted(),
    ]).pipe(
      map(
        ([nbTodo, nbNotCompleted]: number[]) =>
          nbTodo !== 0 && nbNotCompleted > 0 && nbNotCompleted !== nbTodo
      )
    );
  }
}
