import { Component, OnInit } from '@angular/core';
import { IPreferences } from 'src/app/shared/models/preferences.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { PrefService } from 'src/app/shared/services/pref.service';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss'],
})
export class UserMenuComponent implements OnInit {
  username = '';
  isDark = false;
  locale = 'en';

  constructor(private auth: AuthService, private pref: PrefService) {
    this.pref.isDarkTheme().subscribe((t) => (this.isDark = t));
    this.pref.getLocale().subscribe((l) => (this.locale = l));
  }

  ngOnInit(): void {
    this.auth.getUser().subscribe((u) => {
      if (u) this.username = u?.username;
    });
  }
  onSignout() {
    this.auth.signout();
  }

  toggleTheme(): void {
    this.pref.toggleTheme();
  }

  setLocale(locale: string): void {
    this.pref.setLocale(locale);
  }
}
