## Service de localisation en Android ##
| **Nom** | **lien** |
|:--------|:---------|
| Location and Maps | [lien](http://developer.android.com/guide/topics/location/index.html) |
| User Location | [lien](http://developer.android.com/guide/topics/location/obtaining-user-location.html)|
| API     | [lien](http://developer.android.com/reference/android/location/LocationManager.html) |

Android a proposé deux moyens pour obtenir la localisation d'utilisateur:
  * _GPS_
    * avantage: le plus précis
    * inconvénient: consommer plus de batterie; disponible seulement à l'extérieur; réponse lente
  * _Network Location Provider_
    * avantage: économiser la batterie; réponse rapide
    * inconvénient: moins précis que GPS
Q: on utilise les deux?

Q: pour diminuer la consomption et rendre application plus performant, on doit définir un model qui spécifie comment notre application obtenir la localisation d'utilisateur? et faire des estimations?


## Projets Open Source intéressants ##
Quelque projets open source existants...
| **Nom** | **lien** |
|:--------|:---------|
| mytracks | [lien](http://code.google.com/p/mytracks) |
| posit-mobile | [lien](http://code.google.com/p/posit-mobile) |


## Service en Android ##
Le Service est un composant d'application en Android([API](http://developer.android.com/reference/android/app/Service.html)) qui permet d'effectuer une opération de longue durée à l'arrière plan sans interaction avec utilisateur.

example: [APIDemo/App/Service](http://developer.android.com/resources/samples/ApiDemos/src/com/example/android/apis/app/index.html)

  * RQ:
    * Il est possible de lancer un service en démarrage de système(Android). Il suffit d'implémenter un [BroadcastReceiver](http://developer.android.com/reference/android/content/BroadcastReceiver.html) qui attend le BOOT\_COMPLETED. [ref1](http://groups.google.com/group/android-developers/browse_thread/thread/112887fbb59f3ee2?pli=1) [ref2](http://androidgps.blogspot.com/2008/09/starting-android-service-at-boot-time.html)
    * Un service ne peut pas exister indépendamment. Il est un composant de l'application. Il exécute dans le même processus que l'application. L'idée est d'implémenter une application  avec des fonctions(start service, stop service, service setting... ) qui permet de gérer sons service déclenché.