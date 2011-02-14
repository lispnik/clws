
(ns clws.core
  (:use ring.handler.dump
	ring.middleware.stacktrace
	ring.middleware.reload
        ring.middleware.file-info
        ring.middleware.file
        ring.middleware.lint
        ring.adapter.jetty
	compojure.core
	compojure.handler
	compojure.route)
  (:use [clws.view :only [index-view]]))

(defroutes clws
  (GET "/" [] (index-view "/etc" "/etc")))

(defn make-app [targets]
  (let [target (first targets)]
    (-> #'clws
	(wrap-file target)
	(wrap-file-info)
	(wrap-reload '(clws.core))
	(wrap-lint)
	(wrap-stacktrace))))

(defn start
  ([address port targets recursive search verbose]
     (run-jetty (make-app ["/etc"])
		{:port port
		 :host address
		 :join? false}))
  ([address port targets]
     (start address port targets false false false)))

(defonce server (start "localhost" 4580 ["/etc/"]))

(comment
  (.stop server))

