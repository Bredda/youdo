import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpEvent,
} from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { SnackService } from '../services/snack.service';
import { TokenStorageService } from '../services/token-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private readonly AUTH_HEADER = 'Authorization';

  constructor(private token: TokenStorageService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getToken();
    if (token != null) {
      authReq = req.clone({
        headers: req.headers.set(this.AUTH_HEADER, 'Bearer ' + token),
      });
    }
    return next.handle(authReq);
  }
}
