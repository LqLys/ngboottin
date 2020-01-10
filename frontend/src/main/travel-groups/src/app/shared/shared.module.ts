import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {HomeComponent} from './components/home/home.component';
import {RouterModule} from '@angular/router';
import {MatIconModule} from '@angular/material';
import {BreadcrumbsComponent} from './components/breadcrumbs/breadcrumbs.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {SystemMessageComponent} from './components/system-message/system-message.component';
import {TableModule} from "primeng/table";
import {SliderModule} from "primeng/slider";
import {DropdownModule} from "primeng/dropdown";
import {MultiSelectModule} from "primeng/multiselect";
import {ButtonModule} from "primeng/button";
import {DialogModule} from "primeng/dialog";
import {PanelModule} from 'primeng/panel';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import {CardModule} from 'primeng/card';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmationService, MessageService} from 'primeng/api';
import {ToastModule} from 'primeng/toast';


@NgModule({
  declarations: [HeaderComponent, FooterComponent, HomeComponent, BreadcrumbsComponent, SystemMessageComponent],
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    ReactiveFormsModule,
    HttpClientModule,
    TableModule,
    SliderModule,
    DropdownModule,
    MultiSelectModule,
    FormsModule,
    ButtonModule,
    DialogModule,
    PanelModule,
    ScrollPanelModule,
    CardModule,
    ConfirmDialogModule,
    ToastModule
  ],
  providers: [ConfirmationService, MessageService],
  exports: [HeaderComponent, FooterComponent, BreadcrumbsComponent, ReactiveFormsModule, HttpClientModule, SystemMessageComponent, TableModule, SliderModule, DropdownModule,
    MultiSelectModule, FormsModule, ButtonModule, DialogModule , PanelModule, ScrollPanelModule, CardModule, ConfirmDialogModule, ToastModule]
})
export class SharedModule {
}
