import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/shared/services/auth.service';
import { IUser } from 'src/app/shared/models';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  signinForm!: FormGroup;

  constructor(
    private auth: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.signinForm = this.fb.group({
      usernameCtrl: ['', Validators.required],
      passwordCtrl: ['', Validators.required],
    });
  }

  onSignin(): void {
    const user: IUser = {
      username: this.signinForm.get('usernameCtrl')?.value,
      password: this.signinForm.get('passwordCtrl')?.value,
    };
    this.auth.signin(user);
  }
}
