(ns locutus.server
  (:require [aleph.http :as http]
            [bidi.vhosts :refer [vhosts-model make-handler]]
            [locutus.api :as api]))

(defn start []
  (let [port 8888
        model (vhosts-model
               [[{:scheme :http :host (str "localhost:" port)}]
                ["/" (fn [req] {:body "hullo"})]
                (api/api)])]
    (http/start-server (make-handler model) {:port port :raw-stream? true})))
