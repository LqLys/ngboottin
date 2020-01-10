import { TestBed } from '@angular/core/testing';

import { TripChatService } from './trip-chat.service';

describe('TripChatService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TripChatService = TestBed.get(TripChatService);
    expect(service).toBeTruthy();
  });
});
