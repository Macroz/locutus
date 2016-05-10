(defproject macroz/locutus "0.1.0"
  :description "The collective knows where you are."
  :url "http://github.com/Macroz/locutus"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.1-beta4" :exclusions [org.clojure/clojure byte-streams]]
                 [aero "0.1.4" :exclusions [prismatic/schema]]
                 [bidi "2.0.0"]
                 [yada "1.1.0-20160225.101107-28"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/core.async "0.2.374"]
                 [mount "0.1.9"]
                 [io.moquette/moquette-broker "0.8.1"]
                 [clojurewerkz/propertied "1.2.0"]]
  :repositories [["jcenter" "http://jcenter.bintray.com/"]]
  :repl-options {:init-ns user
                 :welcome (println "Resistance is futile!")}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [ring-mock "0.1.5" :exclusions [commons-codec]]]
                   :source-paths ["dev"]}
             :uberjar {:aot :all}}
  :main locutus.core)
