export interface User {
  id: number;
  name: string;
  username: string;
  email: string;
  password?: string;
}

export interface UserData {
  name: string;
  username: string;
  password: string;
  email: string;
}

export interface Trip {
  id: number;
  name: string;
  destination: string;
  startDate: Date;
  endDate: Date;
}

export interface AddTripWithUsers {
  name: string;
  destination: string;
  startDate: Date;
  endDate: Date;
  userIds: number[];
}

export interface UserTrip {
  id: number;
  userId: number;
  tripId: number;
  username: string;
  email: string;
  participation: number;
}

export interface Expense {
  id?: number;
  trip_userId?: number;
  name?: string;
  amount?: number;
  title?: string;
}

export interface Credentials {
  username: string;
  password: string;
}

export interface JoingroupDto {
  groupId: number;
  username: string | string[];
}

export interface AddExpense {
  title: string;
  amount: number;
  username: string | string[];
  groupId: number;
}

export interface ChangePasswordDto {
  username: string;
  oldPassword: string;
  newPassword: string;
}

export interface EditGroup {
  id: number;
  name: string;
  destination: string;
  startDate: Date;
  endDate: Date;
}

export interface UserGroupBalance {
  name: string;
  destination: string;
  startDate: Date;
  endDate: Date;
  settledBalance: number;

}

export interface ChatMessage {
  author: string;
  time: Date;
  message: string;
}

export interface SendMessage {
  groupId: number;
  author: string | string[];
  message: string;
}
