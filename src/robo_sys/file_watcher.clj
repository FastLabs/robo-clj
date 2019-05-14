(ns robo-sys.file-watcher
  (:require [hawk.core :as hawk]
            [clojure.java.io :as io])
  (:import (java.io FileFilter)))


(defn load-sensor
  [sensor-path]
  {:address (slurp (io/file sensor-path "address"))
   :driver-name (slurp (io/file sensor-path "driver_name"))})

(def sensor-filter (reify FileFilter
                     (accept [this file]
                       (and (.isDirectory file)
                            (.startsWith (.getName file) "sensor")))))


(defn load-sensors
  [sysfs]
  (let [sensor-dir  (io/file sysfs "lego-sensor")]
    (prn (doall(map load-sensor (.listFiles sensor-dir sensor-filter))))
    sysfs))

(defn load-leds
  [sysfs])


(defn load-sys
  [sysfs-path]
  (-> (io/file sysfs-path)
      load-sensors
      load-leds))




(defn watch-sensors
  [sysfs]
  (hawk/watch! [{:paths   ["resources/robo-sys"]
                 :handler (fn [ctx ev]
                            (prn ev))}]))


