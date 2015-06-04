# Création d'un web service à partir d'une classe Java #

  * http://www.youtube.com/watch?v=o2Vjs8ylmFM&feature=related
  * http://www.ibm.com/developerworks/library/ws-pojo-springcxf/      En complement du tuto youtube
  * http://www.eclipse.org/webtools/community/education/web/t320/Implementing_a_Simple_Web_Service.pdf
  * http://www.eclipse.org/webtools/jst/components/ws/M4/tutorials/WebServiceExplorer.html

Procédure :
  1. Créer un Dynamic Web Project ;
  1. Ajouter/Créer des classes Java;
  1. Créer un Web Service avec Eclipse.
    * New -> Web Service,
    * Choisir la classe pour service implementation
    * Choisir start service et ne créer pas de client. Ce niveau permet de générer le WSDL. On testera notre WS avec SoapUI. Cocher la case monitor the WS et non pas publish. Nous allons plus tard créer une classe qui permet de publier le WS.
    * Indiquer ou créer le SEI.
    * Finish
  1. Publier le WS
> > Endpoint.publish("http://localhost:9999/serviceXX", new ServiceImpl());
  1. Tester le WS avec SoapUI
> > New project -> click droit sur le projet -> add wsdl -> charger le fichier wsdl à tester -> une fenêtre contenant la requête apparait

  * Explication des fichiers xml généré:  http://www.ibm.com/developerworks/webservices/library/ws-pojo-springcxf/

# Utilisation Postgres #

  * Apres les étapes de l'installation, connecter sur la base de données en tant que superutilsateur (cad pas de username et mot de passe défini lors de l'installation).
  * http://www.postgresql.org/docs/manuals/


# Utilisation PostGIS #

  * http://tokumine.com/postgis/

# Hibernate #

  * http://docs.jboss.org/hibernate/core/3.3/reference/en/html/tutorial.html
  * http://www.laliluna.de/articles/first-hibernate-example-tutorial.html
  * http://www.roseindia.net/hibernate/index.shtml
  * http://facestutorials.icefaces.org/tutorial/hibernate-tutorial.html#stepfour


> A propos du mappings, deux type de mappings sont possibles: xml ou annotation. On utilise plutôt les annotations comme ce qu'on a fait en 3IF avec TopLink
  * http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/

# Maven/Spring/Hibernate #

