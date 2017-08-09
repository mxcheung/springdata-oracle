import { Component } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  private my_port = '8080'
  private apiUrl = document.location.protocol +'//'+ document.location.hostname + ":" + this.my_port + '/v6/funddata/userDTO/';
  
  data: any = {};
    
  constructor(private http: Http) {
	 console.log('Hello fellow user');  
	 console.log(document.location.protocol);  
	 console.log(document.location.hostname);  
	 this.getContacts();
	 this.getData();
  }
  
  getData() {
	  return this.http.get(this.apiUrl)
	    .map((res: Response) => res.json())
  }
  
  getContacts() {
	  this.getData().subscribe(data => {
		  console.log(data);
		  this.data = data
	  })
  }
}
