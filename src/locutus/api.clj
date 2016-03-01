(ns locutus.api
  (:require [bidi.vhosts :as bidi]
            [yada.yada :as yada :refer [yada]]
            [locutus.beacon :as beacon]))

(defn api []
  ["" [["/" (yada "Resistance is futile!")]
       ["/beacons" (yada beacon/known-beacons)]
       ["/observations" (yada {:msgs ["We are the borg."]})]
       [true (fn [req] {:status 404 :body "404"})]]])

(defn handler [scheme host port]
  (let [model (bidi/vhosts-model
               [[{:scheme scheme
                  :host (str host ":" port)}]
                (api)])]
    (bidi/make-handler model)))

