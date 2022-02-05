import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {Router} from "@angular/router";
import {map, Observable, shareReplay} from "rxjs";

@Component({
  selector: 'app-item-types-admin',
  templateUrl: './item-types-admin.component.html',
  styleUrls: ['./item-types-admin.component.css']
})
export class ItemTypesAdminComponent implements OnInit {

  constructor(private router: Router,private breakpointObserver: BreakpointObserver){}

  ngOnInit(): void {

  }
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

}
