_Ce document présente les différents outils et les logiciels qui seront utilisés dans ce projet. L'installation et la configuration de ces outils sont traitées dans le document suivant : http://code.google.com/p/inpranet/wiki/Environnement_Developpement_2_

# Sommaire #



# Java Runtime Environment (JRE) #

**Documentation**
  * http://en.wikipedia.org/wiki/Java_Virtual_Machine
  * http://www.java.com/en/download/faq/techinfo.xml
  * http://en.wikipedia.org/wiki/Java_%28software_platform%29
  * http://www.michael-thomas.com/tech/java/javafirststeps/jemjdkinstall.htm

**Téléchargement**
  * Lien : http://www.oracle.com/technetwork/java/javase/downloads/index.html
  * Version : 6 Update 24
  * Fichier : jre-6u24-windows-i586.exe
  * Remarque : Pour les technologies Java, prendre la version Windows multi-language.

**Description**<br />
Un JRE est un environnement nécessaire à l'exécution de programmes Java. Celui-ci contient une JVM, des librairies fondamentales ainsi que des petits composants et appelets supplémentaires.


---

**> Java Development Kit (JDK)**

**Documentation**
  * http://en.wikipedia.org/wiki/Java_Development_Kit
  * http://stackoverflow.com/questions/2860513/java-ee-and-jdk

**Description**<br />
Un SDK (ou une partie du SDK) pour développer en Java. Contient les outils suivants : java, javac, jdb, etc. À préciser que le JDK fournit également un JRE (qui contient une JVM). On pourra noter que Java SE est un JDK et qu'il n'est pas le seul (OpenJDK par exemple).

**> Public JRE vs Private JRE**

**Documentation** 
  * http://asjs.wordpress.com/2006/12/13/9/
  * http://www.velocityreviews.com/forums/t145658-why-two-jres.html
  * http://www.oracle.com/technetwork/java/javase/install-windows-189425.html

**Description**<br />
Un JRE privé est un JRE qui est automatiquement installé avec un JDK. Celui-ci est utilisé par le JDK. Un JRE public est distribué et est utilisé par l'utilisateur.

---


# Java Standard Edition SDK (Java SE SDK ou JDK) #

**Documentation**
  * http://www.oracle.com/technetwork/java/javase/downloads/index.html
  * http://www.oracle.com/technetwork/java/javase/version-6-141920.html

**Téléchargement**
  * Lien : http://www.oracle.com/technetwork/java/javase/downloads/index.html
  * Version : 6 Update 24
  * Fichier : jdk-6u24-windows-i586.exe
  * Remarque : Pour les technologies Java, prendre la version Windows multi-language.

**Description**<br />
Un JDK, avec un ensemble de composants utiles pour les développeurs. Se limite aux usages courants (pas de gestion de serveurs).

# Java Enterprise Edition SDK (Java EE SDK) #

**Documentation**
  * http://en.wikipedia.org/wiki/Java_EE
  * http://download.oracle.com/javaee/6/firstcup/doc/firstcup.pdf
  * http://www.oracle.com/technetwork/articles/javaee/javaee6overview-141808.html
  * http://java.sun.com/javaee/sdk/javaee6sdk_relnotes.jsp
  * http://download.oracle.com/javaee/6/tutorial/doc/index.html
  * http://www.oracle.com/technetwork/java/javaee/downloads/javaee6sdk-contents-jsp-136722.html
  * http://forums.oracle.com/forums/thread.jspa?threadID=2124725
  * http://en.wikipedia.org/wiki/GlassFish
  * http://java.sun.com/javaee/downloads/previous/index.jsp

**Téléchargement**
  * Lien : http://www.oracle.com/technetwork/java/javaee/downloads/index.html
  * Version : 6 Update 1
  * Fichier : java\_ee\_sdk-6u1-windows-ml.exe
  * Remarque : Pour les technologies Java, prendre la version Windows multi-language.

