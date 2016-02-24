(ns locutus.server
  (:require [aleph.http :as http]
            [bidi.vhosts :refer [vhosts-model make-handler]]
            [yada.yada :as yada :refer [yada]]
            [mount.core :refer [defstate]]
            [locutus.config :refer [config]]
            [locutus.api :as api]))

(defn start-server []
  (let [port (:port config)
        model (vhosts-model
               [[{:scheme :http :host (str "localhost:" port)}]
                (api/api)])]
    (http/start-server (make-handler model) {:port port :raw-stream? true})))

(defn stop-server [server]
  (.close server))

(defstate server
  :start (start-server)
  :stop (stop-server server))
