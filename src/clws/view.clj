
(ns clws.view
  (:require [net.cgrand.enlive-html :as html])
  (:use [clws.util]))

(html/deftemplate index-template "index.html"
  [path & body-fns]
  [#{:title :h1}] (html/content (str "Index of " path))
  [[:tr.entry (html/nth-of-type 1)]] (apply html/do-> body-fns))

(index-template "fuck")

;;; [[:.content (nth-of-type 1)] :> first-child]

(html/defsnippet entry-snippet "index.html" [:.entry] 
  [file]
  [:.file :> :a] (html/do->
		  (html/set-attr :href (file :name))
		  (html/content (file :name)))
  [:img.icon] (html/set-attr :alt (if (file :is-directory) "dir" "file"))
  [:.size] (html/content (str (file :size)))
  [:.date] (html/content (str (file :last-modified))))

(defn index-view
  ([title path]
     (index-template
      title
      (html/content (map entry-snippet
			 (list-path path))))))

(comment
  (apply str (index-view "lol" "/etc")))

