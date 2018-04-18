(ns robo-sys.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defprotocol Robot
  (sensors [this])
  (leds [this]))



(defn get-sensor-data [sensor-root sensor-id]
  {:id          sensor-id
   :address     (slurp (io/file (str sensor-root "/" sensor-id "/address")))
   :driver-name (slurp (io/file (str sensor-root "/" sensor-id "driver_name")))})

(defn sensor-folder [folder]
  (->> (.listFiles folder)
       (into [])))




(defn load-sensor [root-path]
  (let [sensor-path (str root-path "/lego-sensor")]
    (->> (io/file sensor-path)
         sensor-folder
         (filter #(.isDirectory %))
         (map #(.getName %))
         (filter #(str/starts-with? % "sensor"))
         (map #(get-sensor-data sensor-path %)))))



(defrecord Evo-Robot []
  Robot
  (sensors [this]
    [{:name :touch}])
  (leds [this]))
