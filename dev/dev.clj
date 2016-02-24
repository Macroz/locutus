(ns dev
  (:require
   [aero.core :refer [read-config]]
   [clojure.pprint :refer [pprint]]
   [clojure.reflect :refer [reflect]]
   [clojure.repl :refer [apropos dir doc find-doc pst source]]
   [clojure.tools.namespace.repl :refer [refresh refresh-all]]
   [clojure.java.io :as io]
   [schema.core :as s]
   ))

(defn config []
  (read-config
   (io/resource "config.edn")
   {}))
