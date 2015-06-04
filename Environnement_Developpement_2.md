_Ce document constitue une aide dans la mise en place de l'environnement de développement qui sera utilisé. Il précise notamment l'ordre des installations à effectuer ainsi que la configuration de ces logiciels. Pour obtenir des tutoriels sur l'utilisation de ces outils, on pourra se référer au document suivant : http://code.google.com/p/inpranet/wiki/Tutoriels_Developpement_

# Sommaire #



# Introduction #

La procédure de mise en place de l’environnent décrit ci-dessous a été testée sur un environnement vide, à l'aide d'une machine virtuelle (installation fraîche de Windows XP, sans aucune variable d'environnement prédéfinie et pas d'autres applications préinstallées).

Si l'installation d'un plugin Eclipse prend beaucoup de temps (blocage à l'étape "Calculating requirements and depedencies"), faire les actions suivantes :
  * Référence : http://code.google.com/eclipse/docs/faq.html#longinstall
  * Manage Sites, décocher tout sauf ceux qui vous intéressent.

**Prérequis**<br />
Voici les fichiers qui vont être utilisés pour mettre en place l'environnement de développement.

http://inpranet.googlecode.com/svn/wiki/screenshots/00.PNG

# 01. jre-6u24-windows-i586.exe #

**Tutoriels**
  * http://www.oracle.com/technetwork/java/javase/index-137561.html
  * http://www.oracle.com/technetwork/java/javase/jrereadme-182762.html

**Procédure**
  * Exécuter l'installateur.

**Répertoire par défaut**
  * C:\Program Files\Java\jre6\

**Contenu**<br />
Aucun autre contenu que les fichiers contenus dans le répertoire d'installation avec une exception pour les programmes java, javaw, javaws dans C:\WINDOWS\system32 (contenu dans le PATH).

**Vérification**
  * Entrer "java -version" dans un interpréteur de commandes Windows.

# 02. jdk-6u24-windows-i586.exe #

**Tutoriels**
  * http://www.oracle.com/technetwork/java/javase/index-137561.html
  * http://www.oracle.com/technetwork/java/javase/readme-142177.html

**Procédure**
  * Exécuter l'installateur.

**Répertoire par défaut**
  * C:\Program Files\Java\jdk1.6.0\_24

**Contenu**<br />
L'installateur propose Java DB, qui sera installé dans un autre répertoire que le JDK. Le JDK fournit un JRE privé.

# 03. java\_ee\_sdk-6u1-windows-ml.exe #

**Tutoriels**
  * http://www.youtube.com/watch?v=m_33ZHYCjf8&feature=related

**Procédure**
  * Exécuter l'installateur.

**Répertoire par défaut**
  * C:\glassfishv3

**Notes**
  * Débloquer le serveur Glassfish vis-à-vis du pare-feu Windows.

http://inpranet.googlecode.com/svn/wiki/screenshots/07.PNG

# 04. apache-tomcat-7.0.8.exe #

**Procédure**
  * Exécuter l'installateur **en précisant que le JDK est le JRE qui sera utilisé par Tomcat**.

**Répertoire par défaut**
  * C:\Program Files\Apache Software Foundation\Tomcat 7.0

**Configuration**
  * Pas besoin d'installer le service au démarrage, surtout si vous êtes sur Vista ou 7 (problème à cause de l'UAC). Si ça a été fait, désactivez le démarrage automatique du service et de l'application ApacheTomcatMonitor.

**Vérification**
  * Lancer l'application "Monitor Tomcat" pour pouvoir démarrer le serveur Tomcat
  * Essayer ensuite d'afficher la page "http://localhost:8080" depuis votre navigateur Web
  * Vous pouvez maintenant arrêter les applications que vous venez de lancer.

# 05. eclipse-jee-helios-SR2-win32.zip #

**Tutoriels**
  * http://wiki.eclipse.org/FAQ_Where_do_I_get_and_install_Eclipse%3F
  * http://publib.boulder.ibm.com/infocenter/wf/v2r7m0/topic/org.eclipse.platform.doc.user/tasks/running_eclipse.htm
  * http://stackoverflow.com/questions/165230/is-the-offical-sun-java-ee-tutorial-the-best-way-to-learn-how-to-make-java-web-ap
  * http://help.eclipse.org/galileo/topic/org.eclipse.jdt.doc.user/gettingStarted/qs-2.htm
  * http://www.youtube.com/watch?v=aBjlR9HoR50
  * http://www.youtube.com/watch?v=9Kf5m7bMu74&feature=related
  * http://wiki.eclipse.org/WTP_Tomcat_FAQ
  * http://www.eclipse.org/webtools/initial-contribution/IBM/evalGuides/ServerToolsEval.html
  * http://www.youtube.com/user/pgscispike

