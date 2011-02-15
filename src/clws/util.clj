
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

(defn relative-time
  ([target relative-to units]
     (let [diff (- (.getTime target) (.getTime relative-to))]
       (loop [a (Math/abs diff)
	      u units
	      r []]
	 (if (zero? (count u))
	   [(if (neg? diff) :before :after) r]
	   (let [p (reduce * u)]
	     (let [d (long (/ a p))]
	       (recur (- a (* d p))
		      (rest u)
		      (conj r d))))))))
  ([target relative-to]
     (relative-time target (java.util.Date.) [24 60 60 1000 1]))
  ([target]
     (relative-time target (java.util.Date.))))

(comment (relative-time (java.util.Date. 111 1 1)))


