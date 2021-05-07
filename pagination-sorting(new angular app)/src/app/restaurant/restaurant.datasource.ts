


import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {Observable, BehaviorSubject, of} from 'rxjs';
import {catchError, finalize} from 'rxjs/operators';
import { Restaurant } from './restaurant';
import { RestaurantService } from './restaurant.service';



export class RestaurantDataSource implements DataSource<Restaurant> {

    private lessonsSubject = new BehaviorSubject<Restaurant[]>([]);

    private loadingSubject = new BehaviorSubject<boolean>(false);

    public loading$ = this.loadingSubject.asObservable();

    constructor(private restaurantService: RestaurantService) {

    }

    // tslint:disable-next-line: typedef
    loadRestaurant(sortField: string,
                   sortDirection: string,
                   pageIndex: number,
                   pageSize: number) {

        this.loadingSubject.next(true);

        this.restaurantService.getRestaurantList(sortField, sortDirection,
            pageIndex, pageSize).pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe(lessons => this.lessonsSubject.next(lessons));

    }

    connect(collectionViewer: CollectionViewer): Observable<Restaurant[]> {
        console.log('Connecting data source');
        return this.lessonsSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.lessonsSubject.complete();
        this.loadingSubject.complete();
    }

}

