(ns locutus.api
  (:require [yada.yada :as yada :refer [yada]]))

(defn api []
  ["/observations"
   {"" (yada "hello")}])

