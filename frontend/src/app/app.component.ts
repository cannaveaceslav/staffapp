import {Component, OnInit} from '@angular/core';
import {NavigationStart, Router} from "@angular/router";
import * as events from "events";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  showMenu: boolean = false;

  constructor(private router: Router) {

    router.events.forEach((event) => {
      if (event instanceof NavigationStart) {
        this.showMenu = event.url != "/admin"
          && event.url != "/admin/dashboard"
          && event.url != "/admin/departments"
          && event.url != "/admin/add-new-content"
          && event.url != "/admin/employees"
          && event.url != "/admin/items"
          && event.url != "/admin/item-types"
          && event.url != "/admin/users"
          && event.url != "/admin/locations"
      }
    });
  }

  ngOnInit(): void {
  }

}
