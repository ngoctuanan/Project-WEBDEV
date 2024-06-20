import { Component } from '@angular/core';
import { CompanyService } from 'src/company/services/company.service';

@Component({
  selector: 'app-company-dashboard',
  templateUrl: './company-dashboard.component.html',
  styleUrls: ['./company-dashboard.component.scss'],
})

export class CompanyDashboardComponent {
  constructor(private companyService: CompanyService, private notificationService: NotificationService) {}

  ngOnInit() {
    this.getAllAdBookings();
  }

  getAllAdBookings() {
    this.companyService.getAllAdBookings().subscribe(res => {
      console.log(res);
    })
  }
}
