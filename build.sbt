name := "estore-demo"

version := "1.0.0"

scalaVersion := "2.10.3"

seq(webSettings :_*)

scalaSource in Compile := baseDirectory.value / "src/scala"

javaSource in Compile := baseDirectory.value / "src/java"

scalaSource in Test := baseDirectory.value / "test/scala"

javaSource in Test := baseDirectory.value / "test/java"

resourceDirectory in Compile := baseDirectory.value / "conf"

resourceDirectory in Test := baseDirectory.value / "test/conf"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "9.1.0.v20131115" % "container"

libraryDependencies += "org.eclipse.jetty" % "jetty-plus" % "9.1.0.v20131115" % "container"

libraryDependencies += "org.eclipse.jetty" % "jetty-jsp" % "9.1.0.v20131115" % "container"

libraryDependencies += "javax.servlet" % "servlet-api" % "2.5" % "provided"

libraryDependencies += "javax.servlet.jsp" % "javax.servlet.jsp-api" % "2.3.1" % "provided"

libraryDependencies += "javax.servlet" % "jstl" % "1.2"

libraryDependencies += "org.apache.tomcat" % "el-api" % "6.0.39" % "provided"

libraryDependencies += "jaxen" % "jaxen" % "1.1.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.2" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.10" % "test"

libraryDependencies += "commons-beanutils" % "commons-beanutils" % "1.9.1"

libraryDependencies += "org.apache.ant" % "ant" % "1.9.3"

libraryDependencies += "com.h2database" % "h2" % "1.4.177"

libraryDependencies += "c3p0" % "c3p0" % "0.9.1.2"

libraryDependencies += "commons-dbutils" % "commons-dbutils" % "1.5"

libraryDependencies += "javax.mail" % "mail" % "1.4"

libraryDependencies += "commons-fileupload" % "commons-fileupload" % "1.3.1"

libraryDependencies += "commons-io" % "commons-io" % "2.4"

libraryDependencies += "log4j" % "log4j" % "1.2.17"
            
