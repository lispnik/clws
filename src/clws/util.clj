
(ns clws.util
  (:require clojure.java.io))

(defn- make-path [file]
  {:file (clojure.java.io/file file)
   :name (.getName file)
   :size (.length file)
   :is-directory (.isDirectory file)
   :last-modified (java.util.Date. (.lastModified file))
   :can-read (.canRead file)
   :is-hidden (.isHidden file)})

(defn list-path
  [path]
  (let [p (clojure.java.io/file path)]
    (if (.isDirectory p)
      (sort-by :name (map make-path
			  (seq (.listFiles p))))
      [(p)])))
