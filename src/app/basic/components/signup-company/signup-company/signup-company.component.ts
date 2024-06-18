import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-signup-company',
  templateUrl: './signup-company.component.html',
  styleUrls: ['./signup-company.component.scss']
})
export class SignupCompanyComponent {
  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private notification: NzNotificationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Khởi tạo FormGroup và các FormControl với validators
    this.validateForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      name: [null, [Validators.required]],
      address: [null, [Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required]]
    });
  }

  submitForm(): void {
    // Kiểm tra nếu form hợp lệ
    if (this.validateForm.valid) {
      // Gọi phương thức đăng ký từ AuthService
      this.authService.registerCompany(this.validateForm.value).subscribe(
        res => {
          // Nếu đăng ký thành công, hiển thị thông báo thành công
          this.notification.success('THÀNH CÔNG', 'Đăng ký thành công', { nzDuration: 5000 });
          // Chuyển hướng đến trang đăng nhập
          this.router.navigateByUrl('/login');
        },
        error => {
          // Nếu có lỗi trong quá trình đăng ký, hiển thị thông báo lỗi
          console.error('Lỗi khi đăng ký:', error); // Log lỗi để debug
          this.notification.error('LỖI', 'Đăng ký thất bại', { nzDuration: 5000 });
        }
      );
    } else {
      // Nếu form không hợp lệ, hiển thị thông báo lỗi
      this.notification.error('LỖI', 'Form không hợp lệ!', { nzDuration: 5000 });
    }
  }
}
