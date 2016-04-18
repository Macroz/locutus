(ns locutus.api
  (:require [clojure.core.async :as async]
            [aleph.http :as http]
            [manifold.stream :as s]
            [bidi.vhosts :as bidi]
            [yada.yada :as yada :refer [yada]]
            [locutus.beacon :as beacon]
            [locutus.broker :as broker]))

(defn read-mqtt-fixed-header [[h l]]
  {:type (bit-shift-right (bit-and 240 h) 4)
   :flags (bit-and 15 h)
   :length l})

(defn to-bin [b]
  (.replaceAll (format "%8s" (Integer/toBinaryString b)) " " "0"))

;;(def adapter (broker/make-netty (async/chan)))

(defn websocket-handler [req]
  (println req)
  ;;(adapter req)
  #_(let [s @(http/websocket-connection req)
        in-ch (async/chan)
        out-ch (async/chan)]
    (println "Connected ws!")
    (s/connect s in-ch)
    (s/connect out-ch s)
    (async/go-loop []
      (let [x (async/<! in-ch)]
        (when x
          (println "data" (map to-bin x))
          (println "head" (read-mqtt-fixed-header (take 2 x)))
          (async/>! out-ch (str "hello " x))
          (recur))))))

(defn api []
  ["" [;;["/ws" websocket-handler]
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

