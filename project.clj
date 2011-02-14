
(defproject clws "1.0.0-SNAPSHOT"
  :description "Command Line Web Server"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
		 [ring/ring-core "0.3.5"]
		 [ring/ring-devel "0.3.5"]
		 [ring/ring-jetty-adapter "0.3.5"]
		 [enlive "1.0.0-SNAPSHOT"]
		 [compojure "0.6.0"]
		 [eu.medsea.mimeutil/mime-util "2.1.3"]]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]]
  :main clws.clws
  :uberjar-name "clws.jar")
