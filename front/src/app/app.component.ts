import { Component } from '@angular/core';
import { PrefService } from './shared/services/pref.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'youdo';
  constructor(private pref: PrefService) {}
}
