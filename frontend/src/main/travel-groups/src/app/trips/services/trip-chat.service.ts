import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ChatMessage, SendMessage} from '../../models';
import {environment} from '../../../environments/environment';
import {Validators} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class TripChatService {

  private apiBase = environment.API_BASE_PATH;



  constructor(private http: HttpClient) { }


  getMessagesByGroupId(groupId: number){
    return this.http.get<ChatMessage[]>(this.apiBase + '/chat-message/' + groupId);
  }

  createChatMessage(message: SendMessage) {
    return this.http.post<any>(this.apiBase + '/chat-message', message);
  }
}
