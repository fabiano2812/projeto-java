
name := """app-treino"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  javaJdbc,
  filters,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  "org.hibernate" % "hibernate-envers" % "4.3.10.Final",
  "org.postgresql" % "postgresql" % "42.2.2",
  "org.jasypt" % "jasypt" % "1.9.2",
  "com.itextpdf" % "itext7-core" % "7.1.9",
  "com.itextpdf" % "html2pdf" % "2.1.6",
  "org.apache.commons" % "commons-email" % "1.3.1",
  "org.apache.commons" % "commons-io" % "1.3.2",
  "javax.el" % "javax.el-api" % "3.0.0",
  "org.glassfish.web" % "el-impl" % "2.2",
  "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.4",
  "com.github.dfabulich" % "sitemapgen4j" % "1.0.4",
  "com.lowagie" % "itext" % "2.1.7",
  "net.sf.jasperreports" % "jasperreports" % "6.4.0",
  "org.apache.xmlgraphics" % "xmlgraphics-commons" % "1.5",
  "org.apache.xmlgraphics" % "batik-bridge" % "1.8",
  "org.apache.xmlgraphics" % "batik-svg-dom" % "1.9.1",
  "net.sf.barcode4j" % "barcode4j" % "2.1",
  "com.drewnoakes" % "metadata-extractor" % "2.9.1",
  "org.imgscalr" % "imgscalr-lib" % "4.2",
  "org.xhtmlrenderer" % "flying-saucer-pdf-itext5" % "9.0.1",
  "org.apache.pdfbox" % "pdfbox" % "1.8.1",
  "com.thoughtworks.xstream" % "xstream" % "1.4.11.1",
  "me.pagar" % "pagarme-java" % "1.5.9",
  "com.google.firebase" % "firebase-admin" % "7.0.1",
  "org.jsoup" % "jsoup" % "1.14.3",
  "org.apache.poi" % "poi" % "3.17",
  "javax.xml.bind" % "jaxb-api" % "2.3.1"
)

resolvers ++= Seq(
  "Local Maven Repository" at "file:///C:/Users/Agium/.m2/repository",
  "Jasper" at "http://jaspersoft.artifactoryonline.com/jaspersoft/third-party-ce-artifacts/",
  "JBoss 3rd-party" at "https://repository.jboss.org/nexus/content/repositories/thirdparty-releases/"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

//nao cria jar de external sources
PlayKeys.externalizeResources := false

//pipelineStages := Seq(uglify)

offline:=true

playEnhancerEnabled := false

EclipseKeys.preTasks := Seq(compile in Compile)