Utiliser le marketplace d'Eclipse pour installer Maven + les aides dans l'autre page du wiki concernant Maven

  * [maven + spring](http://blog.netapsys.fr/index.php/post/2008/04/13/Introduction-A-Spring-MVC)
  * [maven + spring + hibernate](http://www.mkyong.com/spring/maven-spring-hibernate-annotation-mysql-example/)
  * http://www.laliluna.de/articles/eclipse-spring-jdbc-tutorial-en.html
  * http://www.laliluna.de/articles/first-hibernate-example-tutorial.html
  * [CXF + Spring + maven](http://www.objis.com/formation-java/tutoriel-web-services-integration-cxf-spring-maven.html)
  * [rest mapping xml/json](http://bdoughan.blogspot.com/2010/08/creating-restful-web-service-part-15.html)
  * [cxf rest](http://sushantworld.wordpress.com/2011/01/23/apache-cxf-restful-web-service/)

# CXF #

## References ##

  * [Doc CXF](http://cxf.apache.org/docs/index.html)
  * [WS CXF Spring](http://www.kezen.fr/webservice-java-avec-cxf-et-spring-partie1/)
  * [Client CXF Spring](http://www.kezen.fr/client-webservice-cxf-spring-partie2/)

## Simuler requete depuis commande ##

Utiliser l'utilitaire **curl**

Exemple de requete :
```
curl.exe --header "content-type: application/json" --data @req.json  -X POST http://localhost:8080/inpranet/services/geo
```

avec @req.json un fichier :
```
{"geopos":{"longitude":4.857,"latitude":45.76,"time":"2011-03-27T16:47:15.523+02:00"}}
```

[JAXB + JSON Provider](http://docs.jboss.org/resteasy/docs/1.0.1.GA/userguide/html/Content_Marshalling_Providers.html#JAXB_+_JSON_provider)

Lancer jetty sur un autre port :
```
mvn -Djetty.port=9000 jetty:run
```

## Web Service ##

**WEB-INF/web.xml**
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd"
	version="2.4">

	<!-- Lien vers le fichier qui gere les beans -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/appContext.xml</param-value>
	</context-param>
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<!-- utilisation d'une servlet CXF comme "dispatcher" -->
	<servlet>
		<servlet-name>CXFServlet</servlet-name>
		<servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<!-- URL mapping. Toutes les requetes de type domain-name:port/war-name/* seront routees sur cette servlet -->
	<servlet-mapping>
		<servlet-name>CXFServlet</servlet-name>
		<url-pattern>/*</url-pattern>
	</servlet-mapping>
</web-app>
```

**WEB-INF/appContext**
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:jaxws="http://cxf.apache.org/jaxws"
	xsi:schemaLocation="
http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
http://cxf.apache.org/jaxws http://cxf.apache.org/schemas/jaxws.xsd">

	<!-- Charge les beans de CXF -->
	<import resource="classpath:META-INF/cxf/cxf.xml" />
	<import resource="classpath:META-INF/cxf/cxf-servlet.xml" />
	<import resource="classpath:META-INF/cxf/cxf-extension-soap.xml"/>

	<!-- Définition du Endpoint JAX-WS => pas besoin d'ecrire une classe pour publier le WS
		Reference une classe annotee @WebService 
		adress: adresse relative, ici domain-name:port/war-name/zone
	-->
	<jaxws:endpoint 
	  id="zoneService" 
	  implementor="com.inpranet.zone.service.ZoneService" 
	  address="/zone" />
</beans>
```

**IService**
```
@WebService
public interface IService{
  public String hello(String name);
```

**ServiceImpl**
```
@WebService(endpointInterface = "com.inpranet.etc.etc.ServiceImpl")
public class ServiceImplimplements IService{
  public String hello(String name) {
    return "Hello " + name;
  }
```

**Lancer le WS**
```
mvn jetty:run
```

**Exemple d'accès :**
```
http://localhost:8080/inpranet/zone
```

**exemple wsdl**
```
http://localhost:8080/inpranet/zone?wsdl
```


## Client ##

Utiliser le wsdl créé précédemment pour générer les classes utiles à la contruction du client

=> **pom.xml**
```
<plugin>
				<groupId>org.apache.cxf</groupId>
				<artifactId>cxf-codegen-plugin</artifactId>
				<version>${cxf.version}</version>
				<executions>
					<execution>
						<id>generate-sources</id>
						<phase>generate-sources</phase>
						<configuration>
							<sourceRoot>${basedir}/src/main/java/</sourceRoot>
							<wsdlOptions>
								<wsdlOption>
									<wsdl>${basedir}/src/main/resources/wsdl/monwsdl.wsdl</wsdl>
								</wsdlOption>
							</wsdlOptions>
						</configuration>
						<goals>
							<goal>wsdl2java</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
```

**web.xml => idem qu'au dessus**

**package com/resources, ajout du fichier inpranet-service.xml**
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:jaxrs="http://cxf.apache.org/jaxrs"
	xmlns:jaxws="http://cxf.apache.org/jaxws"
	xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://cxf.apache.org/jaxrs
http://cxf.apache.org/schemas/jaxrs.xsd
http://cxf.apache.org/jaxws 
http://cxf.apache.org/schemas/jaxws.xsd"
	default-lazy-init="false">

	<!-- com.inpranet.zone.service.IZoneService a ete genere par le plugin maven à partir du wsdl 
	adress : point d'entree du service -->
	<jaxws:client id="monservice" serviceClass="com.inpranet.etc.etc.IService"
		address="http://localhost:port/inpranet/monservice">
	</jaxws:client>
</beans>
```

**utilisation du client dans le code java**
```
		ClassPathXmlApplicationContext     context =
		    new ClassPathXmlApplicationContext(new String[]     {"inpranet-service.xml"});
		IService service =     (IService)context.getBean("monservice");
		
		String ret = service.hello("lolilol");
```

## Marshalling ##

```
JAXBContext context = JAXBContext.newInstance(MyPerson.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(p, System.out);
```