(defproject macroz/locutus "0.1.0"
  :description "Resistance is futile."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [aleph "0.4.1-beta4" :exclusions [org.clojure/clojure byte-streams]]
                 [aero "0.1.4" :exclusions [prismatic/schema]]
                 [bidi "2.0.0"]
                 [yada "1.1.0-20160219.181822-27"]
                 [mount "0.1.9"]]
  :repl-options {:init-ns user
                 :welcome (println "Resistance is futile!")}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.7.0"]
                                  [ring-mock "0.1.5" :exclusions [commons-codec]]]
                   :source-paths ["dev"]}})
