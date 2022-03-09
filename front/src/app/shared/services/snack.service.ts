import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackComponent } from '../components/snack/snack.component';
import { SnackType } from '../models/snack-type.model';

@Injectable({
  providedIn: 'root',
})
export class SnackService {
  private readonly DURATION = 5000;
  constructor(private snackBar: MatSnackBar) {}

  public open(message: string, type: SnackType) {
    this.snackBar.openFromComponent(SnackComponent, {
      data: message,
      duration: this.DURATION,
      panelClass: `snack-${type}`,
    });
  }
}
