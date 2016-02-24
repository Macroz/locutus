(ns locutus.config
  (:require [aero.core :refer [read-config]]
            [clojure.java.io :as io]
            [mount.core :refer [defstate]]))

(defn load-config []
  (read-config
   (io/resource "config.edn")
   {}))

(defstate config :start (load-config))

