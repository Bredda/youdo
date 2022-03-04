import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpEvent,
} from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { SnackService } from '../services/snack.service';

@Injectable()
export class ApiErrorInterceptor implements HttpInterceptor {
  constructor(private snack: SnackService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((requestError: any) => {
        const skipIntercept = request.headers.has('skip');
        if (skipIntercept) {
          request = request.clone({
            headers: request.headers.delete('skip'),
          });
        } else {
          this.snack.openDanger(requestError.error.details);
        }
        return of(requestError);
      })
    );
  }
}
