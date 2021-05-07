import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Restaurant } from './restaurant';
import {map} from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private http: HttpClient) { }

  getRestaurantList(
    sortField: string, sortDirection: string,
    pageNo: number, pageSize: number
  ): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(`${environment.baseUrl}`, {
      params: new HttpParams()
      .set('sortField', sortField)
      .set('sortDirection', sortDirection)
      .set('pageNo', pageNo.toString())
      .set('pageSize', pageSize.toString())
    }).pipe(
      // tslint:disable-next-line: no-string-literal
      map(res => res['payload'])
    );

  }

  getActiveRestaurantList(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(`${environment.baseUrl + '/active'}`);

  }
}
