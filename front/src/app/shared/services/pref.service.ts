import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { IPreferences } from '../models/preferences.model';

@Injectable({
  providedIn: 'root',
})
export class PrefService {
  private readonly PREF_KEY = 'preferences';

  private preferences$ = new BehaviorSubject<IPreferences>({
    isDark: false,
    locale: 'en',
  });

  constructor(private translate: TranslateService) {
    const pref = localStorage.getItem(this.PREF_KEY);
    if (pref) {
      this.preferences$.next(JSON.parse(pref));
    } else {
      this.updatePref(this.preferences$.value);
    }
    this.translate.use(this.preferences$.value.locale);
    console.log('locale is ' + this.preferences$.value.locale);
  }

  public isDarkTheme(): Observable<boolean> {
    return this.preferences$.pipe(map((p) => p.isDark));
  }
  public getLocale(): Observable<string> {
    return this.preferences$.pipe(map((p) => p.locale));
  }

  public toggleTheme(): void {
    const newPref = this.preferences$.value;
    newPref.isDark = !newPref.isDark;
    this.preferences$.next(newPref);
    localStorage.removeItem(this.PREF_KEY);
    localStorage.setItem(this.PREF_KEY, JSON.stringify(newPref));
    this.updatePref(newPref);
  }

  public setLocale(locale: string): void {
    const newPref = this.preferences$.value;
    newPref.locale = locale;
    this.translate.use(locale);
    this.updatePref(newPref);
  }

  private updatePref(pref: IPreferences): void {
    this.preferences$.next(pref);
    localStorage.removeItem(this.PREF_KEY);
    localStorage.setItem(this.PREF_KEY, JSON.stringify(pref));
  }
}
