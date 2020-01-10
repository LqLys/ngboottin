import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../../../../auth/services/auth.service';
import {FormBuilder, Validators} from '@angular/forms';
import {TripsFacade} from '../../../facades/trips.facade';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-trip-chat',
  templateUrl: './trip-chat.component.html',
  styleUrls: ['./trip-chat.component.css']
})
export class TripChatComponent implements OnInit, OnDestroy {

  messages$ = this.tripsFacade.messages$;
  sub;

  messageForm = this.fb.group({
    message: ['', [Validators.required]]
  });


  constructor(private tripsFacade: TripsFacade, private authService: AuthService, private fb: FormBuilder,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    const groupId = this.route.snapshot.params.id;
    this.tripsFacade.loadChatMessagesByGroupId(groupId).subscribe();

    this.sub = setInterval(() => {
      this.tripsFacade.loadChatMessagesByGroupId(groupId).subscribe();
    }, 3000);
  }


  onSubmitMessage() {
    console.log(this.messageForm.value.message);
    const groupId = this.route.snapshot.params.id;
    const author = this.authService.getAuthenticatedUsername();
    const message = {
      groupId: groupId,
      author: author,
      message: this.messageForm.value.message
    };
    this.messageForm.reset();
    this.tripsFacade.sendChatMessage(message).subscribe(() =>
      this.tripsFacade.loadChatMessagesByGroupId(groupId).subscribe());
  }

  ngOnDestroy(): void {
    clearTimeout(this.sub);
  }
}
