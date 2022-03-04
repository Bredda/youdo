import { HttpClient, HttpResponse } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, map, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IUser } from '../models';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private user$ = new BehaviorSubject<IUser | undefined>(undefined);

  constructor(
    private http: HttpClient,
    private router: Router,
    private tokenService: TokenStorageService
  ) {
    const token = this.tokenService.getToken();
    if (token) {
      console.log('Token in storage', token);
      this.refreshUser().subscribe((user) => {
        this.router.navigate(['/']);
        this.user$.next(user);
      });
    }
  }

  public getUser(): Observable<IUser | undefined> {
    return this.user$.asObservable();
  }

  public refreshUser(): Observable<IUser> {
    return this.http.get<IUser>(`${environment.api}/auth/me`, {
      headers: { skip: 'true' },
    });
  }

  public signin(user: IUser): void {
    this.http
      .post<IUser>(`${environment.api}/auth/signin`, user)
      .subscribe((user) => {
        this.user$.next(user);
        if (user.token) this.tokenService.saveToken(user.token);
        this.router.navigate(['/']);
      });
  }

  public signup(user: IUser): void {
    this.http
      .post<IUser>(`${environment.api}/auth/signup`, user)
      .subscribe((user) => {
        this.user$.next(user);
        if (user.token) this.tokenService.saveToken(user.token);
        this.router.navigate(['/']);
      });
  }

  public signout(): void {
    this.tokenService.signOut();
    this.user$.next(undefined);
    this.router.navigate(['/auth/signin']);
  }

  public isAuthenticated(): Observable<boolean> {
    return this.user$.pipe(map((user) => user !== undefined));
  }
}
