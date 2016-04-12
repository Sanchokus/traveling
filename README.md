# Traveling blog wrapper

This is small wrapper for travel blog with world map. Add your articles to the app, fill some properties, and look at cozy-looking world map with your travel markers on it.

Thanks to my wife to idea how to both learn some new technologies and do something useful and workable!
And to [dillinger.io] for this cool MD editor.

Back-end is classic: [Spring MVC] + [Spring Security] + [ORM Hibernate] + [MySQL]:
  - Spring Security is needed because of admin interface (I didn't want to make a deep into Angular-Spring-auth-relationship, so it's just Basic HTTP Auth, just use HTTPS for safety.
  - Why MySQL? I place this project on OpenShift, that has free MySQL support.
  - Maybe

Front-end is [AngularJS] + [Material Angular] + [LeafletJS] + [leaflet-providers] + little help of [lodash].
  - Leaflet is much simplier than OpenLayers, and with help of leaflet-providers gives cozy look. 
  - Material Angular - really beautiful. Unfortunately it doesn't have build-in tables, but thanks to [Daniel Nagy] and his [md-data-table] I had all I wanted.
  - I tried JS-substitutions like TypeScript, Dart, but they were not very good in using various JS-world libraries, so I modernized JS with lodash.
  - Maybe there is some need of RequireJS, although project is not very big for now.

### TODOs for this project:
  - Front-end tests - Karma Protractor etc.
  - More fine-grained templates for main app page .
  - RequireJS - 'cause JS list will be too frightening in future.
  - Tags support for articles - just didn't have much time and patience for this block.
  - Spring code refactoring - I have some similar-looking code that can be simplified by custom spring annotations. 
  - Single-page angular app - now we have one app, but it initializes by two different pages. Maybe it's too much work for poor Angular? Need more beauty.

Here you can see my web application in production mode (content depends on my wife's free time): [CLICK ME]

### Screenshots:
[here]

[//]: #
  [dillinger.io]: <http://dillinger.io/>
  [CLICK ME]: <http://sawyl91traveling-aolisov.rhcloud.com/>
  [here]: <https://www.dropbox.com/sh/dpi68bbzeomlv44/AABV7h_FsE7DDChlfSuNfAbna?dl=0>
  [Spring MVC]: <http://projects.spring.io/spring-framework/>
  [Spring Security]: <http://projects.spring.io/spring-security/>
  [ORM Hibernate]: <http://hibernate.org/>
  [MySQL]: <https://www.mysql.com/>
  [AngularJS]: <https://angularjs.org/>
  [Material Angular]: <https://material.angularjs.org/latest/>
  [LeafletJS]: <http://leafletjs.com/>
  [leaflet-providers]: <https://github.com/leaflet-extras/leaflet-providers>
  [lodash]: <https://lodash.com/>
  [Daniel Nagy]: <https://github.com/daniel-nagy>
  [md-data-table]: <https://github.com/daniel-nagy/md-data-table>