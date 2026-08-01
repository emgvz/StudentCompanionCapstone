import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter, withRouterConfig } from '@angular/router';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideHttpClient, withFetch} from '@angular/common/http';
import { withInMemoryScrolling } from '@angular/router';


export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(
      routes,
      withRouterConfig({ onSameUrlNavigation: 'reload'}),
      withInMemoryScrolling({
        scrollPositionRestoration: 'top',
        anchorScrolling: 'enabled' })
    ),
    provideClientHydration(withEventReplay()),
    provideHttpClient(
      withFetch(),
    ),
	provideCharts(withDefaultRegisterables())
  ]
};
