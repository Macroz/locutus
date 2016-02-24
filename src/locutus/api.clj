(ns locutus.api
  (:require [yada.yada :as yada :refer [yada]]))

(defn api []
  ["" [["/" (yada "Resistance is futile!")]
       ["/observations" (yada "hello")]
       [true (fn [req] {:status 404 :body "404"})]]])

