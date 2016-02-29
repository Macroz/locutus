(ns locutus.api
  (:require [bidi.vhosts :as bidi]
            [yada.yada :as yada :refer [yada]]))

(defn api []
  ["" [["/" (yada "Resistance is futile!")]
       ["/observations" (yada {:msgs ["We are the borg."]})]
       [true (fn [req] {:status 404 :body "404"})]]])

(defn handler [scheme host port]
  (let [model (bidi/vhosts-model
               [[{:scheme scheme
                  :host (str host ":" port)}]
                (api)])]
    (bidi/make-handler model)))

