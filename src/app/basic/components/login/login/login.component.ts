import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { NzAffixComponent } from 'ng-zorro-antd/affix';
import { NzNotificationComponent, NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  validateForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private notification: NzNotificationService,
    private router: Router,
  ){
    
  }
  ngOnInit(){
    this.validateForm = this.fb.group({
      userName : [null, [Validators.required]],
      password : [null, [Validators.required]],
    })
  }
  submitForm(){
    this.authService.login(this.validateForm.get(['userName'])!.value, this.validateForm.get(['password'])!.value)
    .subscribe(res =>{
    console.log(res)
    }, error =>{
    this.notification
    .error(
    'ERROR',
     `Bad crendentials`,
    { nzDuration: 5000 }
    )
  })
}
} 