**Procédure**
  * Extraire le contenu de l'archive.

**Répertoire par défaut**
  * C:\eclipse

**Configuration**
  * On peut installer le plugin pour le serveur GlassFish (mais ce n'est pas nécessaire pour nous) ;
  * Configurer Eclipse pour avoir un serveur Tomcat dans le Workspace, en utilsant le JDK comme JRE par défaut (voir les images ci-dessous).

**Ajout d'un JRE, ici le JDK sera considéré comme tel**<br />
http://inpranet.googlecode.com/svn/wiki/screenshots/15.PNG

**Configuration du serveur Tomcat pour utiliser le JDK**<br />
http://inpranet.googlecode.com/svn/wiki/screenshots/16.PNG

# 06. apache-ant-1.8.2-bin.zip #

**Tutoriels**
  * http://ant.apache.org/manual/install.html
  * http://help.eclipse.org/helios/index.jsp?topic=/org.eclipse.platform.doc.user/gettingStarted/qs-82_create.htm

**Procédure**
  * Extraire le contenu de l'archive.

**Répertoire par défaut**
  * C:\Program Files\Apache Software Foundation\apache-ant-1.8.2

**Configuration**
  * Ajouter ANT\_HOME comme variable d'environnement ainsi que %ANT\_HOME%/bin dans votre Path.
  * Il n'y a pas de plugin Eclipse pour Ant puisque celui-ci est déjà intégré dans l'IDE.

**Vérification**<br />
Entrer "ant -version" pour voir si Ant a bien été installé.

# 07. apache-maven-3.0.2-bin.zip #

**Tutoriels**
  * http://maven.apache.org/download.html#Installation
  * http://maven.40175.n5.nabble.com/naming-now-that-Maven-3-is-out-td3210546.html
  * http://maven.apache.org/eclipse-plugin.html
  * http://m2eclipse.sonatype.org/
  * http://m2eclipse.sonatype.org/installing-m2eclipse.html
  * http://www.jroller.com/JOKe/entry/configure_eclipse_3_3_wtp
  * http://www.youtube.com/watch?v=rdhqAzHZkwc&feature=related
  * http://www.youtube.com/watch?v=LyuMMqlpOq0&feature=related

**Procédure**
  * Extraire le contenu de l'archive.

**Répertoire par défaut**
  * C:\Program Files\Apache Software Foundation\apache-maven-3.0.2

**Configuration**
  * Ajouter la varaible d'environnement M2\_HOME ("C:\Program Files\Apache Software Foundation\apache-maven-3.0.2") et M2 ("%M2\_HOME%\bin") ;
  * Ajouter dans le Path la valeur "%M2%" ;
  * Vérifier que JAVA\_HOME existe ("C:\Program Files\Java\jdk1.6.0\_24") et que la valeur du Path contienne bien "%JAVA\_HOME%\bin" ;
  * Installer le plugin m2eclipse (Core et Extras) : http://m2eclipse.sonatype.org/sites/m2e et http://m2eclipse.sonatype.org/sites/m2e-extras

**Vérification**
  * Entrer "mvn --version" dans un interpréteur de commandes Windows.

**Notes**
  * Apparament, garder le M2 au lieu de M3, même si on est en version 3...

# 08. apache-cxf-2.3.2.zip #

**Tutoriels**
  * http://www.youtube.com/watch?v=UpZgxutMgYg
  * http://cxf.apache.org/apache-cxf-232-release-notes.html
  * http://cxf.apache.org/setting-up-eclipse.html
  * http://www.ibm.com/developerworks/library/ws-pojo-springcxf/
  * http://www.developpez.net/forums/f301/java/developpement-web-java/web-services/

**Procédure**
  * Extraire le contenu de l'archive.

**Répertoire par défaut**
  * C:\Program Files\Apache Software Foundation\apache-cxf-2.3.2

**Configuration**
  * Vérifier que les variables d'environnement pour le JDK soient bien présentes.
  * Suivre le tutoriel pour faire fonctionner CXF avec Eclipse (partie 4 du tutoriel vidéo sur YouTube).
  * Configurer le serveur Tomcat pour qu'il utilise CXF comme Runtime.

# 09. spring-framework-3.0.5.RELEASE.zip #

**Tutoriels**
  * http://viralpatel.net/blogs/2010/06/spring-3-mvc-create-hello-world-application-spring-3-mvc.html
  * http://viralpatel.net/blogs/category/spring
  * http://www.youtube.com/watch?v=OwN6UdDzZNA&feature=related
  * http://www.youtube.com/watch?v=ZwMwfGNrRtE
  * http://www.springsource.com/developer/sts
  * http://dist.springsource.com/release/STS/doc/STS-installation_instructions-2.3.3.M1.pdf
  * http://blog.springsource.com/2010/07/01/sts-on-eclipse-3-6/
  * http://forum.springsource.org/archive/index.php/t-100925.html
  * http://download.springsource.com/release/STS/doc/STS-installation_instructions.pdf

**Procédure**
  * Extraire le contenu de l'archive.

**Répertoire par défaut**
  * C:\spring-framework

**Configuration**
  * On peut avoir un peu plus d'intégration avec Eclipse en utilisant le plugin STS, mais ce n'est pas nécessaire (et en plus, pas très clean lors de l'installation), il n'y a que les JARs qui nous intéressent.
  * Liens : http://dist.springsource.com/release/TOOLS/update/e3.6 et http://dist.springsource.com/release/TOOLS/composite/e3.6 (il faut avoir ces deux liens en même temps dans la liste des sites disponibles avec le lien Helios, se référer aux instructions d'installation).

**Installation du SpringTools Suite (STS)**<br />
Ne cocher que les cases qui sont affichées ci-dessous sinon l'installation ne se fera pas...<br />

http://inpranet.googlecode.com/svn/wiki/screenshots/19.PNG

# 10. hibernate-distribution-3.6.1.Final-dist.zip #

**Tutoriels**
  * http://download.jboss.org/jbosstools/updates/
  * http://programming.manessinger.com/tutorials/an-eclipse-glassfish-java-ee-6-tutorial/
  * http://www.visualbuilder.com/java/hibernate/tutorial/introduction-to-hibernate/
  * http://stackoverflow.com/questions/351640/how-to-install-hibernate-tools-in-eclipse

**Procédure**
  * Extraire le contenu de l'archive.

**Répertoire par défaut**
  * C:\hibernate

**Configuration**
  * On peut également installer le plugin Hibernate Tools pour Eclipse (voir la capture ci-dessous).
  * Lien : http://download.jboss.org/jbosstools/updates/stable/helios

http://inpranet.googlecode.com/svn/wiki/screenshots/20.PNG

# 11. postgresql-9.0.3-1-windows.exe #

**Tutoriels**
  * http://www.postgresql.org/docs/7.4/static/charset.html
  * http://en.wikipedia.org/wiki/PostGIS

**Procédure**
  * Exécuter l'installateur.

**Répertoire par défaut**
  * C:\Program Files\PostgreSQL\9.0
  * C:\Program Files\PostgreSQL\9.0\data (pour les données)

**Configuration**
  * Numéro de port : 5432 (par défaut)
  * Localisation : French, France

**Notes**
  * À la fin de l'assistant d'installation, on peut aussi installer PostGIS 1.5.2-3 à l'aide de Stack Builder.

# Configuration finale #

**Variables d'environnement Windows**<br />

```
C:\Documents and Settings\XPMUser>echo %ANT_HOME%
C:\Program Files\Apache Software Foundation\apache-ant-1.8.2

C:\Documents and Settings\XPMUser>echo %JAVA_HOME%
C:\Program Files\Java\jdk1.6.0_24

C:\Documents and Settings\XPMUser>echo %M2_HOME%
C:\Program Files\Apache Software Foundation\apache-maven-3.0.2

C:\Documents and Settings\XPMUser>PATH
PATH=C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\Program Files\Java\jdk1.6.0_24\bin;C:\Program Files\Apache Software Foundation\apache-maven-3.0.2\bin;C:\Program Files\Apache Software Foundation\apache-ant-1.8.2/bin
```

http://inpranet.googlecode.com/svn/wiki/screenshots/23.PNG

**Programmes installés**<br />

```
C:\>dir /B/A:D
Documents and Settings
eclipse
glassfishv3
hibernate
I386
opt
Program Files
RECYCLER
spring-framework
System Volume Information
WINDOWS
Workspace

C:\>dir "C:\Program Files" /B/A:D
Apache Software Foundation
ComPlus Applications
Fichiers communs
Internet Explorer
Java
Messenger
microsoft frontpage
Movie Maker
MSN
MSN Gaming Zone
NetMeeting
Online Services
Outlook Express
PostgreSQL
Services en ligne
Sun
Uninstall Information
Virtual PC Integration Components
Windows Media Player
Windows NT
WindowsUpdate
xerox

C:\>dir "C:\Program Files\Apache Software Foundation" /B/A:D
apache-ant-1.8.2
apache-cxf-2.3.2
apache-maven-3.0.2
Tomcat 7.0

C:\>dir "C:\Program Files\Java" /B/A:D
jdk1.6.0_24
jre6
```

**Plugins Eclipse**<br />

http://inpranet.googlecode.com/svn/wiki/screenshots/21.PNG