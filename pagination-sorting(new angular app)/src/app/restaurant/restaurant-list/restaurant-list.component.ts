import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { HttpClient } from '@angular/common/http';
// tslint:disable-next-line: max-line-length
import { Subscription } from 'rxjs';
import { RestaurantService } from '../restaurant.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Restaurant } from '../restaurant';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';


@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['restaurant-list.component.scss']

})
export class RestaurantListComponent implements OnInit, OnDestroy {
  constructor(
    private restaurantService: RestaurantService,
    private router: Router, private formBuilder: FormBuilder,
    private http: HttpClient) {

  }
  get form(): any {
    return this.restaurantForm.controls;
  }
  get menuform(): any {
    return this.menuForm.controls;
  }


  restaurants = new Array<Restaurant>();
  id: number;
  restaurantObject = new Array<Restaurant>();
  closeResult: string;
  restaurant: Restaurant = new Restaurant();
  data: any;
  file: any;
  restaurantId: any;
  restaurantData: any;
  component: string;
  isSubmitted = false;
  dialog: boolean;
  currentTutorial = null;
  currentIndex = -1;
  restaurantName = '';
  meridian = true;
  rest: any;
  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  // tslint:disable-next-line: variable-name
  open_time = '';
  // tslint:disable-next-line: variable-name
  close_time = '';
  time = { hour: 13, minute: 30 };
  dining: boolean;
  homeDelivery: boolean;
  takeAway: boolean;
  restaurantForm: FormGroup;
  menuForm: FormGroup;
  menu = '';
  userToken: any;
  isSignedIn: boolean;
  subscription = new Subscription();

  // tslint:disable-next-line: member-ordering
  listData: MatTableDataSource<Restaurant>;
  // tslint:disable-next-line: member-ordering
  displayedColumns: string[] = ['restaurantName', 'address', 'phoneNo', 'open_time', 'close_time', 'updatedTime', 'status', 'actions'];
  // tslint:disable-next-line: member-ordering
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  // tslint:disable-next-line: member-ordering
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  searchKey: string;




  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

}
