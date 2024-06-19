import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup-client',
  templateUrl: './signup-client.component.html',
  styleUrls: ['./signup-client.component.scss']
})
export class SignupClientComponent implements OnInit {
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
      lastname: [null, [Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required]]
    });
  }

  submitForm(): void {
    // Kiểm tra nếu form hợp lệ
    if (this.validateForm.valid) {
      // Gọi phương thức đăng ký từ AuthService
      this.authService.registerClient(this.validateForm.value).subscribe(
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
