(ns locutus.core
  (:require
   [mount.core :refer [start stop]]
   [locutus.server :refer [server]])
  (:gen-class))

(defn -main [& args]
  (start))
