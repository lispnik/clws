
(ns clws.view
  (:use net.cgrand.enlive-html)
  (:use clws.util))

(deftemplate index-template "index.html"
  [path & body-fns]
  [#{:title :h1}] (content (str "Index of " path))
  [:.entries] (apply do-> body-fns))

(defsnippet entry-snippet "index.html" [:.entries :> first-child] 
  [file]
  [:a] (do->
	(set-attr :href (file :name))
	(content (file :name)))
  [[:td (nth-of-type 1)]] (set-attr :class (if (file :is-directory) "dir" "file"))
  [:.size] (content (str (file :size)))
  [:.date] (content (str (file :last-modified))))

(defn index-view
  ([title path]
     (index-template
      title
      (content (map entry-snippet
		    (list-path path))))))

(comment
  (apply str (index-view "lol" "/etc"))
  (first (list-path "/etc/")))

