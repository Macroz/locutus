(ns locutus.api
  (:require [aleph.http :as http]
            [manifold.stream :as s]
            [bidi.vhosts :as bidi]
            [yada.yada :as yada :refer [yada]]
            [locutus.beacon :as beacon]))

(defn websocket-handler [req]
  (let [s @(http/websocket-connection req)]
    (println "Got ws!")
    (s/connect s s)))

(defn api []
  ["" [["/ws" websocket-handler]
       ["/" (yada "Resistance is futile!")]
       ["/beacons" (yada beacon/known-beacons)]
       ["/observations" (yada {:msgs ["We are the borg."]})]
       [true (fn [req] {:status 404 :body "404"})]]])

(defn handler [scheme host port]
  (let [model (bidi/vhosts-model
               [[{:scheme scheme
                  :host (str host ":" port)}]
                (api)])]
    (bidi/make-handler model)))

