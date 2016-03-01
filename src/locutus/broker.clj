(ns locutus.broker
  (:require [clojure.core.async :refer [put! chan go-loop <! close!]]
            [mount.core :refer [defstate]]
            [locutus.config :refer [config]])
  (:import (io.netty.channel ChannelHandlerAdapter)))

(defn make-channel-handler [ch]
  (println "Starting channel handler!")
  (let [close-ch (chan)]
    (go-loop []
      (let [x (<! ch)]
        (if x
          (do (println "Got" x)
              (recur))
          (put! close-ch true))))
    (go-loop []
      (<! close-ch)
      (println "Stopping channel handler!"))))

(defn start-netty-mqtt-broker []
  (let [ch (chan 32)
        handler (make-channel-handler ch)]
    {:channel ch :handler handler}))

(defn stop-netty-mqtt-broker [broker]
  (when (:channel broker)
    (close! (:channel broker))))

(defstate netty-mqtt-broker
  :start (start-netty-mqtt-broker)
  :stop (stop-netty-mqtt-broker netty-mqtt-broker))
