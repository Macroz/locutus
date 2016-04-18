(ns locutus.broker
  (:require [clojure.core.async :refer [put! chan go-loop <! close!]]
            [mount.core :refer [defstate]]
            [locutus.config :refer [config]]
            [locutus.util :refer [map->stringify-all]]
            [clojurewerkz.propertied.properties :as p])
  (:import (io.moquette.server Server)))

(defn start-moquette-mqtt-broker []
  (let [server (Server.)
        config (map->stringify-all (:moquette config))
        properties (p/load-from config)]
    (.startServer server properties)
    {:server server}))

(defn stop-moquette-mqtt-broker [broker]
  (when-let [server (:server broker)]
    (.stopServer server)))

(defstate moquette-mqtt-broker
  :start (start-moquette-mqtt-broker)
  :stop (stop-moquette-mqtt-broker moquette-mqtt-broker))
