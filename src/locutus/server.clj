(ns locutus.server
  (:require [aleph.http :as http]
            [mount.core :refer [defstate]]
            [locutus.config :refer [config]]
            [locutus.api :as api]))

(defn start-server []
  (let [scheme (:scheme config)
        host (:host config)
        port (:port config)]
    (println "Starting server" host "at port" port)
    (http/start-server (api/handler scheme host port)
                       {:port port :raw-stream? true})))

(defn stop-server [server]
  (.close server))

(defstate server
  :start (start-server)
  :stop (stop-server server))
