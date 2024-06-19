import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserStoargeService } from 'src/app/basic/components/services/stogare/user-stoarge.service';

const BASIC_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  constructor(private http: HttpClient) {}

  getAllAds(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/client/ads', {
      headers: this.createAuthorizationHeader()
    });
  }
   searchAdByName(name: any): Observable<any> {
    return this.http.get(`${BASIC_URL}/api/client/search/${name}`, {
        headers: this.createAuthorizationHeader()
    });
  }
  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set('Authorization', 'Bearer ' + UserStoargeService.getToken());
  }
}