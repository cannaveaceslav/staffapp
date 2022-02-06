import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {Router} from "@angular/router";
import {map, Observable, shareReplay} from "rxjs";

@Component({
  selector: 'app-add-new-content-admin',
  templateUrl: './add-new-content-admin.component.html',
  styleUrls: ['./add-new-content-admin.component.css']
})
export class AddNewContentAdminComponent implements OnInit {

   constructor(private router: Router,private breakpointObserver: BreakpointObserver){}

  ngOnInit(): void {

  }
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
}
