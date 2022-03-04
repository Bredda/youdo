import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackComponent } from '../components/snack/snack.component';

@Injectable({
  providedIn: 'root',
})
export class SnackService {
  private readonly DURATION = 5000;
  constructor(private snackBar: MatSnackBar) {}

  public openDanger(message: string) {
    this.snackBar.openFromComponent(SnackComponent, {
      data: message,
      duration: this.DURATION,
    });
  }

  public openSuccess(message: string) {
    this.snackBar.openFromComponent(SnackComponent, {
      data: message,
      duration: this.DURATION,
    });
  }

  public openInfo(message: string) {
    this.snackBar.openFromComponent(SnackComponent, {
      data: message,
      duration: this.DURATION,
    });
  }
}
