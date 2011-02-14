
(ns clws.util
  (:use [clojure.java.io :only [file]]))

(defn- make-path [file]
  {:name (.getName file)
   :size (.length file)
   :is-directory (.isDirectory file)
   :last-modified (java.util.Date. (.lastModified file))
   :can-read (.canRead file)
   :is-hidden (.isHidden file)})

(defn list-path
  [path]
  (let [p (file path)]
    (if (.isDirectory p)
      (sort-by :name (map make-path
			  (seq (.listFiles p))))
      [(p)])))
