# Mise en place de l'environnement de développement #

## Eclipse ##
Télécharger et installer l'Eclipse JEE
[lien](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr1)


## Serveur Tomcat ##

On utilise le serveur tomcat pour les web services entre les modules du côté serveur.

Télécharger Tomcat version 7
[lien](http://apache.cict.fr/tomcat/tomcat-7/v7.0.5-beta/bin/apache-tomcat-7.0.5-windows-x86.zip)

Suivre le tutoriel pour intégrer dans l'Eclipse
[tuto](http://www.objis.com/formation-java/tutoriel-eclipse-wtp-jee-integration-tomcat.html)

Remarque: le tutoriel est pour Tomcat 6.0, mais c'est exactement le même que pour la version 7




## Android ##
Tout les tutos sont sur le site [developer\_Android](http://developer.android.com/index.html). Je vous explique ce que j'ai fait:
  * Télécharger et lancer Android SDK installer: [installer\_r08-windows.exe](http://developer.android.com/sdk/index.html)
  * AVD Manager va proposer les différentes versions de SDK. attention, on n'a pas besoin de tout prendre(sinon ça prendra du temps pour télécharger et bcp d'espace dans disk :p)
  * Cliquer cancel pour les propositions, on les installera manuellement
  * Dans AVD Manager, aller dans rubrique 'Available packages', choisir:
    * plateforme tools
    * documentation
    * cible 1.6 et 2.1 (je vais expliquer après. Si vous voulez développer des app sur la version la plus récente, vous pouvez choisir 2.3 aussi)
    * sample(n'import quel parmi les trois, j'ai pris API 9. Il contient les exemples de 2.3 :p )
  * Une fois les packages sont installés(peut être il y a des mises à jour de composant durant l'installation, cliquer ok), on peut ajouter un device virtuel.
  * Aller dans le rubrique 'Virtual Device'. Cliquer 'create'. On donne le nom par exemple 'emulator1', choisi la cible 1.6 par exemple. Cliquer OK(les autres on s'en fou :p)
  * On va ajouter le ADT plugin dans eclipse. Installer [ADT\_plugin](http://developer.android.com/sdk/eclipse-adt.html#installing). Suivre le tuto dans le même page pour configurer le plugin, c'est très facile!
  * Bon, on a fini l'installation du SDK et le plugin d'eclipse! A vous de créer votre première application [helloWorld](http://developer.android.com/resources/tutorials/hello-world.html). Attention! la cible choisi de votre projet Android doit correspondre à ce que vous avez choisi pour l’émulateur.

RQ: Pourquoi choisir la cible 1.6 et 2.1? Regardez [target\_device](http://developer.android.com/resources/dashboard/platform-versions.html)

le camembert a montré que 2.2 + 2.1 + 1.6 ont occupé plus que 90% de devices. Vue qu'une application de 1.6 marche aussi en versions plus récentes, on essaye de développer sur une plateforme plus grande possible. Si on choisi 2.2 comme la cible, on pourra peut-être utiliser des nouvelles APIs qui n'existent pas en 1.6 mais on va perdre 7.9% de devices:p
On discutera après sur la cible(1.6 ou 2.1), par contre votre hello world derva marcher sur les deux ^^