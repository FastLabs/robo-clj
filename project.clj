(defproject robo-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clj-ev3dev "0.2.0-SNAPSHOT"]
                 [hawk "0.2.11"]]


  :main ^:skip-aot robo-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
