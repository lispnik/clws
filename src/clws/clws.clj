
(ns clws.clws
  (:use [clojure.contrib.command-line :only (with-command-line)]
	[clws.core :only (start)])
  (:gen-class))

(defn -main [& args]
  (with-command-line args
    "clws -- Command Line Web Server"
    [[port p "Port to listen on" 4580]
     [address a "Interface to bind to" "localhost"]
     [recursive? r? "Recursive directories listings"]
     [search? s? "Enable file searches"]
     [verbose? v? "Verbose output"]
     targets]
    (start address port recursive? search? verbose? targets)))
