import { Injectable } from '@angular/core';
import { IUser } from '../models';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
@Injectable({
  providedIn: 'root',
})
export class TokenStorageService {
  constructor() {}
  signOut(): void {
    localStorage.removeItem(TOKEN_KEY);
  }
  public saveToken(token: string): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }
  public getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }
  public saveUser(user: any): void {
    localStorage.removeItem(USER_KEY);
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }
  public getUser(): IUser | undefined {
    const user = localStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return undefined;
  }
}