**Description**<br />
Cette plateforme fournit des composants supplémentaires à ceux du JDK pour la programmation sur serveurs (exemples d'API : javax.ejb.**, javax.persistance.**, etc.). Il faut donc installer un JDK puis installer le Java EE pour assurer un fonctionnement normal. Enfin, Java EE SDK est également livré avec GlassFish, un serveur d'application, au même titre qu'Apache Geronimo ou que JBoss AS.
Java EE contient égalment


---

**> Précisions sur le vocabulaire Java**

  * Java EE SDK = Java SE SDK (= JDK) + JRE (= Platform) + Compléments propre au Java EE SDK
  * Java EE Platform = Java EE SDK, ce n'est qu'un SDK, la notion de plate-forme étant une interpolation du 2ème degré.
  * JRE = Java SE Runtime Environment (voir ensuite la différence entre JRE public et JRE privé).

---


# Eclipse IDE for Java EE #

**Téléchargement**
  * Lien : http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2
  * Version : Helios Service Release 2 (3.6.2)
  * Fichier : eclipse-jee-helios-SR2-win32.zip

**Description**<br />
Contient l'IDE pour Java ainsi que de nombreux outils pour aider les développeurs utilisant Java EE et s'intéressant aux applications Web.

# Apache Tomcat #

**Documentation**
  * http://en.wikipedia.org/wiki/Apache_Tomcat
  * http://www.javaworld.com/javaworld/jw-12-2007/jw-12-appservers.html
  * http://www.velocityreviews.com/forums/t360952-what-is-the-difference-between-geronimo-and-tomcat.html

**Téléchargement**
  * Lien : http://tomcat.apache.org/download-70.cgi
  * Version : 7.0.8
  * Fichier : apache-tomcat-7.0.8.exe

**Description**<br />
Apache Tomcat, à ne pas confondre avec les serveurs HTTP Apache, est un conteneur de servlets Java EE (donc pas vraiment un serveur d'applications. Il comporte également un serveur HTTP (avec Coyote). Il implémente
donc les spécifications des Java Servlet et des JavaServer Pages (avec Catalina et Jaspr).

# Spring Framework #

**Documentation**
  * http://en.wikipedia.org/wiki/Spring_Framework
  * http://en.wikipedia.org/wiki/Enterprise_JavaBean
  * http://www.capcourse.com/Library/Spring+Hibernate/Spring+Hibernate_20_32.pdf
  * http://www.shoesobjects.com/blog/2004/11/21/1101083542880.html
  * http://static.springsource.org/spring-ws/sites/2.0/faq.html
  * http://www.javabeat.net/tutorials/8-spring-framework-beginners-tutorial.html
  * http://www.ibm.com/developerworks/web/library/wa-spring1/

**Téléchargement**
  * Lien : http://www.springsource.com/download/community
  * Version : 3.0.5.RELEASE (Spring Framework) ; 2.0.0.RELEASE (Spring Web Services)
  * Fichier : spring-framework-3.0.5.RELEASE.zip ; spring-ws-2.0.0.RELEASE-full.zip
  * Remarque : L'enregistrement n'est obligatoire, cliquez sur le lien qui mène à la page de téléchargement.

**Description**<br />
Spring est un framework Java qui a eu du succès nottament du fait de l'apport d'un nouveau modèle, plus efficace que celui des Enterprise JavaBeans, concernant les composants logiciels côté serveur. Spring offre donc plusieurs fonctionnalités pour les applications Java : Data access, Transaction management, Model-view-controler, Batch processing, etc.


---

**> Web Services**

**Documentation**
  * http://www.w3.org/TR/ws-arch/
  * http://en.wikipedia.org/wiki/Web_service
  * http://fr.wikipedia.org/wiki/Service_Web
  * http://fr.wikipedia.org/wiki/Representational_state_transfer
  * http://fr.wikipedia.org/wiki/Liste_des_sp%C3%A9cifications_des_Services_Web_WS-*

**Description**<br />
Un Web Service est un programme permettant la communication et l'échange de données entre deux applications, dans des environnements distribués. Son interface peut être décrite avec un WSDL et les interactions avec le système peuvent être effectuées en utilisant le protocole SOAP. On a deux types de Services Web :
  * Services Representational State Transfert (REST) : utilisation de requêtes HTTP de type GET, POST, PUT, DELETE ;
  * Services WS-**, exposant les mêmes fonctions sous la forme de services exécutables à distance.**

**> Web Services Frameworks**

**Documentation**
  * http://ws.apache.org/axis/
  * http://en.wikipedia.org/wiki/Apache_Axis
  * http://fr.wikipedia.org/wiki/Apache_Axis
  * http://www.theserverside.com/news/1363958/Axis-Axis2-and-CXF-Surveying-the-WS-Landscape
  * http://en.wikipedia.org/wiki/List_of_web_service_frameworks
  * http://en.wikipedia.org/wiki/Comparison_of_web_application_frameworks
  * http://en.wikipedia.org/wiki/Web_application_framework
  * http://stackoverflow.com/questions/1243247/difference-between-apache-cxf-and-axis

**Description**<br />
Les WS Frameworks sont des kits de composants logiciels conçus pour le développement d'applications Web et de WS. Beaucoup d'entre eux sont basés sur le modèle MVC. Deux exemples de frameworks pour les WS, Axis et CXF. Apparament, CXF a été conçu pour répondre pour pouvoir bien fonctionner avec Spring, ce qui n'est pas forcément le cas de Axis.

**> SOAP**

**Documentation**
  * http://www.w3.org/TR/ws-arch/#id2260892
  * http://www.w3.org/TR/wsdl
  * http://en.wikipedia.org/wiki/SOAP
  * http://fr.wikipedia.org/wiki/Simple_Object_Access_Protocol
  * http://www.w3.org/TR/soapjms/

**Description**<br />
SOAP (anciennement pour Simple Object Access Protocol) est un protocole d'échange de données pour les Web Services.

**> WSDL**

**Documentation**
  * http://www.w3.org/TR/wsdl
  * http://www.w3.org/TR/ws-arch/#id2260892
  * http://www.whiteshaarks.com/blog/2010/01/wsdl/

**Description**<br />
Le WSDL est un langage de description de Web Services, en décrivant la structure des messages qui peuvent être transmis et compris par le Web Service en question.

---


# Apache CXF #

**Documentation**
  * http://en.wikipedia.org/wiki/Apache_CXF
  * http://cxf.apache.org/
  * http://en.wikipedia.org/wiki/Java_Message_Service
  * http://en.wikipedia.org/wiki/Java_Business_Integration
  * http://fr.wikipedia.org/wiki/Java_Business_Integration
  * http://fr.wikipedia.org/wiki/Enterprise_Service_Bus
  * http://cxf.apache.org/docs/tools.html

**Téléchargement**
  * Lien : http://cxf.apache.org/download.html
  * Version : 2.3.2
  * Fichier : apache-cxf-2.3.2.zip

**Description**<br />
Framework pour le développement de services, en aidant à la mise en place de services frontaux (APIs) et au support de nombreux protocoles de communication (SOAP, XML/HTTP, RESTful HTTP, CORBA) passant sur HTTP, JMS ou JBI (qui sont implémentés par les Enterprise Service Bus). Ce framework est issu de la fusion de deux projets, Celtix et XFire d'où le nom de CXF. Apache CXF dispose également de nombreux outils tels que java2ws, qui permet la génération d'un fichier WSDL à partir d'un code source Java.

# Hibernate #

**Documentation**
  * http://en.wikipedia.org/wiki/Hibernate_%28Java%29
  * http://fr.wikipedia.org/wiki/Hibernate

**Téléchargement**
  * Lien : http://www.hibernate.org/downloads
  * Version : 3.6.1-Final
  * Fichier : hibernate-distribution-3.6.1.Final-dist.zip

**Description**<br />
Hibernate est une librairie Java permettant de faire un mapping entre des objets Java et des bases de données relationnelles. Elle permet, entre autre, d'établir de la persistance pour des POJOs et est utilisable seule (en standalone) ou avec Java EE, dans des servlets (compatible avec Apache Tomcat et Spring).


---

**> Enterprise Service Bus**

**Documentation**
  * http://fr.wikipedia.org/wiki/Enterprise_Service_Bus
  * http://fr.wikipedia.org/wiki/JBI
  * http://fr.wikipedia.org/wiki/Java_Message_Service
  * http://en.wikipedia.org/wiki/Category:Message-oriented_middleware
  * http://www.vasanth.in/2005/07/16/building-esb-to-support-soa/

**Description**<br />
Middleware permettant donc la communication entre plusieurs applications. Un ESB est l'une des implémentations de la norme Java Business Integration (JBI). Ceux-ci font appel à plusieurs outils pour la mise en œuvre de la communication :
- Middleware Orienté Message (JMS) : permet les échanges asynchrones (gestion de files de messages)
- Services Web (WSDL, envoi en SOAP du côté application, Biding Components du côté ESB) : interface entre les applications et le bus ;
- Transformations (XSLT pour le Service Engine) : Traduction entre les différents protocoles qui diffèrent selon les applications ;
- Routage intelligent.

---


# Apache ServiceMix #

**Documentation**
  * http://servicemix.apache.org/home.html
  * http://servicemix.apache.org/tutorials.html
  * http://servicemix.apache.org/15-beginner-using-jmx-to-look-inside-the-esb.html
  * http://en.wikipedia.org/wiki/Apache_ServiceMix

**Téléchargement**
  * Lien : http://servicemix.apache.org/download.html
  * Version : 3.2.2
  * Fichier : apache-servicemix-3.3.2.zip

**Description**<br />
L'ESB ServiceMix est compatible avec Java SE et Java EE et intègre également un support pour Spring. Il est composé d'un conteneur JBI. Cet ESB laisse aussi la possibilité de se connecter avec d'autres ESB, en fournissant une API dédiée. Son statut est contrôlable en utilisant les outils du Java Management Extensions (JMX).

# Apache Camel #

**Documentation**
  * http://en.wikipedia.org/wiki/Apache_Camel
  * http://camel.apache.org/
  * http://camel.apache.org/enterprise-integration-patterns.html
  * http://camel.apache.org/dsl.html
  * http://www.javaworld.com/javaworld/jw-06-2008/jw-06-dsls-in-java-1.html?page=1
  * http://fusesource.com/docs/router/1.5/defining_routes/FMRS.BJDS.html
  * http://camel.apache.org/tutorials.html
  * http://servicemix.apache.org/3-beginner-using-apache-camel-inside-servicemix.html
  * http://camel.apache.org/is-camel-an-esb.html
  * http://camel.apache.org/2010/09/28/camel-riders-to-discontinue-support-for-1x-branch.html

**Téléchargement**
  * Lien : http://camel.apache.org/download.html
  * Version : 2.6.0
  * Fichier : apache-camel-2.6.0.zip

**Liens de téléchargement**
  * http://camel.apache.org/download.html

**Description**<br />
Camel est un framework qui implémente les recommendations données par le livre de G. Hohpe et de B. Woolf sur les Enterprise Integration Patterns. Il permet la définition et la réalisation des règles de routage et de médiation. Ces règles sont configurables soit avec Spring, soit en utilisant un Java Domain Specific Language (DSL) qui permet une intégration poussée avec l'IDE (plus besoin d'éditer des fichiers XML, la configuration se fait directement dans l'IDE). Camel est enfin utilisable avec Apache ServiceMix et Apache CXF entre autres.

# Fuse ESB #

**Documentation**
  * http://fusesource.com/products/enterprise-servicemix/
  * http://fusesource.com/docs/esb/4.3.1/esb_install_guide/index.html
  * http://servicemix.apache.org/whats-the-difference-between-servicemix-and-fuse.html

**Téléchargement**
  * Lien : http://fusesource.com/products/enterprise-servicemix/
  * Version : 4.3.1-fuse-00-00
  * Fichier : apache-servicemix-4.3.1-fuse-00-00.zip

**Description**<br />
Fuse ESB est un ESB basé sur Apache ServiceMix. Il s'agit d'une sorte de distribution du produit libre ServiceMix avec une valeur ajoutée sur l'offre de support et de formation garantie par la société FuseSource. Cette société propose, de la même manière, de nombreux produits Apache libres en apportant une valeur ajoutée comme Fuse Mediation Router (basé sur Apache Camel) ou encore Fuse Services Framework (basé sur Apache CXF).

# PostgreSQL #

**Documentation**
  * http://www.postgresql.org/
  * http://www.postgresql.org/docs/9.0/interactive/index.html

**Téléchargement**
  * Lien : http://www.postgresql.org/download/windows
  * Version : 9.0.3-1
  * Fichier : postgresql-9.0.3-1-windows.exe

**Description**<br />
PostgreSQL est un système de gestion de base de données relationnelles/objet.


---

**> Project/Build Management Tools**

**Documentation**
  * http://en.wikipedia.org/wiki/Build_automation
  * http://www.google.com/Top/Computers/Software/Build_Management/Build_Manager_Tools/

**Description**<br />
Un outil d'automatisation de build est un outil gérant, via des scripts, les opérations de compilation, de packaging (pour les livrables utilisateur), de tests, de déploiement et de gestion de la documentation (avec ou sans les release notes).

---


# Apache Maven #

**Documentation**
  * http://maven.apache.org/
  * http://maven.apache.org/what-is-maven.html
  * http://maven.apache.org/maven-features.html
  * http://java.dzone.com/news/maven-or-ant
  * http://ant.apache.org/
  * http://kent.spillner.org/blog/work/2009/11/14/java-build-tools.html

**Téléchargement**
  * Lien : http://maven.apache.org/download.html
  * Version : 3.0.2
  * Fichier : apache-maven-3.0.2-bin.zip

**Description**<br />
Maven est un outil de gestion de projet. Il y a un grand débat sur quel outil de gestion de projet choisir (Ant, Maven, autre ?).

# Apache Ant #

**Documentation**
  * http://ant.apache.org/

**Téléchargement**
  * Lien : http://ant.apache.org/bindownload.cgi
  * Version : 1.8.2
  * Fichier : apache-ant-1.8.2-bin.zip

**Description**<br />
Ant est un utilitaire permettant l'automatisation des builds en utilisant des fichiers XML, similaire à l'outil Make avec les Makefiles.